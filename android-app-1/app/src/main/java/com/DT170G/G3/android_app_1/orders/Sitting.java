package com.DT170G.G3.android_app_1.orders;

import com.DT170G.G3.android_app_1.tables.Table;

import java.time.LocalDate;
import java.time.LocalTime;

public class Sitting {
    public int id;
    public String startTime;
    public String date;
    public int durationMinutes;
    public Table restaurantTable;
}