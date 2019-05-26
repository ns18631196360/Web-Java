package org.zjn.myplant.entity;

public class Maxmoisture {
	private int soilId;
	private int soilQuality;
	private String name;
	private double maxMoisture;
	private String soilQualityName;
	public int getSoilId() {
		return soilId;
	}
	public void setSoilId(int soilId) {
		this.soilId = soilId;
	}
	public String getSoilQualityName() {
		return soilQualityName;
	}
	public void setSoilQualityName(String soilQualityName) {
		this.soilQualityName = soilQualityName;
	}
	public int getSoilQuality() {
		return soilQuality;
	}
	public void setSoilQuality(int soilQuality) {
		this.soilQuality = soilQuality;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getMaxMoisture() {
		return maxMoisture;
	}
	public void setMaxMoisture(double maxMoisture) {
		this.maxMoisture = maxMoisture;
	}
	@Override
	public String toString() {
		return "Maxmoisture [soilId=" + soilId + ", soilQuality=" + soilQuality + ", name=" + name + ", maxMoisture="
				+ maxMoisture + ", soilQualityName=" + soilQualityName + "]";
	}
	
	

}
