package com.example.musicplayer.Service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BaseWebService {
    fun getWebClient(baseURL:String) : Retrofit{
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit;
    }
}