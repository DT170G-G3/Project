/**
 * NotFoundMapper.java
 *
 * JAX-RS Exception Mapper for handling NotFoundException.
 *
 * Purpose:
 *   - Converts any thrown NotFoundException in the REST API
 *     into a standard HTTP 404 Not Found response.
 *   - Centralizes error handling for missing resources.
 *
 * Behavior:
 *   - Intercepts NotFoundException anywhere in the JAX-RS application
 *   - Returns HTTP 404 status
 *   - Sets the response body to the exception message (plain text)
 *
 * Usage:
 *   - Automatically triggered when NotFoundException is thrown
 *     in any REST resource method.
 *   - No changes needed in the resource classes themselves.
 *
 * Example:
 *   throw new NotFoundException("Dish not found");
 *   --> automatically returns HTTP 404 with message "Dish not found"
 *
 * Author: Axel Friman
 * Date: 2026-02-26
 */
package com.dt170g.g3.backend.exceptions;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NotFoundMapper implements ExceptionMapper<NotFoundException> {
    @Override
    public Response toResponse(NotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(e.getMessage())
                .build();
    }
}