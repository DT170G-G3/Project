package com.example.myapplication;

import java.util.List;

public class Order {
    private int tableNumber;
    private List<String> orders;

    public Order(int tableNumber, List<String> orders) {
        this.tableNumber = tableNumber;
        this.orders = orders;
    }
    public int getTableNumber() {
        return tableNumber;
    }

    public List<String> getOrders() {
        return orders;
    }
}
