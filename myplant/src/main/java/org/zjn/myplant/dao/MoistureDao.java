package org.zjn.myplant.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

public interface MoistureDao {
	//��ѯ��һ�������ʪ��
	double queryLastDayMoisture(@Param("deviceId")int deviceId, @Param("serial")int serial);
	
	//����һ����¼
	int insertMoisture(@Param("deviceId")int deviceId, @Param("serial")int serial, @Param("soilMoisture")double soilMoisture, @Param("time")Date time);
}
