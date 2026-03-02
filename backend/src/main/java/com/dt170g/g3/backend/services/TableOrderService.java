/**
 * TableOrderService.java
 *
 * Service class for managing TableOrder entities.
 * Provides methods to:
 *   - findAllOrders()             : Retrieve all table orders
 *   - getOrderById(int id)        : Retrieve a single table order by its ID
 *   - findAllOrdersBySitting(int id) : Retrieve all orders for a specific sitting, including associated dishes and drinks
 *   - addOrder(TableOrder o)   : Add a new TableOrder entry to the DB
 *
 * Uses JPA EntityManager to perform database operations.
 * Methods are application-scoped and can be injected into REST API resources.
 *
 * Example usage:
 *   tableOrderService.findAllOrders();
 *   tableOrderService.getOrderById(5);
 *   tableOrderService.findAllOrdersBySitting(2);
 *
 * Author: Axel Friman
 * Date: 2026-02-20
 */
package com.dt170g.g3.backend.services;

import com.dt170g.g3.backend.entities.TableOrder;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
@ApplicationScoped
public class TableOrderService {
    @PersistenceContext
    EntityManager entityManager;

    public List<TableOrder> findAllOrders() {
        return entityManager
                .createNamedQuery("TableOrder.getAll", TableOrder.class)
                .getResultList();
    }
    public TableOrder getOrderById(int id){
        return entityManager.find(TableOrder.class, id);
    }
    public List<TableOrder> findAllOrdersByTable(int id) {
        TypedQuery<TableOrder> query = entityManager.createNamedQuery("TableOrder.findByTable", TableOrder.class);
        query.setParameter("tableId", id);
        return query.getResultList();
    }

    @Transactional
    public void addOrder(TableOrder o) {
        entityManager.persist(o);
    }
}