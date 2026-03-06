package com.DT170G.G3.android_app_3.shifts;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ShiftsApi {
    //http://localhost:8080/restaurant/api/shift
    @GET("shift")
    Call<List<Shift>> getShifts();
}