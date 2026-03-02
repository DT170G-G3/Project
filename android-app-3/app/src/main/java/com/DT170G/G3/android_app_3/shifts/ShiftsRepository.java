package com.DT170G.G3.android_app_3.shifts;

import android.util.Log;

import com.DT170G.G3.android_app_3.ApiClient;
import com.DT170G.G3.android_app_3.employees.Employee;
import com.DT170G.G3.android_app_3.employees.EmployeesRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShiftsRepository {
    public interface ShiftsCallback {
        void onSuccess(List<Shift> shifts);
        void onError(String message);
    }


    public void getShifts(ShiftsRepository.ShiftsCallback cb) {
        Call<List<Shift>> listShifts = ApiClient.shiftsApi().getShifts();
        listShifts.enqueue(new Callback<List<Shift>>() {
            @Override
            public void onResponse(Call<List<Shift>> call, Response<List<Shift>> response) {
                if (response.body() == null) {
                    Log.d("API Response","Response call is null");
                }
                else {
                    Log.d("API Response", "Code: " + response.code());
                    cb.onSuccess(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<Shift>> call, Throwable t) {
                //handle error code
                Log.d("Shift API onFailure: ", t.toString());
                cb.onError(t.getMessage());
            }
        });
    }
}
