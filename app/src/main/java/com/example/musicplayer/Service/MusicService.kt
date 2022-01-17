package com.example.musicplayer.Service

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MusicService {

    @GET("2020-flo/song.json")
    fun listMusics() : Call<MusicEntity>
}

object MusicClient{
    private val baseURL:String = "https://grepp-programmers-challenges.s3.ap-northeast-2.amazonaws.com/"
    val client = BaseWebService().getWebClient(baseURL).create(MusicService::class.java);
}
