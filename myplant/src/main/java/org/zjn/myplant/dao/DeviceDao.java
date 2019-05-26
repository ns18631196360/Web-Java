package org.zjn.myplant.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zjn.myplant.entity.Device;

public interface DeviceDao {
	//添加新设备
	int newDevice(@Param("deviceId")int deviceId, @Param("userId")int userId,@Param("number")int number);
	//查询
	Device queryById(int deviceId);
	//增加占用的串口数
	int increaseDeviceNumber(int deviceId);
	//减少占用的串口数
	int decreaseDeviceNumber(int deviceId);
	//查询所有设备
	List<Device> queryAll();
	//更新设备状态
	int updateUser(int deviceId);
	
	int bindingDevice(int deviceId, int userId);
	
	int untiedDevice(int deviceId);
}
