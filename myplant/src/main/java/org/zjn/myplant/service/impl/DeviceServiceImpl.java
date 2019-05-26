package org.zjn.myplant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zjn.myplant.dao.DeviceDao;
import org.zjn.myplant.service.*;

@Service
public class DeviceServiceImpl implements DeviceService{
	
	@Autowired
	DeviceDao deviceDao;

	@Override
	public int bindingDevice(int deviceId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int untiedDevice(int deviceId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
