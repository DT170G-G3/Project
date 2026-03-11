package com.DT170G.G3.android_app_3.shifts;

import android.util.Log;
import com.DT170G.G3.android_app_3.*;
import com.DT170G.G3.android_app_3.employees.Employee;
import com.DT170G.G3.android_app_3.employees.EmployeesRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShiftsRepository {
    public interface GetCallback {
        void onSuccess(List<Shift> shifts);
        void onError(String message);
    }
    public interface PostCallback {
        void onSuccess(ShiftSwap shiftSwap);
        void onError(String message);
    }
    public interface PutCallback {
        void onSuccess();
        void onError(String message);
    }
    public interface GetShiftSwapCallback {
        void onSuccess(List<ShiftSwap> shiftSwaps);
        void onError(String message);
    }

    public void getShiftSwaps(GetShiftSwapCallback cb) {
        Call<List<ShiftSwap>> listShiftSwaps = ApiClient.shiftsApi().getShiftSwaps();
        listShiftSwaps.enqueue(new Callback<List<ShiftSwap>>() {
            @Override
            public void onResponse(Call<List<ShiftSwap>> call, Response<List<ShiftSwap>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    cb.onSuccess(response.body());
                }
                else {
                    cb.onError("HTTP " + response.body());
                }
            }
            @Override
            public void onFailure(Call<List<ShiftSwap>> call, Throwable t) {
                //handle error code
                Log.d("Shift API onFailure: ", t.toString());
                cb.onError(t.getMessage());
            }
        });
    }

    public void putShiftUpdate(int id, ShiftUpdate update, PutCallback cb) {
        Call<Void> call = ApiClient.shiftsApi().putShiftUpdate(id, update);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    cb.onSuccess();
                } else {
                    cb.onError("HTTP " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                cb.onError(t.getMessage());
            }
        });
    }
    public void postShiftSwap(ShiftSwap shiftSwap, PostCallback cb) {
        Call<Void> call = ApiClient.shiftsApi().postShiftSwap(shiftSwap);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    cb.onSuccess(shiftSwap);
                } else {
                    cb.onError("HTTP " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                cb.onError(t.getMessage());
            }
        });
    }
    public void getShifts(GetCallback cb) {
        Call<List<Shift>> listShifts = ApiClient.shiftsApi().getShifts();
        listShifts.enqueue(new Callback<List<Shift>>() {
            @Override
            public void onResponse(Call<List<Shift>> call, Response<List<Shift>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    cb.onSuccess(response.body());
                }
                else {
                    cb.onError("HTTP " + response.body());
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
    public void getShiftsByDate(String date, GetCallback cb) {
        Call<List<Shift>> listShifts = ApiClient.shiftsApi().getShiftsByDate(date);
        listShifts.enqueue(new Callback<List<Shift>>() {
            @Override
            public void onResponse(Call<List<Shift>> call, Response<List<Shift>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    cb.onSuccess(response.body());
                }
                else {
                    cb.onError("HTTP " + response.body());
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
