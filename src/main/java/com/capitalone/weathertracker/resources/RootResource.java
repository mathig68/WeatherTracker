package com.capitalone.weathertracker.resources;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.capitalone.weathertracker.annotations.PATCH;
import com.capitalone.weathertracker.resources.models.Measurement;
import com.capitalone.weathertracker.resources.models.Statistics;
import com.capitalone.weathertracker.resources.service.MeasurementService;
import com.capitalone.weathertracker.resources.service.StatisticsService;
import com.capitalone.weathertracker.resources.util.ValidationService;

/*
  TODO: Implement the endpoints in the ATs.
  The below stubs are provided as a starting point.
  You may refactor them however you like, so long as the resources are defined
  in the `com.capitalone.weathertracker.resources` package.
*/

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RootResource {
	private static final Response NOT_IMPLEMENTED = Response.status(501).entity("").build();
	private static final Response STATUS_OK = Response.status(201).entity("").build();
	private static final Response BAD_REQUEST = Response.status(400).entity("").build();
	private static final Response NOT_FOUND = Response.status(404).entity("").build();
	private static final Response NO_CONTENT = Response.status(204).entity("").build();
	private static final Response CONFLICT = Response.status(409).entity("").build();

	MeasurementService measurementService = new MeasurementService();
	Measurement measurement = new Measurement();

	// dummy handler so you can tell if the server is running
	// e.g. `curl localhost:8000`
	@GET
	public Response get() {
		return Response.ok("Weather tracker is up and running!\n").build();
	}

	// features/01-measurements/01-add-measurement.feature
	@POST
	@Path("/measurements")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createMeasurement(Measurement measurement) {
		try {
			if (measurement != null) {
				if (ValidationService.doValidateInput(measurement)) {
					measurementService.saveMeasurement(measurement);
				} else
					return Response.status(400).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Response.ok().header("Location", "api/tasks").status(201).build();
		return Response.status(200).entity(measurement).build();
	}

	// features/01-measurements/02-get-measurement.feature
	@GET
	@Path("/measurements/{timestamp}") // checking input date patter
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMeasurement(@PathParam("timestamp") String timestamp) throws ParseException {

		List<Measurement> measurementsList = new ArrayList<Measurement>();
		measurementsList = measurementService.getMeasurementByTimestamp(timestamp);
		System.out.println(" measurementsList.size() : " + measurementsList.size());

		if (measurementsList.size() <= 0) {
			return NOT_FOUND;
		}
		return Response.ok(measurementsList, MediaType.APPLICATION_JSON).build();

	}

	// features/01-measurements/03-update-measurement.feature
	@PUT
	@Path("/measurements/{timestamp}")
	public Response replaceMeasurement(@PathParam("timestamp") String timestamp, Measurement measurement) {
		try {
			// When try to replace with empty values
			if (measurement != null && !timestamp.isEmpty()) {
				if (ValidationService.doValidateInput(measurement)) {
					// When try replace the measurement for wrong timestamp
					if (!measurement.getTimestamp().equals(timestamp)) {
						return CONFLICT;
					} else {
						Measurement mst = measurementService.replaceMeasurement(timestamp, measurement);
						if (mst != null) {
							return NO_CONTENT;
						} else {
							return NOT_FOUND;
						}
					}

				} else {
					return BAD_REQUEST;
				}
			}
			return BAD_REQUEST;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return BAD_REQUEST;
	}

	// features/01-measurements/03-update-measurement.feature
	
	@PATCH @Path("/measurements/{timestamp}")
	public Response updateMeasurement(@PathParam("timestamp") String timestamp, Measurement measurement) {
		/*
		 * Example: timestamp := "2015-09-01T16:20:00.000Z"
		 * 
		 * measurement := { "timestamp": "2015-09-01T16:00:00.000Z", "precipitation":
		 * 15.2 }
		 */
		return NOT_IMPLEMENTED;
	}

	// features/01-measurements/04-delete-measurement.feature
	@DELETE
	@Path("/measurements/{timestamp}")
	public Response deleteMeasurement(@PathParam("timestamp") String timestamp) {
		boolean status = measurementService.deleteMeasurement(timestamp);
		if (status) {
			return NO_CONTENT;
		}
		return NOT_FOUND;
	}

	@GET @Path("/stats")
    public Response getStats(@QueryParam("metric") List<String> metrics, @QueryParam("stat") List<String> stats,  @QueryParam("fromDateTime") String fromDateTime,  @QueryParam("toDateTime") String toDateTime ) {
      StatisticsService ss = new StatisticsService();
      
      System.out.println("fromdate:" + fromDateTime + ", todate:" + toDateTime);
      List<Statistics> sList = ss.getStatistics(stats, metrics, ZonedDateTime.parse(fromDateTime),  ZonedDateTime.parse(toDateTime));
      System.out.println(sList);

      Response response = Response.status(Status.OK).entity(sList).build();
       return response;
    }
}
