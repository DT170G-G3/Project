package se.miun.g3.android_app_2.drinks;
import se.miun.g3.android_app_2.dishes.Dish;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

//Retrofit interface (endpoint)
public interface DrinksApi {
    @GET("drinks")
    Call<List<Drink>> getDrinks();
}
