/**
 * DrinkApi.java
 *
 * REST API resource for managing Drink entities.
 * Provides endpoints to:
 *   - GET /drink             : Retrieve all drinks
 *   - GET /drink/name/{name} : Retrieve a drink by its name
 *   - GET /drink/id/{id}     : Retrieve a drink by its ID
 *
 * Uses DrinkService to interact with the database.
 * All responses are returned in JSON format.
 *
 * Error handling:
 *   - Throws NotFoundException if a drink is not found
 *     (handled by NotFoundMapper)
 *
 * Example usage:
 *   GET http://localhost:8080/restaurant/api/drink
 *   GET http://localhost:8080/restaurant/api/drink/name/CocaCola
 *   GET http://localhost:8080/restaurant/api/drink/id/1
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
import com.dt170g.g3.backend.entities.Drink;
import com.dt170g.g3.backend.services.DrinkService;
import jakarta.ws.rs.core.Response;
import java.util.List;
import jakarta.ws.rs.NotFoundException;

@Path("/drink")
public class DrinkApi {
    @Inject
    private DrinkService drinkHandler;

    /**
     ** GET /drink
     ** Retrieve all drinks.
     **
     ** Returns:
     **   - HTTP 200 OK with a list of Drink entities in JSON
     **   - An empty list [] if there are no drinks
     **   - HTTP 500 Internal Server Error automatically if a database error occurs
     **/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDrinks() {
        List<Drink> drinks = drinkHandler.findAllDrinks();
        return Response.ok(drinks).build();
    }


    /**
     ** GET /drink/name/{name}
     ** Retrieve a single drink by its name.
     **
     ** Parameters:
     **   @PathParam("name") - the name of the drink
     **
     ** Returns:
     **   - HTTP 200 OK with the Drink entity in JSON if found
     **   - HTTP 404 Not Found if the drink does not exist
     **     (throws NotFoundException, handled by NotFoundMapper)
     **/
    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDrinkByName(@PathParam("name") String name) {
        Drink d = drinkHandler.getDrinkByName(name);
        if (d == null) {
            throw new NotFoundException("Drink not found");
        }
        return Response.ok(d).build();
    }

    /**
     ** GET /drink/id/{id}
     ** Retrieve a single drink by its ID.
     **
     ** Parameters:
     **   @PathParam("id") - the ID of the drink
     **
     ** Returns:
     **   - HTTP 200 OK with the Drink entity in JSON if found
     **   - HTTP 404 Not Found if the drink does not exist
     **     (throws NotFoundException, handled by NotFoundMapper)
     **/
    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDrinkById(@PathParam("id") int id) {
        Drink d = drinkHandler.getDrinkById(id);
        if (d == null) {
            throw new NotFoundException("Drink not found");
        }
        return Response.ok(d).build();
    }

}