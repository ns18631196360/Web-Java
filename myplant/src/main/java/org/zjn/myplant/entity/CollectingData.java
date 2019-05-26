package org.zjn.myplant.entity;

import java.util.Date;

public class CollectingData {
	private int deviceId;
	private int plantId;
	private double airTemperature;
	private double airMoisture;
	private double soilMoisture1;
	private double soilMoisture2;
	private double soilMoisture3;
	private double soilMoisture4;
	private Date detectionTime;
	
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public int getPlantId() {
		return plantId;
	}
	public void setPlantId(int plantId) {
		this.plantId = plantId;
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
	public double getSoilMoisture1() {
		return soilMoisture1;
	}
	public void setSoilMoisture1(double soilMoisture1) {
		this.soilMoisture1 = soilMoisture1;
	}
	public double getSoilMoisture2() {
		return soilMoisture2;
	}
	public void setSoilMoisture2(double soilMoisture2) {
		this.soilMoisture2 = soilMoisture2;
	}
	public double getSoilMoisture3() {
		return soilMoisture3;
	}
	public void setSoilMoisture3(double soilMoisture3) {
		this.soilMoisture3 = soilMoisture3;
	}
	public double getSoilMoisture4() {
		return soilMoisture4;
	}
	public void setSoilMoisture4(double soilMoisture4) {
		this.soilMoisture4 = soilMoisture4;
	}
	public Date getDetectionTime() {
		return detectionTime;
	}
	public void setDetectionTime(Date detectionTime) {
		this.detectionTime = detectionTime;
	}
	@Override
	public String toString() {
		return "CollectingData [deviceId=" + deviceId + ", plantId=" + plantId + ", airTemperature=" + airTemperature
				+ ", airMoisture=" + airMoisture + ", soilMoisture1=" + soilMoisture1 + ", soilMoisture2="
				+ soilMoisture2 + ", soilMoisture3=" + soilMoisture3 + ", soilMoisture4=" + soilMoisture4
				+ ", detectionTime=" + detectionTime + "]";
	}
	

}
