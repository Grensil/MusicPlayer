package com.example.musicplayer.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musicplayer.repository.MusicRepository

class MusicViewFactory(private val musicRepository: MusicRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val repositoryIns = modelClass.getConstructor(MusicRepository::class.java).newInstance(musicRepository);
        return repositoryIns;
    }
}