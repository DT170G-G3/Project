package com.DT170G.G3.android_app_1;

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
