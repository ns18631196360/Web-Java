package org.zjn.myplant.service;

import java.util.ArrayList;

public interface AlgorithmService {
	
	/*
	 * ÿ��һСʱ����������
	 */
	void detectAirData();
	
	/*
	 * Ԥ��ÿ�յ�����ʪ��
	 */
	void predictMoisture();
	
	/*
	 * Ԥ��ÿ�յ���ɢ����
	 */
	//double predictEvaporation(int deviceId, int serial);
	
	/*
	 * ���ƽ�ˮ
	 */
	int water(int deviceId, int serial);
	
	/*
	 * ���㽽ˮ��
	 */
	int calculateWaterVolume(int deviceId, int serial);
	

	double calculateSum(ArrayList<Double> list);
	
	double calculateVariance(ArrayList<Double> list);
	
	double calculateMean(ArrayList<Double> list);
	public String httpGetData(String url) throws Exception;
	
}
