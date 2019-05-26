package org.zjn.myplant.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zjn.myplant.dao.CultivationDao;
import org.zjn.myplant.dao.DetectionDao;
import org.zjn.myplant.dao.DeviceDao;
import org.zjn.myplant.dao.MaxmoistureDao;
import org.zjn.myplant.dao.PlantDao;
import org.zjn.myplant.entity.Cultivation;
import org.zjn.myplant.entity.Detection;
import org.zjn.myplant.entity.Device;
import org.zjn.myplant.entity.Maxmoisture;
import org.zjn.myplant.entity.Plant;
import org.zjn.myplant.service.impl.DeviceServiceImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/userplant")
public class UserPlantController {
	@Autowired
	CultivationDao cultivationDao;
	@Autowired
	DetectionDao detectionDao;
	@Autowired
	PlantDao plantDao;
	@Autowired
	DeviceDao deviceDao;
	@Autowired
	MaxmoistureDao maxmoistureDao;
	@Autowired
	DeviceServiceImpl deviceServiceImpl;
	
	@RequestMapping(value="/query", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody public String userQueryPlants(HttpServletRequest request) {
		String loginFlag = request.getParameter("login_flag");
		String userId = request.getParameter("user_id");
		System.out.println(userId);
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		List<Cultivation> list = cultivationDao.queryByUserId(Integer.parseInt(userId));
		if(list == null) {
			jsonObject.put("result", false);
			jsonObject.put("result_list", jsonArray);
		}else {
			jsonObject.put("result", true);
			for(Cultivation c : list) {
				System.out.println(c.toString());
				Detection detection = detectionDao.queryLastOneByDeviceId(c.getDeviceId());
				Plant plant = plantDao.queryById(c.getPlantId());
				Maxmoisture maxmoisture = maxmoistureDao.queryById(c.getSoilId());
				JSONObject object = new JSONObject();
				object.put("device_id", c.getDeviceId());
				object.put("serial", c.getSerial());
				object.put("user_id", c.getUserId());
				object.put("plant_id", c.getPlantId());
				object.put("plant_name", plant.getPlantName());
				object.put("pot_height", c.getPotHeight());
				object.put("pot_diameter", c.getPotDiameter());
				object.put("soil_quality", maxmoisture.getName());
				object.put("cultivation_name", c.getCultivationName());
				object.put("plant_height", c.getPlantHeight());
				object.put("soil_area", c.getSoilArea());
				if(detection == null) {
					object.put("air_temperature", 0.0);
					object.put("air_moisture",0.0);
				}else {
					object.put("air_temperature", detection.getAirTemperature());
					object.put("air_moisture",detection.getAirMoisture());
				}
				jsonArray.add(object);
			}
			jsonObject.put("result_list", jsonArray);
		}
		return jsonObject.toString();
	}
	
	@RequestMapping(value="/new", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody public String userNewPlants(HttpServletRequest request) {
		int deviceId = Integer.parseInt(request.getParameter("device_id"));
		int serial = Integer.parseInt(request.getParameter("serial"));
		int userId = Integer.parseInt(request.getParameter("user_id"));
		String plantName = request.getParameter("plant_name");
		double potHeight = Double.parseDouble(request.getParameter("pot_height"));
		double potDiameter = Double.parseDouble(request.getParameter("pot_diameter"));
		int soilQuality = Integer.parseInt(request.getParameter("soil_quality"));
		String cultivationName = request.getParameter("cultivation_name");
		double plantHeight = Double.parseDouble(request.getParameter("plant_height"));
		int soilArea = Integer.parseInt(request.getParameter("soil_area"));
		int plantId = plantDao.queryByName(plantName).getPlantId();
		Device device = deviceDao.queryById(deviceId);
		JSONObject jsonObject = new JSONObject();
		if(device == null) {//设备不存在，新建设备
			deviceDao.newDevice(deviceId, userId, 1);
			cultivationDao.newCultivation(deviceId, serial, userId, plantId, potHeight, potDiameter, soilQuality, cultivationName, plantHeight,soilArea);
			jsonObject.put("result", true);
			jsonObject.put("result_code", 0);
			return jsonObject.toString();
		}else if(device.getNumber() >= 4) {//串口已满
			jsonObject.put("result", false);
			jsonObject.put("result_code", 1);
		}else if(cultivationDao.queryBySerial(deviceId, serial) != null) {//串口占用
			jsonObject.put("result", false);
			jsonObject.put("result_code", 2);
		}else {//可新建
			cultivationDao.newCultivation(deviceId, serial, userId, plantId, potHeight, potDiameter, soilQuality, cultivationName, plantHeight,soilArea);
			deviceDao.increaseDeviceNumber(deviceId);
			jsonObject.put("result", true);
			jsonObject.put("result_code", 4);
		}
		return jsonObject.toString();
	}
	
	@RequestMapping(value="/change", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody public String userChangePlants(HttpServletRequest request) {
		int deviceId = Integer.parseInt(request.getParameter("device_id"));
		int serial = Integer.parseInt(request.getParameter("serial"));
		int userId = Integer.parseInt(request.getParameter("user_id"));
		String plantName = request.getParameter("plant_name");
		double potHeight = Double.parseDouble(request.getParameter("pot_height"));
		double potDiameter = Double.parseDouble(request.getParameter("pot_diameter"));
		int soilQuality = Integer.parseInt(request.getParameter("soil_quality"));
		String cultivationName = request.getParameter("cultivation_name");
		int plantId = plantDao.queryByName(plantName).getPlantId();
		double plantHeight = Double.parseDouble(request.getParameter("plant_height"));
		int soilArea = Integer.parseInt(request.getParameter("soil_area"));
		int result = cultivationDao.updateCultivation(deviceId, serial, userId, plantId, potHeight, potDiameter, soilQuality, cultivationName,plantHeight,soilArea);
		JSONObject jsonObject = new JSONObject();
		if(result == 1) {
			jsonObject.put("result", true);
		}else {
			jsonObject.put("result", false);
		}
		return jsonObject.toString();
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody public String userDeletePlants(HttpServletRequest request) {
		int deviceId = Integer.parseInt(request.getParameter("device_id"));
		int serial = Integer.parseInt(request.getParameter("serial"));
		int userId = Integer.parseInt(request.getParameter("user_id"));
		cultivationDao.deleteCultivation(deviceId, serial);
		deviceDao.decreaseDeviceNumber(deviceId);
		Device device = deviceDao.queryById(deviceId);
		if(device.getNumber() == 0) {//解绑设备
			deviceDao.updateUser(deviceId);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", true);
		return jsonObject.toString();
	}
	

}
