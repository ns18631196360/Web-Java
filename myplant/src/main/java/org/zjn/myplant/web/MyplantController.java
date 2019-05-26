package org.zjn.myplant.web;


import java.util.Date;

//import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zjn.myplant.dao.DetectionDao;
import org.zjn.myplant.service.impl.MqttInboundServiceImpl;

//将当前controller放入spring容器中
//url:/模块/资源/{id}/细分 /seckill/list
@Controller
@RequestMapping(value="/")
public class MyplantController {
	@Autowired
	MqttInboundServiceImpl serviceimpl;
	@Autowired
	DetectionDao detectionDao;
//	@RequestMapping(value="/adminlogin", method = RequestMethod.GET)
//	public String list(ModelMap model) {
//		//model.addAttribute("greeting", "Hello World Again, from Spring 4 MVC");
//		//int result = detectionDao.newDetection(2, 1, 777, 999, 888, new Date());
//		System.out.println("can redirect");
//		return "userlist";
//	}
//	
//	@RequestMapping(value="/adminloginform")
//	public String adminLogin(HttpServletRequest req, Model model) {
//		String username, password;
//		username = req.getParameter("username");
//		password = req.getParameter("password");
//		if(username.equals("a") &&password.equals("aa")) {
//			return "plantlist";
//		}else if(username.equals("b") && password.equals("bb")){
//			return "userlist";
//		}else {
//			return "index";
//		}
//	}
}
