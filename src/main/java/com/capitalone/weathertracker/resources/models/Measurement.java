package com.capitalone.weathertracker.resources.models;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Measurement {

	public Measurement() {}
	
	public Measurement(String timestamp, Double temperature, Double dewPoint, Double precipitation) {
		super();
		this.timestamp = timestamp;
		this.temperature = temperature;
		this.dewPoint = dewPoint;
		this.precipitation = precipitation;
		this.zonedTimeStamp = ZonedDateTime.parse(timestamp).withZoneSameInstant(ZoneOffset.UTC);
	}
	
	private String timestamp;
	private Double temperature;
	private Double dewPoint;
	private Double precipitation;
	
	@JsonIgnore
	private ZonedDateTime zonedTimeStamp;
	
	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
		setZonedTimeStamp(ZonedDateTime.parse(timestamp));
	}
	/**
	 * @return the temperature
	 */
	public Double getTemperature() {
		return temperature;
	}
	/**
	 * @param temperature the temperature to set
	 */
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	/**
	 * @return the dewPoint
	 */
	public Double getDewPoint() {
		return dewPoint;
	}
	/**
	 * @param dewPoint the dewPoint to set
	 */
	public void setDewPoint(Double dewPoint) {
		this.dewPoint = dewPoint;
	}
	/**
	 * @return the precipitation
	 */
	public Double getPrecipitation() {
		return precipitation;
	}
	/**
	 * @param precipitation the precipitation to set
	 */
	public void setPrecipitation(Double precipitation) {
		this.precipitation = precipitation;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Measurement [timestamp=" + timestamp + ", temperature=" + temperature + ", dewPoint=" + dewPoint
				+ ", precipitation=" + precipitation + "]";
	}

	/**
	 * @return the ts
	 */
	public ZonedDateTime getZonedTimeStamp() {
		return zonedTimeStamp;
	}

	/**
	 * @param ts the ts to set
	 */
	public void setZonedTimeStamp(ZonedDateTime ts) {
		this.zonedTimeStamp = ts;
	}
	

	
}
