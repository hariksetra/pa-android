package com.giridhari.pa_android.rest.config;

import com.giridhari.pa_android.rest.service.PreachingAssistantService;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInitializer {

    public static final String PREACHING_ASSISTANT_SERVICE = "http://demo.sacredprayer.org:8080";

    private PreachingAssistantService preachingAssistantService;

    public PreachingAssistantService getPreachingAssistantService() {
        if(this.preachingAssistantService == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                  Request original = chain.request();

                  Request request = original.newBuilder()
                          .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                          .method(original.method(), original.body())
                          .build();

                  return chain.proceed(request);
                }
            });

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(PREACHING_ASSISTANT_SERVICE)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            preachingAssistantService = retrofit.create(PreachingAssistantService.class);
        }

        return preachingAssistantService;
    }

}
