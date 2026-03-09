package com.dt170g.g3.backend.api;

import com.dt170g.g3.backend.entities.Employee;
import com.dt170g.g3.backend.entities.Shift;
import com.dt170g.g3.backend.services.EmployeeService;
import com.dt170g.g3.backend.services.ShiftService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/employee")
public class EmployeeApi {
    @Inject
    private EmployeeService employeeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response allEmployees(){
        List<Employee> employees = employeeService.getAllEmployees();
        return Response.ok(employees).build();
    }

}
