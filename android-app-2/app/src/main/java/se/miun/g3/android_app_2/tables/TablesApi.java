package se.miun.g3.android_app_2.tables;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TablesApi {
    @GET("table")
    Call<List<Table>> getTables();
}