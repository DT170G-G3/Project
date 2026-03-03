package com.DT170G.G3.android_app_3.employees;

import android.util.Log;
/**
import com.DT170G.G3.android_app_3.ApiClient;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeesRepository {
    public interface EmployeesCallback {
        void onSuccess(List<Employee> waiters);
        void onError(String message);
    }


    public void getEmployee(EmployeesCallback cb) {
        Call<List<Employee>> listEmployees = ApiClient.employeesApi().getEmployees();
        listEmployees.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (response.body() == null) {
                    Log.d("API Response","Response call is null");
                }
                else {
                    Log.d("API Response", "Code: " + response.code());
                    cb.onSuccess(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                //handle error code
                Log.d("Employee API onFailure: ", t.toString());
                cb.onError(t.getMessage());
            }
        });
    }
}
*/