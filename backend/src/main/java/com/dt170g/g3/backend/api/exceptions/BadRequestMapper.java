/**
 * BadRequestMapper.java
 *
 * JAX-RS Exception Mapper for handling BadRequestException.
 *
 * Purpose:
 *   - Converts any thrown BadRequestException in the application
 *     into a standard HTTP 400 Bad Request response.
 *   - Centralizes error handling for invalid request parameters.
 *
 * Behavior:
 *   - Intercepts BadRequestException anywhere in the REST API
 *   - Returns HTTP 400 status
 *   - Sets the response body to the exception message (plain text)
 *
 * Usage:
 *   - Automatically triggered when BadRequestException is thrown
 *     in any resource method.
 *   - No changes needed in the resource classes themselves.
 *
 * Example:
 *   throw new BadRequestException("Invalid category id");
 *   --> automatically returns HTTP 400 with message "Invalid category id"
 *
 * Author: Axel Friman
 * Date: 2026-02-26
 */
package com.dt170g.g3.backend.exceptions;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BadRequestMapper implements ExceptionMapper<BadRequestException> {

    @Override
    public Response toResponse(BadRequestException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(e.getMessage())
                .build();
    }
}