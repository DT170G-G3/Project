package com.DT170G.G3.android_app_1;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ApiClient {
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static DishesApi dishesApi() {
        return retrofit.create(DishesApi.class);
    }
    public static WaitersApi waiterApi() {
        return retrofit.create(WaitersApi.class);
    }
    public static OrdersApi ordersApi() {
        return retrofit.create(OrdersApi.class);
    }


    /*//generell lösning
    public static <T> T create(Class<T> service) {
        return retrofit.create(service);
    }*/
    // Call<List<Dish>> call = ApiClient.create(DishesApi.class).getDishes(); (Byt ut den i DishesRepository)
}
