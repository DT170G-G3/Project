package com.DT170G.G3.android_app_3.shifts;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ShiftsApi {
    //http://localhost:8080/restaurant/api/shift
    @GET("shift")
    Call<List<Shift>> getShifts();

    //http://localhost:8080/restaurant/api/shift/day/2026-03-02
    @GET("shift/day/{date}")
    Call<List<Shift>> getShiftsByDate(@Path("date") String date);

    @POST("shift/swap/request")
    Call<Void> postShiftSwap(@Body ShiftSwap shiftChange);

    @PUT("shift/swap/{id}")
    Call<Void> putShiftUpdate(@Path("id") int id, @Body ShiftUpdate update);
}