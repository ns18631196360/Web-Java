package org.zjn.myplant.test;

import java.net.URLEncoder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zjn.myplant.service.impl.AlgorithmServiceImpl;

import net.sf.json.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
	"classpath:spring/spring-service.xml"})

public class HttpClientServiceTest {
	@Autowired
	AlgorithmServiceImpl impl;
	
	@Test
	public void testHttp() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("LAST_WATER_TIME", 12);
		jsonObject.put("AT_MEAN", 22.23);
		jsonObject.put("AM_MEAN", 31.75);
		jsonObject.put("AT_VARIANCE", 0.03);
		jsonObject.put("AM_VARIANCE", 1.67);
		jsonObject.put("AT_SUM", 511.3);
		jsonObject.put("AM_SUM", 729.92);
		jsonObject.put("PLANT_KIND", 2);
		jsonObject.put("SOIL_KIND", 3);
		jsonObject.put("PLANT_VOLUME", 3);
		jsonObject.put("POT_VOLUME", 1);
		jsonObject.put("DBEFORE_SOIL_MOISTURE", 66);

		System.out.println(jsonObject.toString());
		String url = jsonObject.toString();
		//String url = "https://www.baidu.com/";
		try {
			String result = impl.httpGetData(URLEncoder.encode(jsonObject.toString(), "utf-8"));
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
