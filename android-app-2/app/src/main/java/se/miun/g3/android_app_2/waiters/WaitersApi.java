package se.miun.g3.android_app_2.waiters;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WaitersApi {
    // Get a list of all waiters
    @GET("waiters")
    Call<List<Waiter>> getWaiters();
}
