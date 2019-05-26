package org.zjn.myplant.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.zjn.myplant.dao.CultivationDao;
import org.zjn.myplant.dao.DetectionDao;
import org.zjn.myplant.dao.DeviceDao;
import org.zjn.myplant.dao.MaxmoistureDao;
import org.zjn.myplant.dao.MoistureDao;
import org.zjn.myplant.dao.PlantDao;
import org.zjn.myplant.dao.WateringDao;
import org.zjn.myplant.entity.Cultivation;
import org.zjn.myplant.entity.Device;
import org.zjn.myplant.service.*;

import net.sf.json.JSONObject;

@Service
@Component
public class AlgorithmServiceImpl implements AlgorithmService{

	@Autowired
	PlantDao plantDao;
	@Autowired
	DeviceDao deviceDao;
	@Autowired
	CultivationDao cultivationDao;
	@Autowired
	MoistureDao moistureDao;
	@Autowired
	WateringDao wateringDao;
	@Autowired
	MaxmoistureDao maxmoistureDao;
	@Autowired
	DetectionDao detectionDao;
	@Autowired
	MqttOutboundServiceImpl mqttOutboundServiceImpl;
	
	/*
	 * 每隔一小时检测空气参数，发送检测指令
	 */
	@Override
	//@Scheduled(cron="0 * * * * *")//每分钟执行一次
	@Scheduled(cron="0 0 * * * *")//每小时执行一次
	public void detectAirData() {
		System.out.println("整点执行任务*******************");
		List<Device> list = deviceDao.queryAll();
		for(Device d : list) {
			if(d.getNumber() != 0) {
				JSONObject jsonObject = new JSONObject();
				//{“clientID”:ID,"WF":1/0,"serial":1,"volume":x}
				jsonObject.put("clientID", d.getDeviceId());
				jsonObject.put("WF", 0);
				jsonObject.put("serial", 0);
				jsonObject.put("volume", 0);
				mqttOutboundServiceImpl.sendMqtt(jsonObject.toString(), d.getDeviceId());
			}
		}
		
	}
	
	
	/*
	 * 预测每日的土壤湿度
	 */
	@Override
	//@Scheduled(cron="0/5 * * * * ? ")//间隔5秒执行
	@Scheduled(cron="0 30 0 * * *")//定时每天0:30执行
	public void predictMoisture() {
		System.out.println("0:30执行了任务*****************");
		List<Cultivation> list = cultivationDao.queryAll();
		//取出系统中所有的植物，依次计算
		for(Cultivation c : list) {
			//利用模型计算蒸散量，predictEvaporation
			System.out.println(c.toString());
			//使用java Runtime运行python程序预测
			//double evaporation = predictEvaporation(c.getDeviceId(), c.getSerial());
			
			//http调用puthon服务器提供的方法
			JSONObject jsonObject = extractFeatures(c.getDeviceId(), c.getSerial());
			double evaporation = 0;
			try {
				String httpResult = httpGetData(URLEncoder.encode(jsonObject.toString(), "utf-8"));
				JSONObject object = JSONObject.fromObject(httpResult);
				evaporation = Double.parseDouble(object.getString("result"));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(evaporation);
			
			//查询前一天的土壤湿度
			double moistureLastDay = moistureDao.queryLastDayMoisture(c.getDeviceId(), c.getSerial());
			//System.out.println(moistureLastDay);
			double moistureMin = plantDao.queryById(c.getPlantId()).getMoistureMin();
			//计算当日的土壤湿度
			//System.out.println(moistureMin);
			
			double moistureToday = moistureLastDay - evaporation;
			//判断是否需要浇水，若是，则调用water方法
			//System.out.println(moistureToday);
			if(moistureToday < moistureMin) {//需要浇水
				//控制浇水，发送指令
				System.out.println("需要浇水");
				int time = water(c.getDeviceId(), c.getSerial());
				//写浇水记录
				wateringDao.newWatering(c.getDeviceId(), c.getSerial(), time, new Date());
				//写土壤湿度为最大值
				double maxMoisture = maxmoistureDao.queryMaxMoisture(c.getSoilId());
				moistureDao.insertMoisture(c.getDeviceId(), c.getSerial(), maxMoisture, new Date());
			}else {//不需要浇水
				//写土壤湿度为当前值
				System.out.println("不需要浇水");
				moistureDao.insertMoisture(c.getDeviceId(), c.getSerial(), moistureToday, new Date());
				//System.out.println("插入moisture表");
			}
		}
	}
	
	//通过http请求python服务器的服务，计算蒸散发量
	public String httpGetData(String data) throws Exception{
		String result = "";
		// 创建httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		URI uri = new URIBuilder().setScheme("http")
				.setHost("127.0.0.1:9999/predict/" + data)
				.build();
		// 创建get方式请求对象
		HttpGet httpGet = new HttpGet(uri);

		// 通过请求对象获取响应对象
		CloseableHttpResponse response = httpClient.execute(httpGet);
		// 获取结果实体
        // 判断网络连接状态码是否正常(0--200都数正常)
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        }
        // 释放链接
        response.close();
		return result;
		
	}
	
