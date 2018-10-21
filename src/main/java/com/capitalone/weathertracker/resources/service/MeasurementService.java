package com.capitalone.weathertracker.resources.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.capitalone.weathertracker.resources.models.Measurement;
import com.capitalone.weathertracker.resources.repo.DataRepository;

public class MeasurementService {

	private Map<ZonedDateTime, Measurement> measurementMap = DataRepository.getMeasurements();

	/**
	 * Post Measurements to the dataBase
	 * 
	 * @param measurement
	 */
	public void saveMeasurement(Measurement measurement) {
		//System.out.println("Object value : " + measurement.getTimestamp());
		measurementMap.put(measurement.getZonedTimeStamp(), measurement);
	}
	
	public List<Measurement> getMeasurementByTimestamp(String timestamp) {
		List<Measurement> result = null;
		
		for (Entry<ZonedDateTime, Measurement> e : measurementMap.entrySet()) {
	        if (e.getKey().toString().startsWith(timestamp)) {
	        	
	            return null;
	        }
	    }
	    return null;
		/*try {
			try {
				ZonedDateTime dts = ZonedDateTime.parse(timestamp).withZoneSameInstant(ZoneOffset.UTC);
				System.out.println("dts:" + dts);
				result = measurementMap.values().stream().filter(e -> e.getZonedTimeStamp().equals(dts)).collect(Collectors.toList());
				
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			try {
				LocalDate ld = LocalDate.parse(timestamp);
				System.out.println("ld:" + ld);
				result = measurementMap.values().stream().filter(e -> e.getZonedTimeStamp().getDayOfMonth() == (ld.getDayOfMonth())).collect(Collectors.toList());
				
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}*/
		//return result;
	}
	
	/**
	 * features/01-measurements/03-update-measurement.feature
	 * 
	 * @PUT @Path("/measurements/{timestamp}")
	 */
	public Measurement replaceMeasurement(String timestamp, Measurement newData) {
		
		ZonedDateTime zdt = ZonedDateTime.parse(timestamp);
		
		Measurement mst = null;
		if (measurementMap.get(zdt) != null) {
			measurementMap.put(zdt, newData);
			mst = measurementMap.get(zdt);
		}
		
		return mst;
	}
	
	/**
	 * DELETE /measurements/:timestamp
	 * 
	 * @RequestBody:timestamp
	 * @RrsponseBody none
	 */
	public boolean deleteMeasurement(String timestamp) {
		ZonedDateTime zdt = ZonedDateTime.parse(timestamp);
		if(measurementMap.get(zdt) != null){
			measurementMap.remove(zdt);
			return true;
		}
		return false;
	}
}
