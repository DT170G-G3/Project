package com.DT170G.G3.android_app_1;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

//Retrofit interface (endpoint)
public interface DishesApi {
    @GET("dishes")
    Call<List<Dish>> getDishes();
}
