package org.zjn.myplant.dao;

import org.apache.ibatis.annotations.Param;
import org.zjn.myplant.entity.Detection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface DetectionDao {
	//��Ӽ���¼
	int newDetection(@Param("deviceId")int deviceId, @Param("airTemperature")double airTemperature, @Param("airMoisture")double airMoisture, @Param("detectionTime")Date detectionTime);
	//��ѯ����¼
	List<Detection> queryByDeviceId(int deviceId);
	//��ѯ���µļ���¼
	Detection queryLastOneByDeviceId(int deviceId);
	//��ѯ��ȥһ��Ŀ����¶�
	ArrayList<Double> queryLastDayTemperature(int deviceId);
	//��ѯ��ȥһ��Ŀ���ʪ��
	ArrayList<Double> queryLastDayMoisture(int deviceId);

}
