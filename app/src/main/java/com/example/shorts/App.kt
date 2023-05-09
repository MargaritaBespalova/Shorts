package com.example.shorts

import android.app.Application
import com.example.shorts.data.TimestampsRepositoryImpl
import com.google.gson.Gson

class App: Application() {
    companion object{
        lateinit var instance: App
        private set
        const val TIMESTAMPS_PREF = "timestamps_preferences"
    }
    private val gson = Gson()
    internal lateinit var timestamps: TimestampsRepositoryImpl

    override fun onCreate() {
        super.onCreate()
        instance = this
        timestamps = TimestampsRepositoryImpl(
            gson = Gson(),
            getSharedPreferences(TIMESTAMPS_PREF, MODE_PRIVATE)
        )
    }
}