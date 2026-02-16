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
        return lunchHandler.getDishesToday();
    }

    @GET
    @Path("/lunch")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Dish> getLunchMenu() {
        return lunchHandler.getDishesToday(); }

    @POST
    @Path("/addDish")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addDish(Dish dish) {
        lunchHandler.uploadDish(dish);
        return Response.ok(dish).build();
    }


}