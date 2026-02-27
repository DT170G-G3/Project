package com.DT170G.G3.android_app_1;

import com.DT170G.G3.android_app_1.dishes.DishesApi;
import com.DT170G.G3.android_app_1.drinks.DrinksApi;
import com.DT170G.G3.android_app_1.orders.OrdersApi;
import com.DT170G.G3.android_app_1.tables.TablesApi;
import com.DT170G.G3.android_app_1.waiters.WaitersApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ApiClient {
    private static String BASE_URL = "http://10.0.2.2:8080/restaurant/api/";

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    // http://localhost:8080/restaurant/api/dish
    public static DishesApi dishesApi() {
        return retrofit.create(DishesApi.class);
    }
    public static WaitersApi waiterApi() {
        return retrofit.create(WaitersApi.class);
    }
    public static OrdersApi ordersApi() {
        return retrofit.create(OrdersApi.class);
    }
    public static DrinksApi drinksApi() {
        return retrofit.create(DrinksApi.class);
    }
    public static TablesApi tablesApi() {
        return retrofit.create(TablesApi.class);
    }


    /*//generell lösning möjligtvis
    public static <T> T create(Class<T> service) {
        return retrofit.create(service);
    }*/
    // Call<List<Dish>> call = ApiClient.create(DishesApi.class).getDishes(); (Byt ut den i DishesRepository)
}
