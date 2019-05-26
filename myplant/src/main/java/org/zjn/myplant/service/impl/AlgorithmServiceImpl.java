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
	 * ÿ��һСʱ���������������ͼ��ָ��
	 */
	@Override
	//@Scheduled(cron="0 * * * * *")//ÿ����ִ��һ��
	@Scheduled(cron="0 0 * * * *")//ÿСʱִ��һ��
	public void detectAirData() {
		System.out.println("����ִ������*******************");
		List<Device> list = deviceDao.queryAll();
		for(Device d : list) {
			if(d.getNumber() != 0) {
				JSONObject jsonObject = new JSONObject();
				//{��clientID��:ID,"WF":1/0,"serial":1,"volume":x}
				jsonObject.put("clientID", d.getDeviceId());
				jsonObject.put("WF", 0);
				jsonObject.put("serial", 0);
				jsonObject.put("volume", 0);
				mqttOutboundServiceImpl.sendMqtt(jsonObject.toString(), d.getDeviceId());
			}
		}
		
	}
	
	
	/*
	 * Ԥ��ÿ�յ�����ʪ��
	 */
	@Override
	//@Scheduled(cron="0/5 * * * * ? ")//���5��ִ��
	@Scheduled(cron="0 30 0 * * *")//��ʱÿ��0:30ִ��
	public void predictMoisture() {
		System.out.println("0:30ִ��������*****************");
		List<Cultivation> list = cultivationDao.queryAll();
		//ȡ��ϵͳ�����е�ֲ����μ���
		for(Cultivation c : list) {
			//����ģ�ͼ�����ɢ����predictEvaporation
			System.out.println(c.toString());
			//ʹ��java Runtime����python����Ԥ��
			//double evaporation = predictEvaporation(c.getDeviceId(), c.getSerial());
			
			//http����puthon�������ṩ�ķ���
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
			
			//��ѯǰһ�������ʪ��
			double moistureLastDay = moistureDao.queryLastDayMoisture(c.getDeviceId(), c.getSerial());
			//System.out.println(moistureLastDay);
			double moistureMin = plantDao.queryById(c.getPlantId()).getMoistureMin();
			//���㵱�յ�����ʪ��
			//System.out.println(moistureMin);
			
			double moistureToday = moistureLastDay - evaporation;
			//�ж��Ƿ���Ҫ��ˮ�����ǣ������water����
			//System.out.println(moistureToday);
			if(moistureToday < moistureMin) {//��Ҫ��ˮ
				//���ƽ�ˮ������ָ��
				System.out.println("��Ҫ��ˮ");
				int time = water(c.getDeviceId(), c.getSerial());
				//д��ˮ��¼
				wateringDao.newWatering(c.getDeviceId(), c.getSerial(), time, new Date());
				//д����ʪ��Ϊ���ֵ
				double maxMoisture = maxmoistureDao.queryMaxMoisture(c.getSoilId());
				moistureDao.insertMoisture(c.getDeviceId(), c.getSerial(), maxMoisture, new Date());
			}else {//����Ҫ��ˮ
				//д����ʪ��Ϊ��ǰֵ
				System.out.println("����Ҫ��ˮ");
				moistureDao.insertMoisture(c.getDeviceId(), c.getSerial(), moistureToday, new Date());
				//System.out.println("����moisture��");
			}
		}
	}
	
	//ͨ��http����python�������ķ��񣬼�����ɢ����
	public String httpGetData(String data) throws Exception{
		String result = "";
		// ����httpclient����
		CloseableHttpClient httpClient = HttpClients.createDefault();
		URI uri = new URIBuilder().setScheme("http")
				.setHost("127.0.0.1:9999/predict/" + data)
				.build();
		// ����get��ʽ�������
		HttpGet httpGet = new HttpGet(uri);

		// ͨ����������ȡ��Ӧ����
		CloseableHttpResponse response = httpClient.execute(httpGet);
		// ��ȡ���ʵ��
        // �ж���������״̬���Ƿ�����(0--200��������)
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        }
        // �ͷ�����
        response.close();
		return result;
		
	}
	
	/*
	 * ���ƽ�ˮ
	 */
	public int water(int deviceId, int serial) {
		//���㽽ˮ�������Իع鷽�������㷽ʽ��ϲˮ�̶�*250 + �������*0.03 + ��-69.10��
		int volume = calculateWaterVolume(deviceId, serial);
		//���ͽ�ˮָ��
		//{��clientID��:ID,"WF":1/0,"serial":1,"volume":x}
		JSONObject newobj = new JSONObject();
		newobj.put("clientID", deviceId);
		newobj.put("WF", 1);
		newobj.put("volume", volume);
		newobj.put("serial", serial);
		mqttOutboundServiceImpl.sendMqtt(newobj.toString(), deviceId);
		System.out.println("������ָ����");
		return 1;
	}
	
	public JSONObject extractFeatures(int deviceId, int serial) {
		Cultivation c = cultivationDao.queryBySerial(deviceId, serial);
//      LAST_WATER_TIME
        Date lastTimeWatering = wateringDao.queryLastTimeWater(deviceId, serial).getWaterTime();
        Date now = new Date();
        int days = (int)(now.getTime() - lastTimeWatering.getTime()) / (1000 * 3600 * 24);
        int num1 = days;
        //�����¶�����
        ArrayList<Double> airTemperature = detectionDao.queryLastDayTemperature(deviceId);
        //����ʪ������
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
	 * Ԥ��ÿ�յ���ɢ����
	 * ʹ��Java Runtime����python����
	 * ��δʹ��
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
//        //�����¶�����
//        ArrayList<Double> airTemperature = detectionDao.queryLastDayTemperature(deviceId);
//        //����ʪ������
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
//			//��ӡ������Ϣ
//			
//			FileInputStream errorStream = (FileInputStream)process.getErrorStream();
//            InputStreamReader isr = new InputStreamReader(errorStream,"gbk");//��ȡ
//            System.out.println(isr.getEncoding());
//            BufferedReader bufr = new BufferedReader(isr);//����
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
	//���㽽ˮ�������Իع鷽�������㷽ʽ��ϲˮ�̶�*250 + �������*0.03 + ��-69.10��
	//����Ľ�ˮ��Ϊʮ������������λΪml
	public int calculateWaterVolume(int deviceId, int serial) {
		// TODO Auto-generated method stub
		double volume;
		Cultivation c = cultivationDao.queryBySerial(deviceId, serial);
		volume = plantDao.queryById(c.getPlantId()).getWaterPreference() * 250 + c.getPotHeight()*(c.getPotDiameter()/2)*(c.getPotDiameter()/2)*3.14 * 0.03 - 69.10;
		return (new Double(volume).intValue()) / 10 * 10;
	}


	//�����ֵ
	public double calculateMean(ArrayList<Double> list) {
		int length = list.size();
		double sum = 0;
		for(Double d : list) {
			sum += d;
		}
		return sum / length;
	}
	
	//���㷽��
	public double calculateVariance(ArrayList<Double> list) {
		int length = list.size();
		double sum = 0;
		double mean = calculateMean(list);
		for(Double d : list) {
			sum += (d - mean) * (d - mean);
		}
		return sum / length;
	}
	//�������
	public double calculateSum(ArrayList<Double> list) {
		int length = list.size();
		double sum = 0;
		for(int i = 1; i < length; i++) {
			sum += (list.get(i) + list.get(i - 1)) / 2;
		}
		return sum;
	}
	

}
