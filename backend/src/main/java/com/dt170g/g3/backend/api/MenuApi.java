/**
 * MenuApi.java
 *
 * REST API resource for managing lunch menus and dishes.
 * Provides endpoints to:
 *  - GET /menu/lunch           : Retrieve the lunch menu for today
 *  - GET /menu/lunch/{date}    : Retrieve the lunch menu for the specified date
 *
 * Uses LunchMenuHandler to fetch dishes from the database.
 * All responses are returned as JSON.
 *
 * Example usage:
 *   GET http://localhost:8080/restaurant/api/menu/lunch
 *   GET http://localhost:8080/restaurant/api/menu/lunch/2026-02-17
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
import java.time.LocalDate;


@Path("/menu")
public class MenuApi {
    @Inject
    private LunchMenuHandler lunchHandler;

    @GET
    @Path("/lunch")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Dish> getLunchMenu() {
        return lunchHandler.getLunchDishesToday();
    }

    @GET
    @Path("/lunch/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Dish> getLunchMenu(@PathParam("date") String date) {
        return lunchHandler.getLunchDishesByDate(LocalDate.parse(date));
    }



}