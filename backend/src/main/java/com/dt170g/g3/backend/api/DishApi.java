/**
 * DishApi.java
 *
 * REST API resource for managing Dish entities.
 * Provides endpoints to:
 *  - GET /dish          : Retrieve all dishes
 *  - GET /dish/{id}     : Retrieve a single dish by its ID
 *
 * Uses DishHandler to interact with the database.
 * All responses are returned in JSON format.
 *
 * Example usage:
 *   GET http://localhost:8080/restaurant/api/dish
 *   GET http://localhost:8080/restaurant/api/dish/1
 *
 * Author: Axel Friman
 * Date: 2026-02-16
 */


package com.dt170g.g3.backend;

import com.dt170g.g3.backend.services.LunchDishService;
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
    private LunchDishService dishHandler;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LunchDish> getAllDishes() {
        return dishHandler.findAllLunchDishes();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public LunchDish getDishById(@PathParam("id") int id) {
        return dishHandler.getDishById(id);
    }


}