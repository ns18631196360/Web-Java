package org.zjn.myplant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zjn.myplant.dao.UserDao;
import org.zjn.myplant.service.*;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;

	@Override
	public int userRegister(String userName, String userPasswd) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int userLogin(String userName, String userPasswd) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int userRegisterDevice(int deviceId, int plantId, int userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int queryUserId(String userName) {
		// TODO Auto-generated method stub
		return 0;
	}

}
