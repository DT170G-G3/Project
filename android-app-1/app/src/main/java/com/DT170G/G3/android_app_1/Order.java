package com.DT170G.G3.android_app_1;

import java.util.List;

public class Order {
    String note;
    int tableId;
    List<OrderItem> orderedItems;
    public static class OrderItem {
        int dishId;
        int category; //temp: 1 for appetizer, 2 for main course, 3 for dessert
    }
}