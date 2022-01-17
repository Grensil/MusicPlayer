package com.example.musicplayer.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicplayer.Service.MusicEntity
import com.example.musicplayer.repository.MusicRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MusicViewModel(private val musicRepos:MusicRepository) : ViewModel() {
    var data = MutableLiveData<MusicEntity>();

    fun searchMusicList(){
        musicRepos.seachMusicList().enqueue(object : Callback<MusicEntity>{
            override fun onFailure(call: Call<MusicEntity>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<MusicEntity>, response: Response<MusicEntity>) {

                if(response.code() == 200){
                    data.postValue(response.body() as MusicEntity)
                    //Log.i("success",response.body().toString())

                }

            }
        })
    }
}