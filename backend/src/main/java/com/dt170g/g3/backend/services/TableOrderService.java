/**
 * TableOrderService.java
 *
 * Service class for managing TableOrder entities.
 * Provides methods to:
 *   - findAllOrders()              : Retrieve all table orders
 *   - getOrderById(int id)         : Retrieve a single table order by its ID
 *   - findAllOrdersByTable(int id) : Retrieve all orders for a specific sitting, including associated dishes and drinks
 *   - addOrder(TableOrder o)       : Add a new TableOrder entry to the DB
 *
 * Uses JPA EntityManager to perform database operations.
 * Methods are application-scoped and can be injected into REST API resources.
 *
 * Example usage:
 *   tableOrderService.findAllOrders();
 *   tableOrderService.getOrderById(5);
 *   tableOrderService.findAllOrdersByTable(2);
 *
 * Author: Axel Friman
 * Date: 2026-03-05
 */
package com.dt170g.g3.backend.services;

import com.dt170g.g3.backend.entities.TableOrder;
import com.dt170g.g3.backend.entities.RestaurantTable;
import com.dt170g.g3.backend.entities.CarteDish;
import com.dt170g.g3.backend.entities.Drink;
import com.dt170g.g3.backend.entities.OrderCarteDish;
import com.dt170g.g3.backend.entities.OrderDrink;
import com.dt170g.g3.backend.DTO.OrderRequest;
import com.dt170g.g3.backend.DTO.DishRequest;
import com.dt170g.g3.backend.DTO.DrinkRequest;
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
    public void createOrder(OrderRequest request) {

        TableOrder order = new TableOrder();

        RestaurantTable table = entityManager.find(
                RestaurantTable.class,
                request.getTableId());

        if (table == null) {
            throw new IllegalArgumentException("Table not found");
        }

        order.setTable(table);
        order.setNote(request.getNote());

        // ---- HANDLE DISHES ----
        for (DishRequest dishRequest : request.getDishes()) {

            CarteDish dish = entityManager.find(
                    CarteDish.class,
                    dishRequest.getDishId());

            if (dish == null) {
                throw new IllegalArgumentException("Dish not found");
            }

            OrderCarteDish orderDish = new OrderCarteDish();
            orderDish.setOrder(order);
            orderDish.setCarteDish(dish);
            orderDish.setQuantity(dishRequest.getQuantity());
            order.addDish(orderDish);
        }

        // ---- HANDLE DRINKS ----
        for (DrinkRequest drinkRequest : request.getDrinks()) {

            Drink drink = entityManager.find(
                    Drink.class,
                    drinkRequest.getDrinkId());

            if (drink == null) {
                throw new IllegalArgumentException("Drink not found");
            }

            OrderDrink orderDrink = new OrderDrink();
            orderDrink.setOrder(order);
            orderDrink.setDrink(drink);
            orderDrink.setQuantity(drinkRequest.getQuantity());
            order.addDrink(orderDrink);
        }

        entityManager.persist(order);
    }
}