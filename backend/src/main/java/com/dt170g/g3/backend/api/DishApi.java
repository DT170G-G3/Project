/**
 * DishApi.java
 *
 * REST API resource for managing CarteDish entities.
 * Provides endpoints to:
 *  - GET /dish                     : Retrieve all carte dishes
 *  - GET /dish/category/{category} : Retrieve all carte dishes by category
 *  - GET /dish/id/{id}             : Retrieve a single carte dish by its ID
 *
 * Uses CarteDishService to interact with the database.
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

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import com.dt170g.g3.backend.entities.CarteDish;
import com.dt170g.g3.backend.services.CarteDishService;

import java.util.List;

@Path("/dish")
public class DishApi {
    @Inject
    private CarteDishService dishHandler;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CarteDish> getAllDishes() {
        return dishHandler.findAllDishes();
    }

    @GET
    @Path("/category/{category}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CarteDish> getDishById(@PathParam("category") String category) {
        return dishHandler.getDishesByCategory(category);
    }
    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CarteDish getDishById(@PathParam("id") int id) {
        return dishHandler.getDishById(id);
    }



}