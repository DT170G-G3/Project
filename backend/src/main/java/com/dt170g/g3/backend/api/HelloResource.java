package com.dt170g.g3.backend;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import com.dt170g.g3.backend.entities.Dish;
import java.util.List;

@Path("/message")
public class HelloResource {
    @Inject
    private DatabaseHandler dbHandler;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Dish> getAllMessages() {
        return dbHandler.getDishes();
    }
}

