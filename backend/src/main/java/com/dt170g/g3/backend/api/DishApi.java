/**
 * DishApi.java
 *
 * REST API resource for managing CarteDish entities.
 * Provides endpoints to:
 *  - GET /dish                     : Retrieve all carte dishes
 *  - GET /dish/category/{id}       : Retrieve all carte dishes by category
 *  - GET /dish/id/{id}             : Retrieve a single carte dish by its ID
 *
 * Features proper REST error handling using:
 *  - BadRequestException          : For invalid request parameters
 *  - NotFoundException            : For missing resources
 *
 * Uses:
 *  - CarteDishService             : Handles database operations for CarteDish
 *  - CategoryService              : Handles database operations for Category
 *
 * Responses are returned in JSON format.
 *
 * Example usage:
 *   GET http://localhost:8080/restaurant/api/dish
 *   GET http://localhost:8080/restaurant/api/dish/category/1
 *   GET http://localhost:8080/restaurant/api/dish/id/1
 *
 * Author: Axel Friman
 * Date: 2026-02-26
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
import com.dt170g.g3.backend.services.CategoryService;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@Path("/dish")
public class DishApi {
    @Inject
    private CarteDishService dishHandler;
    @Inject
    private CategoryService categoryService;

    /**
     ** GET /dish
     ** Retrieve all dishes.
     ** Returns HTTP 200 OK with a list of CarteDish in JSON.
     **/
    @GET
    public Response getAllDishes() {
        List<CarteDish> dishes = dishHandler.findAllDishes();
        return Response.ok(dishes).build();
    }

    /**
     ** GET /dish/category/{id}
     ** Retrieve all dishes in a specific category.
     **
     ** Parameters:
     **   @PathParam("id") - category ID (int)
     **
     ** Behavior:
     **   - Throws BadRequestException if id <= 0
     **   - Throws NotFoundException if category does not exist
     **   - Returns empty list [] if category exists but has no dishes
     **
     ** Returns HTTP 200 OK with list of CarteDish in JSON
     **/
    @GET
    @Path("/category/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CarteDish> getDishByCategory(@PathParam("id") int id) {
        if (id <= 0) {
            throw new BadRequestException("Invalid category id");
        }

        if (!categoryService.categoryExists(id)) {
            throw new NotFoundException("Category not found");
        }

        return dishHandler.getDishesByCategory(id);
    }

    /**
     ** GET /dish/id/{id}
     ** Retrieve a single dish by its ID.
     **
     ** Parameters:
     **   @PathParam("id") - dish ID (int)
     **
     ** Behavior:
     **   - Throws BadRequestException if id <= 0
     **   - Throws NotFoundException if dish does not exist
     **
     ** Returns HTTP 200 OK with CarteDish in JSON
     **/
    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDishById(@PathParam("id") int id) {

        if (id <= 0) {
            throw new BadRequestException("Invalid dish id");
        }

        CarteDish dish = dishHandler.getDishById(id);

        if (dish == null) {
            throw new NotFoundException("Dish not found");
        }

        return Response.ok(dish).build();
    }
}