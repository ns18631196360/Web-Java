package org.zjn.myplant.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zjn.myplant.entity.Plant;

public interface PlantDao {
	//���ֲ����Ϣ
	int newPlant(@Param("plantName")String plantName, @Param("temperatureMin")double temperatureMin,
			@Param("temperatureMax")double temperatureMax, @Param("moistureMin")double moistureMin,
			@Param("moistureMax")double moistureMax, @Param("flowerLanguage")String flowerLanguage,
			@Param("maintenanceKnowledge")String maintenanceKnowledge,@Param("waterPreference")int waterPreference);
	//��ѯֲ����Ϣ
	Plant queryById(int plantId);
	//�������Ʋ�ѯֲ��id
	Plant queryByName(String plantName);
	//��ѯȫ��ֲ����Ϣ
	List<Plant> queryAll();
	//ɾ��ֲ��
	int deletePlant(int plantId);
	//������Ϣ
	int updatePlant(@Param("plantId")int plantId, @Param("plantName")String plantName, 
			@Param("temperatureMin")double temperatureMin,
			@Param("temperatureMax")double temperatureMax, @Param("moistureMin")double moistureMin,
			@Param("moistureMax")double moistureMax, @Param("flowerLanguage")String flowerLanguage,
			@Param("maintenanceKnowledge")String maintenanceKnowledge,
			@Param("waterPreference")int waterPreference);

}
