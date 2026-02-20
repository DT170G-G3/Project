package com.DT170G.G3.android_app_1.tables;

import com.DT170G.G3.android_app_1.dishes.Dish;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TablesApi {
    @GET("tables")
    Call<List<Table>> getTables();
}
