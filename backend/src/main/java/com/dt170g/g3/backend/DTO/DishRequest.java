/**
 * DishRequest.java
 *
 * DTO (Data Transfer Object) representing a request for a specific dish.
 * Used to transfer dish order details between client and server.
 * Contains:
 *   - dishId   : ID of the dish being ordered
 *   - quantity : Number of portions requested
 *
 * Typically used in API endpoints when creating or updating orders.
 *
 * Example usage:
 *   {
 *     "dishId": 3,
 *     "quantity": 2
 *   }
 *
 * Author: Axel Friman
 * Date: 2026-03-05
 */

package com.dt170g.g3.backend.DTO;

public class DishRequest {

    private int dishId;
    private int quantity;

    public DishRequest() {}

    public DishRequest(int dishId, int quantity) {
        this.dishId = dishId;
        this.quantity = quantity;
    }

    // Getters and Setters
    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}