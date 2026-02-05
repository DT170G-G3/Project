package com.DT170G.G3.android_app_1;

import retrofit2.Call;

import java.util.List;
import retrofit2.http.GET;

public interface MessageApi {
    @GET("messages")
    Call<List<Message>> getMessages();
}
