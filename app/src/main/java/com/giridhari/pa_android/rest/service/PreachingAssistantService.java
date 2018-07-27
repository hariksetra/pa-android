package com.giridhari.pa_android.rest.service;

import com.giridhari.preachingassistant.rest.model.useraccount.UserLoginResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PreachingAssistantService {

    @GET("/login")
    Call<UserLoginResponse> login(@Query("username") String username);

}
