package com.DT170G.G3.android_app_1.waiters;

import android.util.Log;

import com.DT170G.G3.android_app_1.ApiClient;

import retrofit2.Call;
import retrofit2.Response;
import java.util.List;
import retrofit2.Callback;

public class WaitersRepository {
    public interface WaitersCallback {
        void onSuccess(List<Waiter> waiters);
        void onError(String message);
    }


    public void getWaiters(WaitersCallback cb) {
        Call<List<Waiter>> listWaiters = ApiClient.waiterApi().getWaiters();
        listWaiters.enqueue(new Callback<List<Waiter>>() {
            @Override
            public void onResponse(Call<List<Waiter>> call, Response<List<Waiter>> response) {
                if (response.body() == null) {
                    Log.d("API Response","Response call is null");
                }
                else {
                    Log.d("API Response", "Code: " + response.code());
                    cb.onSuccess(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<Waiter>> call, Throwable t) {
                //handle error code
                Log.d("Waiter API onFailure: ", t.toString());
                cb.onError(t.getMessage());
            }
        });
    }
}