	/*
	 * 控制浇水
	 */
	public int water(int deviceId, int serial) {
		//计算浇水量，线性回归方法，计算方式：喜水程度*250 + 花盆体积*0.03 + （-69.10）
		int volume = calculateWaterVolume(deviceId, serial);
		//发送浇水指令
		//{“clientID”:ID,"WF":1/0,"serial":1,"volume":x}
		JSONObject newobj = new JSONObject();
		newobj.put("clientID", deviceId);
		newobj.put("WF", 1);
		newobj.put("volume", volume);
		newobj.put("serial", serial);
		mqttOutboundServiceImpl.sendMqtt(newobj.toString(), deviceId);
		System.out.println("发出了指令呢");
		return 1;
	}
	
	public JSONObject extractFeatures(int deviceId, int serial) {
		Cultivation c = cultivationDao.queryBySerial(deviceId, serial);
//      LAST_WATER_TIME
        Date lastTimeWatering = wateringDao.queryLastTimeWater(deviceId, serial).getWaterTime();
        Date now = new Date();
        int days = (int)(now.getTime() - lastTimeWatering.getTime()) / (1000 * 3600 * 24);
        int num1 = days;
        //空气温度数据
        ArrayList<Double> airTemperature = detectionDao.queryLastDayTemperature(deviceId);
        //空气湿度数据
        ArrayList<Double> airMoisture = detectionDao.queryLastDayMoisture(deviceId);
//      AT_MEAN
        double atMean = calculateMean(airTemperature);
        double amMean = calculateMean(airMoisture);
        double num2 = atMean;
//      AM_MEAN
        double num3 = amMean;
        System.out.println(num2);
        System.out.println(num3);
//      AT_VARIANCE
        double atVariance = calculateVariance(airTemperature);
        double amVariance = calculateVariance(airMoisture);
        double num4 = atVariance;
//      AM_VARIANCE
        double num5 = amVariance;
        double atSum = calculateSum(airTemperature);
        double amSum = calculateSum(airMoisture);
        int plantKind = plantDao.queryById(c.getPlantId()).getWaterPreference();
        int num6 = plantKind;
        int soilKind = c.getSoilId();
        int num7 = soilKind;
        double plantHeight = c.getPlantHeight();
//      PLANT_KIND
        double num8 = plantHeight;
        double potDiameter = c.getPotDiameter();
//      SOIL_KIND
        double num9 = potDiameter;
        double potHeight = c.getPotHeight();
//      PLANT_VOLUME
        double num10 = potHeight;
//      POT_VOLUME
        int soilArea = c.getSoilArea();
        double num11 = soilArea;
        double soilMoisture = moistureDao.queryLastDayMoisture(deviceId, serial);
//      1DBEFORE_SOIL_MOISTURE
        double num12 = soilMoisture;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("LAST_WATER_TIME", num1);
        jsonObject.put("AT_MEAN", num2);
        jsonObject.put("AM_MEAN", num3);
        jsonObject.put("AT_VARIANCE", num4);
        jsonObject.put("AM_VARIANCE", num5);
        jsonObject.put("PLANT_KIND", num6);
        jsonObject.put("SOIL_KIND", num7);
        jsonObject.put("PLANT_HEIGHT", num8);
        jsonObject.put("POT_DIAMETER", num9);
        jsonObject.put("POT_HEIGHT", num10);
        jsonObject.put("SOIL_AREA", num11);
        jsonObject.put("DBEFORE_SOIL_MOISTURE", num12);
        return jsonObject;
	}
	
