package se.miun.g3.android_app_2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import se.miun.g3.android_app_2.dishes.DishesApi;
import se.miun.g3.android_app_2.orders.*;

public final class ApiClient {
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static OrdersApi ordersApi() {
        return retrofit.create(OrdersApi.class);
    }
    public static DishesApi dishesApi() {return retrofit.create(DishesApi.class);}

}