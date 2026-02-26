package se.miun.g3.android_app_2.dishes;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

//Retrofit interface (endpoint)
public interface DishesApi {
    @GET("dish")  // dishes for json server, dish for payara DATABASE
    Call<List<Dish>> getDishes();
}