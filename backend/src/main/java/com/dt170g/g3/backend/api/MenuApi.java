/**
 * MenuApi.java
 *
 * REST API resource for managing lunch menus and dishes.
 * Provides endpoints to:
 *  - GET /menu          : Retrieve all dishes for today
 *  - GET /menu/lunch    : Retrieve the lunch menu for today
 *
 * Uses LunchMenuHandler to fetch dishes from the database.
 * All responses are returned as JSON.
 *
 * Example usage:
 *   GET http://localhost:8080/restaurant/api/menu
 *   GET http://localhost:8080/restaurant/api/menu/lunch
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
import com.dt170g.g3.backend.entities.LunchMenu;
import com.dt170g.g3.backend.entities.Dish;
import java.util.List;

@Path("/menu")
public class MenuApi {
    @Inject
    private LunchMenuHandler lunchHandler;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Dish> getAllDishes() {
        return lunchHandler.getLunchDishesToday();
    }

    @GET
    @Path("/lunch")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Dish> getLunchMenu() {
        return lunchHandler.getLunchDishesToday();
    }

}