package org.zjn.myplant.entity;

public class Device {
	private int deviceId;
	private int userId;
	private int number;
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "Device [deviceId=" + deviceId + ", userId=" + userId + ", number=" + number + "]";
	}
	

	

}
