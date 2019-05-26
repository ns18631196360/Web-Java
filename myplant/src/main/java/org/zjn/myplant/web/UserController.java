package org.zjn.myplant.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zjn.myplant.dao.UserDao;
import org.zjn.myplant.entity.User;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	UserDao userDao;
	
	@RequestMapping(value="/login", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody public String userLogin(HttpServletRequest request) {
		String userPhone = request.getParameter("user_phone");
		String userPasswd = request.getParameter("user_passwd");
		System.out.println(userPhone);
		System.out.println(userPasswd);
		User myUser = userDao.queryByPhone(userPhone);
		JSONObject jsonObject = new JSONObject();
		if(myUser != null && myUser.getUserPasswd().equals(userPasswd)) {//登录验证通过
			jsonObject.put("result", true);
			jsonObject.put("user_name", myUser.getUserName());
			jsonObject.put("user_Id", myUser.getUserId());
			jsonObject.put("user_phone", myUser.getUserPhone());
			jsonObject.put("user_sex", myUser.getUserSex());
			
		}else {
			jsonObject.put("result", false);
			jsonObject.put("user_name", "");
			jsonObject.put("user_Id", 0);
			jsonObject.put("user_phone", "");
			jsonObject.put("user_sex", -1);
		}
		System.out.println(jsonObject.toString());
		return jsonObject.toString();
		
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody public String userRegister(HttpServletRequest request) {
		String userPhone = request.getParameter("user_phone");
		String userName = request.getParameter("user_name");
		String userPasswd = request.getParameter("user_passwd");
		String userSex = request.getParameter("user_sex");
		User user = userDao.queryByPhone(userPhone);
		JSONObject jsonObject = new JSONObject();
		if(user == null) {
			userDao.newUser(userName, userPasswd, userPhone, Integer.parseInt(userSex));
			jsonObject.put("result", true);
			System.out.println(jsonObject.toString());
			return jsonObject.toString();
		}else {
			jsonObject.put("result", false);
			System.out.println(jsonObject.toString());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/password", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody public String userChangePassword(HttpServletRequest request) {
		int userId = Integer.parseInt(request.getParameter("user_id"));
		String userPassword = request.getParameter("user_passwd");
		userDao.changePassword(userId, userPassword);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", true);
		return jsonObject.toString();
	}

}
