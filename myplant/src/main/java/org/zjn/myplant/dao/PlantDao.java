package org.zjn.myplant.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zjn.myplant.entity.Plant;

public interface PlantDao {
	//添加植物信息
	int newPlant(@Param("plantName")String plantName, @Param("temperatureMin")double temperatureMin,
			@Param("temperatureMax")double temperatureMax, @Param("moistureMin")double moistureMin,
			@Param("moistureMax")double moistureMax, @Param("flowerLanguage")String flowerLanguage,
			@Param("maintenanceKnowledge")String maintenanceKnowledge,@Param("waterPreference")int waterPreference);
	//查询植物信息
	Plant queryById(int plantId);
	//根据名称查询植物id
	Plant queryByName(String plantName);
	//查询全部植物信息
	List<Plant> queryAll();
	//删除植物
	int deletePlant(int plantId);
	//更新信息
	int updatePlant(@Param("plantId")int plantId, @Param("plantName")String plantName, 
			@Param("temperatureMin")double temperatureMin,
			@Param("temperatureMax")double temperatureMax, @Param("moistureMin")double moistureMin,
			@Param("moistureMax")double moistureMax, @Param("flowerLanguage")String flowerLanguage,
			@Param("maintenanceKnowledge")String maintenanceKnowledge,
			@Param("waterPreference")int waterPreference);

}
