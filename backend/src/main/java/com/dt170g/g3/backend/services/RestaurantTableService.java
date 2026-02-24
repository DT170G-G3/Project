/**
 * RestaurantTableService.java
 *
 * Service class for managing restaurant tables in the application.
 * Provides methods to:
 *  - getTableById(int id)       : Retrieve a specific restaurant table by its ID
 *  - findAllTables()            : Retrieve all restaurant tables from the database
 *
 * Uses JPA EntityManager to access the RestaurantTable entities.
 * This class is an application-scoped CDI bean.
 *
 * Example usage:
 *   @Inject
 *   RestaurantTableService tableService;
 *
 *   RestaurantTable table = tableService.getTableById(1);
 *   List<RestaurantTable> allTables = tableService.findAllTables();
 *
 * Author: Axel Friman
 * Date: 2026-02-20
 */
package com.dt170g.g3.backend.services;

import com.dt170g.g3.backend.entities.RestaurantTable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
@ApplicationScoped
public class RestaurantTableService {
    @PersistenceContext
    EntityManager entityManager;

    public RestaurantTable getTableById(int id){
        return entityManager.find(RestaurantTable.class, id);
    }
    public List<RestaurantTable> findAllTables() {
        return entityManager
                .createNamedQuery("restaurantTable.getAll", RestaurantTable.class)
                .getResultList();
    }
}

