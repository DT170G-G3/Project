package se.miun.g3.android_app_2.drinks;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DrinksApi {
    @GET("drink")
    Call<List<Drink>> getDrinks();
}