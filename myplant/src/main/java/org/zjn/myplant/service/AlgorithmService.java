package org.zjn.myplant.service;

import java.util.ArrayList;

public interface AlgorithmService {
	
	/*
	 * 每隔一小时检测空气参数
	 */
	void detectAirData();
	
	/*
	 * 预测每日的土壤湿度
	 */
	void predictMoisture();
	
	/*
	 * 预测每日的蒸散发量
	 */
	//double predictEvaporation(int deviceId, int serial);
	
	/*
	 * 控制浇水
	 */
	int water(int deviceId, int serial);
	
	/*
	 * 计算浇水量
	 */
	int calculateWaterVolume(int deviceId, int serial);
	

	double calculateSum(ArrayList<Double> list);
	
	double calculateVariance(ArrayList<Double> list);
	
	double calculateMean(ArrayList<Double> list);
	public String httpGetData(String url) throws Exception;
	
}
