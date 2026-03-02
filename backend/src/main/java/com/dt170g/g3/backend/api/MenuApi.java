/**
 * MenuApi.java
 *
 * REST API resource for managing menus and dishes.
 * Provides endpoints to:
 *  - GET /menu/lunch           : Retrieve the lunch menu for today
 *  - GET /menu/lunch/{date}    : Retrieve the lunch menu for the specified date
 *  - GET /menu/carte/menu/{menuId}/category/{categoryId} : Retrieve one category of dishes from the menu
 *  - GET /menu/carte/menu/{menuId} : Retrieve one menu by id
 *  - GET /menu/carte   : Retrieve all menus
 *
 * Uses LunchMenuHandler to fetch dishes from the database.
 * All responses are returned as JSON.
 *
 * Example usage:
 *   GET http://localhost:8080/restaurant/api/menu/lunch
 *   GET http://localhost:8080/restaurant/api/menu/lunch/2026-02-17
 *   GET http://localhost:8080/restaurant/api/menu/carte/menu/1/category/1
 *   GET http://localhost:8080/restaurant/api/menu/carte/menu/1
 *   GET http://localhost:8080/restaurant/api/menu/carte
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
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import com.dt170g.g3.backend.services.LunchMenuService;
import com.dt170g.g3.backend.entities.LunchDish;
import com.dt170g.g3.backend.services.CarteMenuService;
import com.dt170g.g3.backend.entities.CarteDish;
import com.dt170g.g3.backend.entities.CarteMenu;
import java.util.List;
import java.time.LocalDate;


@Path("/menu")
public class MenuApi {
    @Inject
    private LunchMenuService lunchHandler;
    @Inject
    private CarteMenuService carteHandler;

    @GET
    @Path("/lunch")
    @Produces(MediaType.APPLICATION_JSON)
    public List<LunchDish> getLunchMenu() {
        return lunchHandler.getLunchDishesToday();
    }

    @GET
    @Path("/lunch/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<LunchDish> getLunchMenu(@PathParam("date") String date) {
        return lunchHandler.getLunchDishesByDate(LocalDate.parse(date));
    }

    @GET
    @Path("/carte/menu/{menuId}/category/{categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarteMenuByCategory(
            @PathParam("menuId") int menuId,
            @PathParam("categoryId") int categoryId) {
        List<CarteDish> dishes = carteHandler.findByCategory(menuId, categoryId);
        return Response.ok(dishes).build();
    }

    @GET
    @Path("/carte/menu/{menuId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarteMenuByCategory(@PathParam("menuId") int id) {
        CarteMenu menu = carteHandler.findById(id);
        return Response.ok(menu.getDishes()).build();
    }

    @GET
    @Path("/carte")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CarteMenu> getCarteMenu() {
        return carteHandler.getAllMenus();
    }

}