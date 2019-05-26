package org.zjn.myplant.entity;

public class Cultivation {
	private int deviceId;
	private int serial;
	private int userId;
	private int plantId;
	private double potHeight;
	private double potDiameter;
	private int soilId;
	private String cultivationName;
	private double plantHeight;
	private int soilArea;


	public int getSoilArea() {
		return soilArea;
	}
	public void setSoilArea(int soilArea) {
		this.soilArea = soilArea;
	}

	@Override
	public String toString() {
		return "Cultivation [deviceId=" + deviceId + ", serial=" + serial + ", userId=" + userId + ", plantId="
				+ plantId + ", potHeight=" + potHeight + ", potDiameter=" + potDiameter + ", soilId=" + soilId
				+ ", cultivationName=" + cultivationName + ", plantHeight=" + plantHeight + ", soilArea=" + soilArea
				+ "]";
	}
	public double getPotHeight() {
		return potHeight;
	}
	public void setPotHeight(double potHeight) {
		this.potHeight = potHeight;
	}
	public double getPotDiameter() {
		return potDiameter;
	}
	public void setPotDiameter(double potDiameter) {
		this.potDiameter = potDiameter;
	}
	public double getPlantHeight() {
		return plantHeight;
	}
	public void setPlantHeight(double plantHeight) {
		this.plantHeight = plantHeight;
	}
	public String getCultivationName() {
		return cultivationName;
	}
	public void setCultivationName(String cultivationName) {
		this.cultivationName = cultivationName;
	}
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPlantId() {
		return plantId;
	}
	public void setPlantId(int plantId) {
		this.plantId = plantId;
	}
	public int getSoilId() {
		return soilId;
	}
	public void setSoilId(int soilId) {
		this.soilId = soilId;
	}

	

	
	

}
