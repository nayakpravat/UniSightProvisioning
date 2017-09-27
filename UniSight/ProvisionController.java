package com.unisight.provision.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ProvisionController {
	static Properties provisionProps = new Properties();

	public static ResponseEntity<Map> executeProvisioning(Map<String,String> paramMap, String inputSrcName){
		try {
			ObjectMapper mapperObj = new ObjectMapper();
			List<String> stttignsList =  createListOfProperties(inputSrcName,paramMap);
			String jsonResp = mapperObj.writeValueAsString(stttignsList);
			System.out.println(stttignsList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	private static List<String> createListOfProperties(String inputSrcName, Map<String, String> paramMap) throws IOException{
		
		InputStream inputStream = ProvisionController.class
				.getResourceAsStream("/"+inputSrcName+"_Settings.properties");
		provisionProps.load(inputStream);
		List<String> settingList = new ArrayList<String>();
		Set<Object> keySets = provisionProps.keySet();
		for(Object key :keySets){
			String keyStr = (String)key;
			String value = provisionProps.getProperty(keyStr);
			settingList.add(keyStr+ " => \""+ value+"\"");
			
		}
		
		Set<String> paramKeySets = paramMap.keySet();
		for(String key :paramKeySets){
			String value = provisionProps.getProperty(key);
			settingList.add(key+ " => \""+ value+"\"");
		}
		
		return settingList;
	}
	

}
