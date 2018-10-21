package com.capitalone.weathertracker.resources.service;

import java.text.DecimalFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.capitalone.weathertracker.resources.models.Measurement;
import com.capitalone.weathertracker.resources.models.Statistics;
import com.capitalone.weathertracker.resources.repo.DataRepository;

public class StatisticsService {

	public List<Statistics> getStatistics(List<String> stats, List<String> metrics, ZonedDateTime fromDateTime, ZonedDateTime toDateTime) {

		List<Statistics> statList = new ArrayList<Statistics>();
		Statistics stat;

		Map<ZonedDateTime, Measurement> measurementMap = DataRepository.getMeasurements();

		List<Measurement> measurements = measurementMap.values().stream()
				.filter(e -> e.getZonedTimeStamp().isAfter(fromDateTime) || e.getZonedTimeStamp().isEqual(fromDateTime))
				.filter(e -> e.getZonedTimeStamp().isBefore(toDateTime)).collect(Collectors.toList());
		DecimalFormat df = new DecimalFormat("##.##");
		if (!measurements.isEmpty()) {

			for (String metric : metrics) {
				for (String stat1 : stats) {
					if (metric.equalsIgnoreCase("temperature")) {
						stat = new Statistics();
						if (stat1.equalsIgnoreCase("avg")) {
							String tempAvg = df.format(measurements.parallelStream()
									.mapToDouble(Measurement::getTemperature).average().getAsDouble());
							stat = new Statistics(metric, stat1, tempAvg);
							statList.add(stat);
						} else if (stat1.equalsIgnoreCase("min")) {
							String tempMin = df.format(measurements.parallelStream()
									.mapToDouble(Measurement::getTemperature).min().getAsDouble());
							stat = new Statistics(metric, stat1, tempMin);
							statList.add(stat);
						} else if (stat1.equalsIgnoreCase("max")) {
							String tempMax = df.format(measurements.parallelStream()
									.mapToDouble(Measurement::getTemperature).max().getAsDouble());
							stat = new Statistics(metric, stat1, tempMax);
							statList.add(stat);
						}
					} else if (metric.equalsIgnoreCase("dewPoint")) {
						stat = new Statistics();
						if (stat1.equalsIgnoreCase("avg")) {
							String dewPointAvg = df
									.format(measurements.parallelStream().filter(e -> e.getDewPoint() != null)
											.mapToDouble(Measurement::getDewPoint).average().getAsDouble());
							stat = new Statistics(metric, stat1, dewPointAvg);
							statList.add(stat);
						} else if (stat1.equalsIgnoreCase("min")) {
							String dewPointMin = df
									.format(measurements.parallelStream().filter(e -> e.getDewPoint() != null)
											.mapToDouble(Measurement::getDewPoint).min().getAsDouble());
							stat = new Statistics(metric, stat1, dewPointMin);
							statList.add(stat);
						} else if (stat1.equalsIgnoreCase("max")) {
							String dewPointMax = df
									.format(measurements.parallelStream().filter(e -> e.getDewPoint() != null)
											.mapToDouble(Measurement::getDewPoint).max().getAsDouble());
							stat = new Statistics(metric, stat1, dewPointMax);
							statList.add(stat);
						}
					} else if (metric.equalsIgnoreCase("precipitation")) {
						stat = new Statistics();
						if (stat1.equalsIgnoreCase("avg")) {
							try {
								Double precipitationAvg = measurements.parallelStream()
										.filter(e -> e.getPrecipitation() != null)
										.mapToDouble(Measurement::getPrecipitation).average().getAsDouble();
								if (precipitationAvg != 0) {
									stat = new Statistics(metric, stat1, precipitationAvg.toString());
									statList.add(stat);
								}
							} catch (NoSuchElementException nce) {
								System.out.println(nce);
							}
						} else if (stat1.equalsIgnoreCase("min")) {
							try {
								Double precipitationMin = measurements.parallelStream()
										.filter(e -> e.getPrecipitation() != null)
										.mapToDouble(Measurement::getPrecipitation).min().getAsDouble();
								if (precipitationMin != 0) {
									stat = new Statistics(metric, stat1, precipitationMin.toString());
									statList.add(stat);
								}
							} catch (NoSuchElementException nce) {
								System.out.println(nce);
							}
						} else if (stat1.equalsIgnoreCase("max")) {
							try {
								Double precipitationMax = measurements.parallelStream()
										.filter(e -> e.getPrecipitation() != null)
										.mapToDouble(Measurement::getPrecipitation).max().getAsDouble();
								if (precipitationMax != 0) {
									stat = new Statistics(metric, stat1, precipitationMax.toString());
									statList.add(stat);
								}
							} catch (NoSuchElementException nce) {
								System.out.println(nce);
							}
						}
					}
				}
			}

			return statList;

		}

		return null;

	}

}
