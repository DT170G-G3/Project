package com.DT170G.G3.android_app_3.employees;

import android.util.Log;

import com.DT170G.G3.android_app_3.*;
import com.DT170G.G3.android_app_3.shifts.Shift;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeesRepository {
    public interface GetCallback {
        void onSuccess(List<Employee> employees);
        void onError(String message);
    }

    public void getEmployees(EmployeesRepository.GetCallback cb) {
        Call<List<Employee>> listEmployees = ApiClient.employeesApi().getEmployees();
        listEmployees.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    cb.onSuccess(response.body());
                }
                else {
                    cb.onError("HTTP " + response.body());
                }
            }
            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                //handle error code
                Log.d("Shift API onFailure: ", t.toString());
                cb.onError(t.getMessage());
            }
        });
    }
}

