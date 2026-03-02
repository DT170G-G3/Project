package se.miun.g3.android_app_2.orders;

import se.miun.g3.android_app_2.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrdersRepository {

    //Used to POST an Order to database
    public interface PostCallback {
        void onSuccess(Order postedOrder);
        void onError(String message);
    }

    //Used to GET a list of all orders from database
    public interface GetCallback {
        void onSuccess(List<Order> orders);
        void onError(String message);
    }


    public void postOrder(Order order, PostCallback cb) {
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

    public void getOrders(GetCallback cb) {
        Call<List<Order>> call = ApiClient.ordersApi().getOrders();
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    cb.onSuccess(response.body());
                }
                else {
                    cb.onError("HTTP " + response.code());
                }
            }
            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                cb.onError(t.getMessage());
            }
        });
    }
}
