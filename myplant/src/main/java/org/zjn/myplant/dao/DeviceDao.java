package org.zjn.myplant.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zjn.myplant.entity.Device;

public interface DeviceDao {
	//������豸
	int newDevice(@Param("deviceId")int deviceId, @Param("userId")int userId,@Param("number")int number);
	//��ѯ
	Device queryById(int deviceId);
	//����ռ�õĴ�����
	int increaseDeviceNumber(int deviceId);
	//����ռ�õĴ�����
	int decreaseDeviceNumber(int deviceId);
	//��ѯ�����豸
	List<Device> queryAll();
	//�����豸״̬
	int updateUser(int deviceId);
	
	int bindingDevice(int deviceId, int userId);
	
	int untiedDevice(int deviceId);
}
