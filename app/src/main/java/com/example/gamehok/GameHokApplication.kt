package com.example.gamehok

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class GameHokApplication : Application() {

    companion object{
        @JvmStatic
        var instance :GameHokApplication?= null
    }

    override fun onCreate() {
        super.onCreate()
        instance=this
    }

}