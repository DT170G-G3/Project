package com.DT170G.G3.android_app_3.employees;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface EmployeesApi {
    @GET("employees")
    Call<List<Employee>> getEmployees();
}
