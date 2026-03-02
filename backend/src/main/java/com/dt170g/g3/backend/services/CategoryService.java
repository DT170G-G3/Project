/**
 * CategoryService.java
 *
 * Service class for managing categories in the restaurant application.
 * Provides methods to:
 *  - categoryExists(int)           : Check if a category exists by its ID
 *
 * Uses JPA EntityManager to access the Category entities.
 * This class is an application-scoped CDI bean.
 *
 * Example usage:
 *   if (!categoryService.categoryExists(1)) {
 *       throw new NotFoundException("Category not found");
 *   }
 *
 * Author: Axel Friman
 * Date: 2026-02-26
 */
package com.dt170g.g3.backend.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import com.dt170g.g3.backend.entities.Category;
import jakarta.inject.Inject;

@ApplicationScoped
public class CategoryService {
    @Inject
    private EntityManager em;

    public boolean categoryExists(int categoryId) {
        return em.find(Category.class, categoryId) != null;
    }
}