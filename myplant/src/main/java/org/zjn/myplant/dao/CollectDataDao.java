package org.zjn.myplant.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zjn.myplant.entity.CollectingData;

public interface CollectDataDao {
	//添加记录
	int newCollection(@Param("deviceId")int deviceId, @Param("plantId")int plantId, @Param("airTemperature")double airTemperature, @Param("airMoisture")double airMoisture, @Param("soilMoisture1")double soilMoisture1, @Param("soilMoisture2")double soilMoisture2, @Param("soilMoisture3")double soilMoisture3, @Param("soilMoisture4")double soilMoisture4, @Param("detectionTime")Date detectionTime);

	//查询记录
	List<CollectingData> queryByDeviceId(int deviceId);

}
