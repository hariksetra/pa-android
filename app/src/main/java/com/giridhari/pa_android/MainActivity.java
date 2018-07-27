package com.giridhari.pa_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.giridhari.pa_android.rest.config.RetrofitInitializer;
import com.giridhari.pa_android.rest.service.PreachingAssistantService;
import com.giridhari.preachingassistant.rest.model.response.BaseDataResponse;
import com.giridhari.preachingassistant.rest.model.useraccount.UserAccountDetailEntity;
import com.giridhari.preachingassistant.rest.model.useraccount.UserLoginResponse;
import com.giridhari.preachingassistant.rest.model.useraccount.UserLoginResponseEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private PreachingAssistantService preachingAssistantService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preachingAssistantService = new RetrofitInitializer().getPreachingAssistantService();
        preachingAssistantService.login("admin").enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                UserLoginResponseEntity responseEntity = response.body().getData();
                Toast.makeText(MainActivity.this, responseEntity.getDevoteeName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
