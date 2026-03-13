package se.miun.g3.android_app_2.drinks;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import se.miun.g3.android_app_2.ApiClient;

public class DrinksRepository {

    public interface GetCallback {
        void onSuccess(List<Drink> drinks);
        void onError(String message);
    }


    public void getDrinks(GetCallback cb) {
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