package org.zjn.myplant.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zjn.myplant.entity.Cultivation;

public interface CultivationDao {
	
	int newCultivation(@Param("deviceId")int deviceId, @Param("serial")int serial, 
			@Param("userId")int userId, @Param("plantId")int plantId, 
			@Param("potHeight")double potHeight, @Param("potDiameter")double potDiameter,
			@Param("soilId")int soilId,
			@Param("cultivationName")String cultivationName, @Param("plantHeight")double plantHeight,
			@Param("soilArea")int soilArea);

	Cultivation queryBySerial(@Param("deviceId")int deviceId, @Param("serial")int serial);
	
	List<Cultivation> queryByUserId(int userId);
	
	int updateCultivation(@Param("deviceId")int deviceId, @Param("serial")int serial, 
			@Param("userId")int userId, @Param("plantId")int plantId, 
			@Param("potHeight")double potHeight, @Param("potDiameter")double potDiameter,
			@Param("soilId")int soilId,
			@Param("cultivationName")String cultivationName, @Param("plantHeight")double plantHeight,
			@Param("soilArea")int soilArea);
	
	List<Cultivation> queryAll();
	
	int deleteCultivation(@Param("deviceId")int deviceId, @Param("serial")int serial);

}
