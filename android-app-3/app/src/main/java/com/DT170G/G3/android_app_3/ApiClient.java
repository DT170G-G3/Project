package com.DT170G.G3.android_app_3;
/**
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static String BASE_URL = "http://10.0.2.2:8080/restaurant/api/";

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    // http://localhost:8080/restaurant/api/dish
    public static EmployeesApi employeesApiApi() {
        return retrofit.create(EmployeeApi.class);
    }

    public static ShiftsApi shiftsApi() {
        return retrofit.create(ShiftsApi.class);
    }
}
*/