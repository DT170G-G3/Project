package se.miun.g3.android_app_2.dishes;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

//Retrofit interface (endpoint)
public interface DishesApi {
    @GET("dish")  // http://localhost:8080/restaurant/api/menu/carte/menu/1
    Call<List<Dish>> getDishes();
}