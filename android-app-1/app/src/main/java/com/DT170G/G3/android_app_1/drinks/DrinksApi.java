package com.DT170G.G3.android_app_1.drinks;

import com.DT170G.G3.android_app_1.dishes.Dish;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

//Retrofit interface (endpoint)
public interface DrinksApi {
    @GET("drinks")
    Call<List<Drink>> getDrinks();
}