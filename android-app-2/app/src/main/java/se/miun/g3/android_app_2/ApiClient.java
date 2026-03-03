package se.miun.g3.android_app_2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import se.miun.g3.android_app_2.dishes.DishesApi;
import se.miun.g3.android_app_2.drinks.DrinksApi;
import se.miun.g3.android_app_2.orders.OrdersApi;
import se.miun.g3.android_app_2.tables.TablesApi;
import se.miun.g3.android_app_2.waiters.WaitersApi;
import se.miun.g3.android_app_2.drinks.DrinksRepository;
import se.miun.g3.android_app_2.orders.*;
import se.miun.g3.android_app_2.tables.TablesRepository;
import se.miun.g3.android_app_2.waiters.WaitersRepository;

public final class ApiClient {
    private static String BASE_URL = "http://10.0.2.2:8080/restaurant/api/";


    // FÖR PAYARA DATABASEN
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    // http://localhost:8080/restaurant/api/dish
    public static DishesApi dishesApi() {
        return retrofit.create(DishesApi.class);
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
    public static WaitersApi waitersApi() {
        return retrofit.create(WaitersApi.class);
    }


    /*//generell lösning möjligtvis
    public static <T> T create(Class<T> service) {
        return retrofit.create(service);
    }*/
    // Call<List<Dish>> call = ApiClient.create(DishesApi.class).getDishes(); (Byt ut den i DishesRepository)
}