	/*
	 * 预测每日的蒸散发量
	 * 使用Java Runtime调用python程序
	 * 暂未使用
	 */
//	@Override
//	public double predictEvaporation(int deviceId, int serial) {
//		String exe = "python";
//        String command = "model/predict_evaporation.py";
//        Cultivation c = cultivationDao.queryBySerial(deviceId, serial);
////      LAST_WATER_TIME
//        Date lastTimeWatering = wateringDao.queryLastTimeWater(deviceId, serial).getWaterTime();
//        Date now = new Date();
//        int days = (int)(now.getTime() - lastTimeWatering.getTime()) / (1000 * 3600 * 24);
//        String num1 = days + "";
//        //空气温度数据
//        ArrayList<Double> airTemperature = detectionDao.queryLastDayTemperature(deviceId);
//        //空气湿度数据
//        ArrayList<Double> airMoisture = detectionDao.queryLastDayMoisture(deviceId);
////      AT_MEAN
//        double atMean = calculateMean(airTemperature);
//        double amMean = calculateMean(airMoisture);
//        String num2 = atMean + "";
////      AM_MEAN
//        String num3 = amMean + "";
//        System.out.println(num2);
//        System.out.println(num3);
////      AT_VARIANCE
//        double atVariance = calculateVariance(airTemperature);
//        double amVariance = calculateVariance(airMoisture);
//        String num4 = atVariance + "";
////      AM_VARIANCE
//        String num5 = amVariance + "";
//        double atSum = calculateSum(airTemperature);
//        double amSum = calculateSum(airMoisture);
////      AT_SUM
//        String num6 = atSum + "";
////      AM_SUM
//        String num7 = amSum + "";
//        int plantKind = plantDao.queryById(c.getPlantId()).getWaterPreference();
////      PLANT_KIND
//        String num8 = plantKind + "";
//        int soilKind = c.getSoilId();
////      SOIL_KIND
//        String num9 = soilKind + "";
//        double plantVolume = c.getPlantVolume();
////      PLANT_VOLUME
//        String num10 = plantVolume + "";
////      POT_VOLUME
//        double potVolume = c.getPotVolume();
//        String num11 = potVolume + "";
//        double soilMoisture = moistureDao.queryLastDayMoisture(deviceId, serial);
////      1DBEFORE_SOIL_MOISTURE
//        String num12 = soilMoisture + "";
//        String[] cmdArr = new String[14];
//        cmdArr[0] = exe;
//        cmdArr[1] = command;
//        cmdArr[2] = num1;
//        cmdArr[3] = num2;
//        cmdArr[4] = num3;
//        cmdArr[5] = num4;
//        cmdArr[6] = num5;
//        cmdArr[7] = num6;
//        cmdArr[8] = num7;
//        cmdArr[9] = num8;
//        cmdArr[10] = num9;
//        cmdArr[11] = num10;
//        cmdArr[12] = num11;
//        cmdArr[13] = num12;
//        Process process;
//        String result = "";
//        String s = "";
//        try {
//			process = Runtime.getRuntime().exec(cmdArr);
//			//打印错误信息
//			
//			FileInputStream errorStream = (FileInputStream)process.getErrorStream();
//            InputStreamReader isr = new InputStreamReader(errorStream,"gbk");//读取
//            System.out.println(isr.getEncoding());
//            BufferedReader bufr = new BufferedReader(isr);//缓冲
//            String line = null;
//            while((line =bufr.readLine())!=null) {
//                System.out.println(line);
//            }
//            isr.close();
//			
//			InputStream is = process.getInputStream();
//	        DataInputStream dis = new DataInputStream(is);
//	        //result = dis.readLine();
//	        
//	        while ((s = dis.readLine()) != null) {  
//	              result += s;  
//	          }  
//	          
//	          dis.close();  
//	          int re = process.waitFor();  
//	          //System.out.println(re);
//	          
//		} catch (Exception e){
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//        System.out.println(result);
//		return Double.parseDouble(result);
//	}
	
	
	
	

	@Override
	//计算浇水量，线性回归方法，计算方式：喜水程度*250 + 花盆体积*0.03 + （-69.10）
	//计算的浇水量为十倍的整数，单位为ml
	public int calculateWaterVolume(int deviceId, int serial) {
		// TODO Auto-generated method stub
		double volume;
		Cultivation c = cultivationDao.queryBySerial(deviceId, serial);
		volume = plantDao.queryById(c.getPlantId()).getWaterPreference() * 250 + c.getPotHeight()*(c.getPotDiameter()/2)*(c.getPotDiameter()/2)*3.14 * 0.03 - 69.10;
		return (new Double(volume).intValue()) / 10 * 10;
	}


	//计算均值
	public double calculateMean(ArrayList<Double> list) {
		int length = list.size();
		double sum = 0;
		for(Double d : list) {
			sum += d;
		}
		return sum / length;
	}
	
	//计算方差
	public double calculateVariance(ArrayList<Double> list) {
		int length = list.size();
		double sum = 0;
		double mean = calculateMean(list);
		for(Double d : list) {
			sum += (d - mean) * (d - mean);
		}
		return sum / length;
	}
	//计算积分
	public double calculateSum(ArrayList<Double> list) {
		int length = list.size();
		double sum = 0;
		for(int i = 1; i < length; i++) {
			sum += (list.get(i) + list.get(i - 1)) / 2;
		}
		return sum;
	}
	

}
