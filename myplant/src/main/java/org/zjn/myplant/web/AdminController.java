package org.zjn.myplant.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

//import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.zjn.myplant.dao.PlantDao;
import org.zjn.myplant.dao.UserDao;
import org.zjn.myplant.entity.Plant;
import org.zjn.myplant.entity.User;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
	@Autowired
	PlantDao plantDao;
	@Autowired
	UserDao userDao;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String list(ModelMap model) {
		//model.addAttribute("greeting", "Hello World Again, from Spring 4 MVC");
		//int result = detectionDao.newDetection(2, 1, 777, 999, 888, new Date());
		System.out.println("can redirect");
		return "adminlogin";
	}
	
	@RequestMapping(value="/adminloginform")
	public ModelAndView adminLogin(HttpServletRequest req, Model model) {
		String username, password;
		username = req.getParameter("username");
		password = req.getParameter("password");
		ModelAndView modelAndView = new ModelAndView();
		if(username.equals("admin") && password.equals("adminPasswd")) {
			List<User> userList = userDao.queryAll();
			modelAndView.setViewName("userlist");
			modelAndView.addObject("userList", userList);
			return modelAndView;
		}else if(username.equals("databaseAdmin") && password.equals("databaseAdminPasswd")){
			modelAndView.setViewName("plantlist");
			List<Plant> plantList = plantDao.queryAll();
			modelAndView.addObject("plantList", plantList);
			return modelAndView;
		}else {
			modelAndView.setViewName("adminlogin");
			return modelAndView;
		}
	}
	@RequestMapping(value="/changeplant")
	public ModelAndView changePlant(HttpServletRequest req, Model model) {
		int plantId = Integer.parseInt(req.getParameter("plantId"));
		Plant plant = plantDao.queryById(plantId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("changeplant");
		modelAndView.addObject("plant", plant);
		return modelAndView;
	}
	
	@RequestMapping(value="/newplant")
	public ModelAndView newPlant(HttpServletRequest req, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("newplant");
		return modelAndView;
	}
	
	@RequestMapping(value="/deleteplant")
	public ModelAndView deletePlant(HttpServletRequest req, Model model) {
		int plantId = Integer.parseInt(req.getParameter("plantId"));
		plantDao.deletePlant(plantId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("plantlist");
		List<Plant> plantList = plantDao.queryAll();
		modelAndView.addObject("plantList", plantList);
		return modelAndView;
	}
	
	@RequestMapping(value="/newplantform")
	public ModelAndView newPlantForm(HttpServletRequest req, Model model) {
		String plantName = req.getParameter("plantName");
		double temperatureMin = Double.parseDouble(req.getParameter("temperatureMin"));
		double temperatureMax = Double.parseDouble(req.getParameter("temperatureMax"));
		double moistureMin = Double.parseDouble(req.getParameter("moistureMin"));
		double moistureMax = Double.parseDouble(req.getParameter("moistureMax"));
		String flowerLanguage = req.getParameter("flowerLanguage");
		String maintenanceKnowledge = req.getParameter("maintenanceKnowledge");
		int waterPreference = Integer.parseInt(req.getParameter("waterPreference"));
		plantDao.newPlant(plantName, temperatureMin, temperatureMax, moistureMin, moistureMax, flowerLanguage, maintenanceKnowledge,waterPreference);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("plantlist");
		List<Plant> plantList = plantDao.queryAll();
		modelAndView.addObject("plantList", plantList);
		return modelAndView;
	}
	
	@RequestMapping(value="/changeplantform")
	public ModelAndView changePlantForm(HttpServletRequest req, Model model) {
		int plantId = Integer.parseInt(req.getParameter("plantId"));
		String plantName = req.getParameter("plantName");
		double temperatureMin = Double.parseDouble(req.getParameter("temperatureMin"));
		double temperatureMax = Double.parseDouble(req.getParameter("temperatureMax"));
		double moistureMin = Double.parseDouble(req.getParameter("moistureMin"));
		double moistureMax = Double.parseDouble(req.getParameter("moistureMax"));
		String flowerLanguage = req.getParameter("flowerLanguage");
		String maintenanceKnowledge = req.getParameter("maintenanceKnowledge");
		int waterPreference = Integer.parseInt(req.getParameter("waterPreference"));
		plantDao.updatePlant(plantId, plantName, temperatureMin, temperatureMax, moistureMin, moistureMax, flowerLanguage, maintenanceKnowledge, waterPreference);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("plantlist");
		List<Plant> plantList = plantDao.queryAll();
		modelAndView.addObject("plantList", plantList);
		return modelAndView;
	}
	
	@RequestMapping(value="/deleteuser")
	public ModelAndView deleteUser(HttpServletRequest req, Model model) {
		int userId = Integer.parseInt(req.getParameter("userId"));
		userDao.deleteUser(userId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("userlist");
		List<User> userList = userDao.queryAll();
		modelAndView.addObject("userList", userList);
		return modelAndView;
	}

}
