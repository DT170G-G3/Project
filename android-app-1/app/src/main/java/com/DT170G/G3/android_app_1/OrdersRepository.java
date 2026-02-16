package com.DT170G.G3.android_app_1;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrdersRepository {
    public interface OrderCallback {
        void onSuccess(Order postedOrder);
        void onError(String message);
    }
    public void postOrder(Order order, OrderCallback cb) {
        Call<Order> call = ApiClient.ordersApi().postOrder(order);
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if (response.isSuccessful() && response.body() != null) {
                    cb.onSuccess(response.body());
                }
                else {
                    cb.onError("HTTP " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                cb.onError(t.getMessage());
            }
        });
    }
}
