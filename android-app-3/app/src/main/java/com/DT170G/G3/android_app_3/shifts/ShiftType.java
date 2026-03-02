package com.DT170G.G3.android_app_3.shifts;

import java.time.LocalTime;

public class ShiftType {
    public int id;
    public String name;
    public String start_time;   // LocalTime object
    public String end_time;     // LocalTime object
    public String getName(){
        return this.name;
    }
}
