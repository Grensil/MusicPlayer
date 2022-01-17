package com.example.musicplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MusicPlayer) // 원래 테마로 돌아옴
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // activity main -> 네비게이션(mainfragment 호출)

    }
}