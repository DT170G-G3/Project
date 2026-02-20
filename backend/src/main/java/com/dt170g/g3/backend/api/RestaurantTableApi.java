/**
 * RestaurantTableApi.java
 *
 * REST API resource for managing restaurant tables.
 * Provides endpoints to:
 *  - GET /table           : Retrieve all restaurant tables
 *  - GET /table/{id}      : Retrieve a specific table by its ID
 *
 * Uses RestaurantTableService to fetch table data from the database.
 * All responses are returned as JSON.
 *
 * Example usage:
 *   GET http://localhost:8080/restaurant/api/table
 *   GET http://localhost:8080/restaurant/api/table/1
 *
 * Author: Axel Friman
 * Date: 2026-02-20
 */
package com.dt170g.g3.backend;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import com.dt170g.g3.backend.services.RestaurantTableService;
import com.dt170g.g3.backend.entities.RestaurantTable;
import java.util.List;


@Path("/table")
public class RestaurantTableApi {
    @Inject
    private RestaurantTableService tableHandler;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RestaurantTable> getAllTables() {
        return tableHandler.findAllTables();
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestaurantTable getTableById(@PathParam("id") int id) {
        return tableHandler.getTableById(id);
    }
}