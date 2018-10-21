package com.capitalone.weathertracker.resources.models;

public class Statistics {

	public Statistics() {}
	
	public Statistics(String metric, String stat, String value) {
		super();
		this.metric = metric;
		this.stat = stat;
		this.value = value;
	}
	private String metric;
	private String stat;
	private String value;
	
	/**
	 * @return the stat
	 */
	public String getStat() {
		return stat;
	}
	/**
	 * @param stat the stat to set
	 */
	public void setStat(String stat) {
		this.stat = stat;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the metric
	 */
	public String getMetric() {
		return metric;
	}
	/**
	 * @param metric the metric to set
	 */
	public void setMetric(String metric) {
		this.metric = metric;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Statistics [metric=" + metric + ", stat=" + stat + ", value=" + value + "]";
	}
    
}
