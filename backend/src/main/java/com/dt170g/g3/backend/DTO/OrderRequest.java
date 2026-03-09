/**
 * OrderRequest.java
 *
 * DTO (Data Transfer Object) representing a complete order request for a table.
 * Used to transfer order details, including dishes and drinks, from client to server.
 * Contains:
 *   - tableId : ID of the table placing the order
 *   - note    : Optional note or special instructions for the order
 *   - dishes  : List of DishRequest objects representing ordered dishes
 *   - drinks  : List of DrinkRequest objects representing ordered drinks
 *
 * Typically used in API endpoints when creating or updating a table order.
 *
 * Example usage:
 *   {
 *     "tableId": 1,
 *     "note": "No cheese",
 *     "dishes": [{"dishId": 3, "quantity": 2}],
 *     "drinks": [{"drinkId": 5, "quantity": 3}]
 *   }
 *
 * Author: Axel Friman
 * Date: 2026-03-05
 */
package com.dt170g.g3.backend.DTO;

import java.util.List;

public class OrderRequest {

    private int tableId;
    private String note;
    private List<DishRequest> dishes;
    private List<DrinkRequest> drinks;

    public OrderRequest() {}

    public OrderRequest(int tableId, String note, List<DishRequest> dishes, List<DrinkRequest> drinks) {
        this.tableId = tableId;
        this.note = note;
        this.dishes = dishes;
        this.drinks = drinks;
    }

    // Getters and Setters
    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<DishRequest> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishRequest> dishes) {
        this.dishes = dishes;
    }

    public List<DrinkRequest> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkRequest> drinks) {
        this.drinks = drinks;
    }
}