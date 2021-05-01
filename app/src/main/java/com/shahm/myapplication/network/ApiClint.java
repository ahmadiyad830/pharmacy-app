package com.shahm.myapplication.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClint {
    private static Retrofit retrofit;
    private static final String linkApi = "https://www.blacktools.io/sapi/";
    public static Retrofit getRetrofit() {
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