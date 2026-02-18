/**
 * DishApi.java
 *
 * REST API resource for managing Dish entities.
 * Provides endpoints to:
 *  - Retrieve all dishes (GET /dish)
 *  - Retrieve a single dish by ID (GET /dish/{id})
 *
 * Uses DishHandler to interact with the database.
 */


package com.dt170g.g3.backend;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import com.dt170g.g3.backend.entities.LunchDish;
import java.util.List;



@Path("/dish")
public class DishApi {
    @Inject
    private DishHandler dbHandler;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LunchDish> getAllDishes() {
        return dbHandler.getDishes();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public LunchDish getDishById(@PathParam("id") int id) {
        return dbHandler.getDishById(id);
    }


}