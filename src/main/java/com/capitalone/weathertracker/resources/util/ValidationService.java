package com.capitalone.weathertracker.resources.util;

import com.capitalone.weathertracker.resources.models.Measurement;

public class ValidationService {
	public static  boolean doValidateInput(Measurement measurement) {
		if (measurement.getTimestamp() == null || measurement.getTimestamp().trim().length() <= 0
				|| measurement.getTemperature() == null || measurement.getTemperature() < 0 || 
				measurement.getDewPoint() == null ||  measurement.getDewPoint() < 0  ||
				measurement.getPrecipitation() == null || measurement.getPrecipitation() < 0 ){
			return false;
		}
		return true;
	}

	
}
