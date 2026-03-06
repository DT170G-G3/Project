package com.DT170G.G3.android_app_3.shifts;

import android.util.Log;
import com.DT170G.G3.android_app_3.*;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShiftsRepository {
    public interface GetCallback {
        void onSuccess(List<Shift> shifts);
        void onError(String message);
    }

    public void getShifts(ShiftsRepository.GetCallback cb) {
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
}
