package org.zjn.myplant.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zjn.myplant.dao.PlantDao;
import org.zjn.myplant.entity.Plant;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/allplants")
public class AllPlantsController {
	@Autowired
	PlantDao plantDao;
	
	@RequestMapping(value="/queryall", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody public String queryAllPlants(HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		List<Plant> list = plantDao.queryAll();
		for(Plant p : list) {
			System.out.println(p.toString());
			JSONObject object = new JSONObject();
			object.put("plant_id", p.getPlantId());
			object.put("plant_name", p.getPlantName());
			object.put("temperature_min", p.getTemperatureMin());
			object.put("temperature_max", p.getTemperatureMax());
			object.put("moisture_min", p.getMoistureMin());
			object.put("moisture_max", p.getMoistureMax());
			object.put("flower_language", p.getFlowerLanguage());
			object.put("maintenance_knowledge", p.getMaintenanceKnowledge());
			object.put("water_preference", p.getWaterPreference());
			jsonArray.add(object);
		}
		jsonObject.put("result_list", jsonArray);
		return jsonObject.toString();
	}

}
