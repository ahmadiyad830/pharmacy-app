package com.shahm.myapplication.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClint {
    private static Retrofit retrofit;
    private static final String linkApi = "https://www.blacktools.io/sapi/";
    private static OkHttpClient okHttpClient;

    public static Retrofit getRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient okHttpClient = new OkHttpClient();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(linkApi)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

}
//https://www.episodate.com/api/most-popular?page=1
//https://www.blacktools.io/api/?drug=3
//api.php