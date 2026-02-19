package se.miun.g3.android_app_2.orders;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OrdersApi {
    @GET("orders")
    Call<List<Order>> getOrders();
    @POST("orders")
    Call<Order> postOrder(@Body Order order);
}
