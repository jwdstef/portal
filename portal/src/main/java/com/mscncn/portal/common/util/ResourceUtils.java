package com.mscncn.portal.common.util;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ResourceUtils {
	private static Logger logger=LoggerFactory.getLogger(ResourceUtils.class);
	public static String getXMLValue(String key,String filePath){
		String value=null;
		XMLConfiguration config=null;
		try {
			config = new XMLConfiguration(filePath);
		} catch (ConfigurationException e) {
			logger.error("解析xml文件出错,文件路径名为{}",filePath,e);
		}
		if(null!=config)
			value=config.getString(key);
		return value;
	}
	
}
