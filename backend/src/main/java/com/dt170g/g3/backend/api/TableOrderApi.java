/**
 * TableOrderApi.java
 *
 * REST API resource for managing TableOrder entities.
 * Provides endpoints to:
 *   - GET /order             : Retrieve all table orders
 *   - GET /order/sitting/{id} : Retrieve all orders for a specific sitting
 *   - GET /order/id/{id}     : Retrieve a single order by its ID
 *   - POST /order/add  : Creates a new table order from the provided JSON payload
 *
 * Uses TableOrderService to interact with the database.
 * All responses are returned in JSON format.
 *
 * Example usage:
 *   GET http://localhost:8080/restaurant/api/order
 *   GET http://localhost:8080/restaurant/api/order/sitting/1
 *   GET http://localhost:8080/restaurant/api/order/id/5
 *
 * Author: Axel Friman
 * Date: 2026-02-20
 */
package com.dt170g.g3.backend;

import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import com.dt170g.g3.backend.entities.TableOrder;
import com.dt170g.g3.backend.services.TableOrderService;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Consumes;
import java.util.List;

@Path("/order")
public class TableOrderApi {
    @Inject
    private TableOrderService orderHandler;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TableOrder> getAllOrders() {
        return orderHandler.findAllOrders();
    }


    @GET
    @Path("/sitting/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TableOrder> getOrderBySitting(@PathParam("id") int id) {
        return orderHandler.findAllOrdersBySitting(id);
    }
    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TableOrder getOrderById(@PathParam("id") int id) {
        return orderHandler.getOrderById(id);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrder(TableOrder order) {
        try {
                orderHandler.addOrder(order);
                return Response.status(Response.Status.CREATED).entity(order).build();
            } catch (Exception e) {
                e.printStackTrace();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                               .entity("Failed to create order: " + e.getMessage())
                               .build();
            }
    }

}