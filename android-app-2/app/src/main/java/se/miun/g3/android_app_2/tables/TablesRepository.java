package se.miun.g3.android_app_2.tables;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import se.miun.g3.android_app_2.ApiClient;

public class TablesRepository {

    public interface GetCallback {
        void onSuccess(List<Table> tables);
        void onError(String message);
    }


    public void getTables(TablesRepository.GetCallback cb) {
        Call<List<Table>> call = ApiClient.tablesApi().getTables();
        call.enqueue(new Callback<List<Table>>() {
            @Override
            public void onResponse(Call<List<Table>> call, Response<List<Table>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    cb.onSuccess(response.body());
                }
                else {
                    cb.onError("HTTP " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Table>> call, Throwable t) {
                cb.onError(t.getMessage());
            }
        });
    }
}