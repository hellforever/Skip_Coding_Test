package com.github.hellforever.skiptest1.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {
    private static volatile Retrofit instance;

    public static Retrofit getRetrofit() {
        if (instance == null) {
            synchronized (RetrofitSingleton.class) {
                if (instance == null) {
                    instance = new Retrofit.Builder()
                            .baseUrl("https://uk.api.just-eat.io/")
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                                    .create()))
                            .build();
                }
            }
        }
        return instance;
    }

}
