package org.zjn.myplant.entity;

import java.util.Date;

public class Detection {
	private int deviceId;
	private double airTemperature;
	private double airMoisture;
	private Date detectionTime;
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public double getAirTemperature() {
		return airTemperature;
	}
	public void setAirTemperature(double airTemperature) {
		this.airTemperature = airTemperature;
	}
	public double getAirMoisture() {
		return airMoisture;
	}
	public void setAirMoisture(double airMoisture) {
		this.airMoisture = airMoisture;
	}
	public Date getDetectionTime() {
		return detectionTime;
	}
	public void setDetectionTime(Date detectionTime) {
		this.detectionTime = detectionTime;
	}
	@Override
	public String toString() {
		return "Detection [deviceId=" + deviceId + ", airTemperature=" + airTemperature + ", airMoisture=" + airMoisture
				+ ", detectionTime=" + detectionTime + "]";
	}

	

}
