package com.example.shorts

import android.app.Application
import com.example.shorts.data.TimestampsRepositoryImpl
import com.google.gson.Gson

class App: Application() {
    companion object{
        private const val TIMESTAMPS_PREF = "timestamps_preferences"
        internal lateinit var instance: App
        private set
    }
    internal lateinit var timestamps: TimestampsRepositoryImpl

    override fun onCreate() {
        super.onCreate()
        instance = this
        timestamps = TimestampsRepositoryImpl(gson = Gson(),getSharedPreferences(TIMESTAMPS_PREF, MODE_PRIVATE))
    }
}