package com.capitalone.weathertracker.resources.repo;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import com.capitalone.weathertracker.resources.models.Measurement;

public class DataRepository {
	
	private static Map<ZonedDateTime, Measurement> measurement = new HashMap<ZonedDateTime,Measurement>();
	
	public static Map<ZonedDateTime, Measurement> getMeasurements(){
		return measurement;
	}
}

