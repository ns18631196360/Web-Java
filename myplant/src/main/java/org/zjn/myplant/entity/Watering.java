package org.zjn.myplant.entity;

import java.util.Date;

public class Watering {
	private int deviceId;
	private int serial;
	private int waterVolume;
	private Date waterTime;
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public int getSerial() {
		return serial;
	}
	public void setSerial(int serial) {
		this.serial = serial;
	}
	public int getWaterVolume() {
		return waterVolume;
	}
	public void setWaterVolume(int waterVolume) {
		this.waterVolume = waterVolume;
	}
	public Date getWaterTime() {
		return waterTime;
	}
	public void setWaterTime(Date waterTime) {
		this.waterTime = waterTime;
	}
	@Override
	public String toString() {
		return "Watering [deviceId=" + deviceId + ", serial=" + serial + ", waterVolume=" + waterVolume + ", waterTime="
				+ waterTime + "]";
	}
	

}
