package org.zjn.myplant.dao;

import org.apache.ibatis.annotations.Param;
import org.zjn.myplant.entity.Detection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface DetectionDao {
	//添加检测记录
	int newDetection(@Param("deviceId")int deviceId, @Param("airTemperature")double airTemperature, @Param("airMoisture")double airMoisture, @Param("detectionTime")Date detectionTime);
	//查询检测记录
	List<Detection> queryByDeviceId(int deviceId);
	//查询最新的检测记录
	Detection queryLastOneByDeviceId(int deviceId);
	//查询过去一天的空气温度
	ArrayList<Double> queryLastDayTemperature(int deviceId);
	//查询过去一天的空气湿度
	ArrayList<Double> queryLastDayMoisture(int deviceId);

}
