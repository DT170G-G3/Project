package com.DT170G.G3.android_app_1.drinks;

import com.DT170G.G3.android_app_1.ApiClient;
import com.DT170G.G3.android_app_1.dishes.Dish;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrinksRepository {

    public interface GetCallback {
        void onSuccess(List<Drink> drinks);
        void onError(String message);
    }


    public void getDrinks(com.DT170G.G3.android_app_1.drinks.DrinksRepository.GetCallback cb) {
        Call<List<Drink>> call = ApiClient.drinksApi().getDrinks();
        call.enqueue(new Callback<List<Drink>>() {
            @Override
            public void onResponse(Call<List<Drink>> call, Response<List<Drink>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    cb.onSuccess(response.body());
                }
                else {
                    cb.onError("HTTP " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Drink>> call, Throwable t) {
                cb.onError(t.getMessage());
            }
        });
    }
}