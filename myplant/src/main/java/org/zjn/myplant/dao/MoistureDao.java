package org.zjn.myplant.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

public interface MoistureDao {
	//查询上一天的土壤湿度
	double queryLastDayMoisture(@Param("deviceId")int deviceId, @Param("serial")int serial);
	
	//插入一条记录
	int insertMoisture(@Param("deviceId")int deviceId, @Param("serial")int serial, @Param("soilMoisture")double soilMoisture, @Param("time")Date time);
}
