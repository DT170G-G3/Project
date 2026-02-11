package com.DT170G.G3.android_app_1.classes;

import com.DT170G.G3.android_app_1.R;

import java.util.List;

public class Order {
    private String tableName;
    private List<String> items;

    private String notes;

    public Order(String tableName, List<String> items, String notes) {
        this.tableName = tableName;
        this.items = items;
        this.notes = notes;
    }

    public Order(String tableName, List<String> items) {
        this.tableName = tableName;
        this.items = items;
        this.notes = "Inga noteringar";
    }

    public String getTableName() {
        return tableName;
    }

    public List<String> getItems() {
        return items;
    }

    public String getNotes() {
        return notes;
    }
}
