/**
 * DrinkRequest.java
 *
 * DTO (Data Transfer Object) representing a request for a specific drink.
 * Used to transfer drink order details between client and server.
 * Contains:
 *   - drinkId : ID of the drink being ordered
 *   - quantity: Number of portions requested
 *
 * Typically used in API endpoints when creating or updating orders.
 *
 * Example usage:
 *   {
 *     "drinkId": 5,
 *     "quantity": 3
 *   }
 *
 * Author: Axel Friman
 * Date: 2026-03-05
 */

package com.dt170g.g3.backend.DTO;

public class DrinkRequest {
    private int drinkId;
    private int quantity;

    public DrinkRequest() {}

    public DrinkRequest(int drinkId, int quantity) {
        this.drinkId = drinkId;
        this.quantity = quantity;
    }

    // Getters and Setters
    public int getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}