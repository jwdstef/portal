package com.mscncn.portal.common.util;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

public class PropertiesUtils {
	public PropertiesUtils() {
		try {
			XMLConfiguration config = new XMLConfiguration("config.xml");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
}
