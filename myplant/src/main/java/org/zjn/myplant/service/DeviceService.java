package org.zjn.myplant.service;

public interface DeviceService {
	
	/*
	 * 服务器接收来自Arduino的数据
	 */
	int bindingDevice(int deviceId);
	
	/*
	 * 服务器向Arduino发送指令
	 */
	int untiedDevice(int deviceId);

}
