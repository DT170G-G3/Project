/**
 * SittingApi.java
 *
 * REST API resource for managing Sitting entities.
 * Provides endpoints to:
 *  - GET /sitting                 : Retrieve all sittings
 *  - GET /sitting/id/{id}         : Retrieve a single sitting by its ID
 *  - GET /sitting/date/{date}     : Retrieve sittings for a specific date
 *  - GET /sitting/table/id/{id}   : Retrieve sittings for a specific table
 *  - POST /sitting/add            : Creates a new sitting from the provided JSON payload
 *
 * Uses SittingService to interact with the database.
 * All responses are returned in JSON format.
 *
 * Example usage:
 *   GET http://localhost:8080/restaurant/api/sitting
 *   GET http://localhost:8080/restaurant/api/sitting/id/1
 *   GET http://localhost:8080/restaurant/api/sitting/date/2026-02-26
 *   GET http://localhost:8080/restaurant/api/sitting/table/id/3
 *
 * Author: Axel Friman
 * Date: 2026-02-26
 */
package com.dt170g.g3.backend;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import com.dt170g.g3.backend.entities.Sitting;
import com.dt170g.g3.backend.services.SittingService;
import java.time.LocalDate;
import java.util.List;

@Path("/sitting")
public class SittingApi {
    @Inject
    private SittingService sittingHandler;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sitting> getAllSittings() {
        return sittingHandler.findAllSittings();
    }
    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Sitting getSittingById(@PathParam("id") int id) {
        return sittingHandler.getSittingById(id);
    }

    @GET
    @Path("/date/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sitting> getSittingsByDate(@PathParam("date") String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        return sittingHandler.findSittingsByDate(parsedDate);
    }

    @GET
    @Path("/table/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sitting> getSittingsByTable(@PathParam("id") int id) {
        return sittingHandler.findSittingsByTable(id);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSitting(Sitting s) {
        try {
            sittingHandler.addSitting(s);
            return Response.status(Response.Status.CREATED).entity(s).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to create sitting: " + e.getMessage())
                    .build();
        }
    }

}