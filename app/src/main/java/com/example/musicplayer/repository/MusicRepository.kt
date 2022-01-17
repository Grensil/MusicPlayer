package com.example.musicplayer.repository

import com.example.musicplayer.Service.MusicClient
import com.example.musicplayer.Service.MusicEntity
import retrofit2.Call

class MusicRepository() {
    private val webClient = MusicClient.client;


    fun seachMusicList() = webClient.listMusics();
}