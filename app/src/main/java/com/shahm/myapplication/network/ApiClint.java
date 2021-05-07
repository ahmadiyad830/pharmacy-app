package com.shahm.myapplication.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClint {
    private static Retrofit retrofit;
    private static final String linkApi = "https://www.blacktools.io/sapi/";


    public static Retrofit getRetrofit() {

//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addNetworkInterceptor(chain -> {
//                    Request.Builder builder = chain.request().newBuilder();
//                    builder.addHeader("Accept-Language", Locale.getDefault().getDisplayLanguage());
//                    Request request = builder.build();
//                    return chain.proceed(request);
//                }).build();
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(linkApi)
                    .addConverterFactory(GsonConverterFactory.create())
//                    .client(okHttpClient)
//                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

}
//https://www.episodate.com/api/most-popular?page=1
//https://www.blacktools.io/api/?drug=3
//api.php