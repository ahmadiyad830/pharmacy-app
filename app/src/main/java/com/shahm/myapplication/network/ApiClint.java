package com.shahm.myapplication.network;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClint {
    private static Retrofit retrofit;
    private static final String linkApi = "https://www.blacktools.io/sapi/";
    private static OkHttpClient okHttpClient;

    public static OkHttpClient getOkHttpClient() {

        if (okHttpClient==null){
            okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(linkApi)
                    .build();
            okHttpClient.newCall(request);
        }
        return okHttpClient;
    }

    public static Retrofit getRetrofit() {
        OkHttpClient okHttpClient = new OkHttpClient();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(linkApi)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
//https://www.episodate.com/api/most-popular?page=1
//https://www.blacktools.io/api/?drug=3
//api.php