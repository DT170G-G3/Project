/**
 * RestApplication.java
 *
 * Configures the JAX-RS application for this backend.
 * Sets the base path for all REST endpoints to "/api".
 *
 * By extending jakarta.ws.rs.core.Application, this class
 * serves as the entry point for the REST API.
 */


package com.dt170g.g3.backend;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class RestApplication extends Application {

}