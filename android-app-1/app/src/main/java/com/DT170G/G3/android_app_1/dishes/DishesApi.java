package com.DT170G.G3.android_app_1.dishes;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

//Retrofit interface (endpoint)
public interface DishesApi {
    @GET("dish")  // http://localhost:8080/restaurant/api/menu/carte/menu/1 fungerar inte
    Call<List<Dish>> getDishes();
}
