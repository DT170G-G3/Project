package com.DT170G.G3.android_app_1.orders;

import com.DT170G.G3.android_app_1.tables.Table;

import java.util.List;

public class Order {
    public String note;
    public int tableId;
    public List<DishEntry> dishes; //carteDish
    public List<DrinkEntry> drinks;
}

