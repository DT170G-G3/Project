package com.DT170G.G3.android_app_1.waiters;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface WaitersApi {
    // Get a list of all waiters
    @GET("waiters")
    Call<List<Waiter>> getWaiters();
}
