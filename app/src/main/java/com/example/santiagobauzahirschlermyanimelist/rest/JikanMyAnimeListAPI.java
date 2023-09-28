package com.example.santiagobauzahirschlermyanimelist.rest;

import com.example.santiagobauzahirschlermyanimelist.rest.interfaces.IJikanMyAnimeListEndPoints;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class JikanMyAnimeListAPI {

    //https://api.jikan.moe/v4/

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.jikan.moe/v4/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //Rxjava "convert gson"
            .build();
    private static IJikanMyAnimeListEndPoints service = retrofit.create(IJikanMyAnimeListEndPoints.class);

    public static IJikanMyAnimeListEndPoints getInstance() {
        if (service == null)
            service = retrofit.create(IJikanMyAnimeListEndPoints.class);
        return service;
    }

}
