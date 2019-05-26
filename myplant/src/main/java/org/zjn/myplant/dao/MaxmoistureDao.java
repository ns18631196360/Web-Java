package org.zjn.myplant.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zjn.myplant.entity.Maxmoisture;

public interface MaxmoistureDao {
	int newMaxmoisture(@Param("soilQuality")int soilQuality, @Param("name")String name, @Param("maxMoisture")double maxMoisture, @Param("soilQualityName")String soilQualityName);

	List<Maxmoisture> queryAll();
	
	int deleteMaxmoisture(@Param("soilId")int soilId);
	
	double queryMaxMoisture(@Param("soilId")int soilId);
	
	Maxmoisture queryById(@Param("soilId")int soilId);
	
	String queryName(@Param("soilId")int soilId);
}
