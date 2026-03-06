package com.dt170g.g3.backend.api;



import com.dt170g.g3.backend.entities.Shift;
import com.dt170g.g3.backend.entities.SwapRequest;
import com.dt170g.g3.backend.entities.TableOrder;
import com.dt170g.g3.backend.services.ShiftService;
import jakarta.inject.Inject;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Path("/shift")
public class ShiftApi {
    @Inject
    private ShiftService shiftHandler;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response allShifts(){
        List<Shift> shifts = shiftHandler.getAllShifts();
        return Response.ok(shifts).build();
    }

    @GET
    @Path("/week/{weekNr}")
    public Response shiftByWeek(@PathParam("weekNr") int weekNr){
        List<Shift> shifts = shiftHandler.getShiftByWeek(weekNr);
        return Response.ok(shifts).build();
    }

    @GET
    @Path("/day/{day}")
    public Response shiftByWeek(@PathParam("day") String day){
        LocalDate date = LocalDate.parse(day);
        List<Shift> shifts = shiftHandler.getShiftsByDate(date);
        return Response.ok(shifts).build();
    }

    @POST
    @Path("/swap")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSwapRequest(SwapRequest req) {
        try {
            shiftHandler.createSwapRequest(req);
            return Response.status(Response.Status.CREATED).entity(req).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to swap: " + e.getMessage())
                    .build();
        }
    }


}
