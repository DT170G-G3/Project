package se.miun.g3.android_app_2.dishes;

import se.miun.g3.android_app_2.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DishesRepository {

    public interface GetCallback {
        void onSuccess(List<Dish> dishes);
        void onError(String message);
    }


    public void getDishes(GetCallback cb) {
        Call<List<Dish>> call = ApiClient.dishesApi().getDishes();
        call.enqueue(new Callback<List<Dish>>() {
            @Override
            public void onResponse(Call<List<Dish>> call, Response<List<Dish>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    cb.onSuccess(response.body());
                }
                else {
                    cb.onError("HTTP " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Dish>> call, Throwable t) {
                cb.onError(t.getMessage());
            }
        });
    }
}