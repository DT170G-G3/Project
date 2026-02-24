/**
 * DrinkApi.java
 *
 * REST API resource for managing Drink entities.
 * Provides endpoints to:
 *   - GET /drink          : Retrieve all drinks
 *   - GET /drink/name/{name} : Retrieve a drink by its name
 *   - GET /drink/id/{id}  : Retrieve a drink by its ID
 *
 * Uses DrinkService to interact with the database.
 * All responses are returned in JSON format.
 *
 * Example usage:
 *   GET http://localhost:8080/restaurant/api/drink
 *   GET http://localhost:8080/restaurant/api/drink/name/CocaCola
 *   GET http://localhost:8080/restaurant/api/drink/id/1
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
import com.dt170g.g3.backend.entities.Drink;
import com.dt170g.g3.backend.services.DrinkService;

import java.util.List;

@Path("/drink")
public class DrinkApi {
    @Inject
    private DrinkService drinkHandler;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Drink> getAllDrinks() {
        return drinkHandler.findAllDrinks();
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Drink getDrinkByName(@PathParam("name") String name) {
        return drinkHandler.getDrinkByName(name);
    }
    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Drink getDrinkById(@PathParam("id") int id) {
        return drinkHandler.getDrinkById(id);
    }

}