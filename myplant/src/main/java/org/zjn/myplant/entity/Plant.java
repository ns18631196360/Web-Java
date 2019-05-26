package org.zjn.myplant.entity;

public class Plant {
	private int plantId;
	private String plantName;
	private double temperatureMin;
	private double temperatureMax;
	private double moistureMin;
	private double moistureMax;
	private String flowerLanguage;
	private String maintenanceKnowledge;
	private int waterPreference;
	public int getWaterPreference() {
		return waterPreference;
	}
	public void setWaterPreference(int waterPreference) {
		this.waterPreference = waterPreference;
	}
	public String getFlowerLanguage() {
		return flowerLanguage;
	}
	public void setFlowerLanguage(String flowerLanguage) {
		this.flowerLanguage = flowerLanguage;
	}
	public String getMaintenanceKnowledge() {
		return maintenanceKnowledge;
	}
	public void setMaintenanceKnowledge(String maintenanceKnowledge) {
		this.maintenanceKnowledge = maintenanceKnowledge;
	}
	public int getPlantId() {
		return plantId;
	}
	public void setPlantId(int plantId) {
		this.plantId = plantId;
	}
	public String getPlantName() {
		return plantName;
	}
	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}
	public double getTemperatureMin() {
		return temperatureMin;
	}
	public void setTemperatureMin(double temperatureMin) {
		this.temperatureMin = temperatureMin;
	}
	public double getTemperatureMax() {
		return temperatureMax;
	}
	public void setTemperatureMax(double temperatureMax) {
		this.temperatureMax = temperatureMax;
	}
	public double getMoistureMin() {
		return moistureMin;
	}
	public void setMoistureMin(double moistureMin) {
		this.moistureMin = moistureMin;
	}
	public double getMoistureMax() {
		return moistureMax;
	}
	public void setMoistureMax(double moistureMax) {
		this.moistureMax = moistureMax;
	}
	@Override
	public String toString() {
		return "Plant [plantId=" + plantId + ", plantName=" + plantName + ", temperatureMin=" + temperatureMin
				+ ", temperatureMax=" + temperatureMax + ", moistureMin=" + moistureMin + ", moistureMax=" + moistureMax
				+ ", flowerLanguage=" + flowerLanguage + ", maintenanceKnowledge=" + maintenanceKnowledge
				+ ", waterPreference=" + waterPreference + "]";
	}
	
	
	

}
