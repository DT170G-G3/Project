package com.DT170G.G3.android_app_1.orders;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OrdersApi {
    @GET("order")
    Call<List<Order>> getOrders();
    @POST("order/add")
    Call<Void> postOrder(@Body Order order);
}
