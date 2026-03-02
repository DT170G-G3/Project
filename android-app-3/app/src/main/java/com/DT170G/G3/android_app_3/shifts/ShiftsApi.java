package com.DT170G.G3.android_app_3.shifts;

import com.DT170G.G3.android_app_3.employees.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ShiftsApi {
    @GET("shifts")
    Call<List<Shift>> getShifts();
}
