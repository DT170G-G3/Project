package com.DT170G.G3.android_app_1.tables;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TablesApi {
    @GET("table")
    Call<List<Table>> getTables();
}
