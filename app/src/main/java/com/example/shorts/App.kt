package com.example.shorts

import android.app.Application
import com.example.shorts.di.dataModule
import com.example.shorts.di.domainModule
import com.example.shorts.di.viewModelModule
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules (
                dataModule,
                domainModule,
                viewModelModule
            )
        }
    }
}