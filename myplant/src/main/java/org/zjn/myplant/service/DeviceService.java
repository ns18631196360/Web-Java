package org.zjn.myplant.service;

public interface DeviceService {
	
	/*
	 * ��������������Arduino������
	 */
	int bindingDevice(int deviceId);
	
	/*
	 * ��������Arduino����ָ��
	 */
	int untiedDevice(int deviceId);

}
