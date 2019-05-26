package org.zjn.myplant.entity;

import java.util.Date;

public class Moisture {
	private int deviceId;
	private int serial;
	private double soilMoisture;
	private Date time;
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
	public double getSoilMoisture() {
		return soilMoisture;
	}
	public void setSoilMoisture(double soilMoisture) {
		this.soilMoisture = soilMoisture;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Moisture [deviceId=" + deviceId + ", serial=" + serial + ", soilMoisture=" + soilMoisture + ", time="
				+ time + "]";
	}

}
