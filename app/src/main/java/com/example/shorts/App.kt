package com.example.shorts

import android.app.Application
import com.example.shorts.domain.model.Timestamps
import com.example.shorts.repository.TimestampsRepositoryImpl
import com.example.shorts.repository.model.TimestampsDto
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