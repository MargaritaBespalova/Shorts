package com.example.shorts.di

import androidx.appcompat.app.AppCompatActivity
import com.example.shorts.features.storage.data.LocalStorageImpl
import com.example.shorts.features.storage.data.converter.DataConverter
import com.example.shorts.features.storage.data.converter.GsonDataConverter
import com.example.shorts.features.storage.domain.api.LocalStorage
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

private const val APP_PREF_KEY = "shorts_app_preferences"

val dataModule = module {

    single { Gson() }
    single { androidContext().getSharedPreferences(APP_PREF_KEY, AppCompatActivity.MODE_PRIVATE) }
    single { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) }
    single {
        val logging = get<HttpLoggingInterceptor>()
        OkHttpClient.Builder().addInterceptor(logging).build()
    }
//    single<ApiITunes> {
//        //val client = get<OkHttpClient>()
//        Retrofit.Builder()
//            .baseUrl(RetrofitNetworkClient.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            //.client(client)
//            .build()
//            .create(ApiITunes::class.java)
//    }
    singleOf(::LocalStorageImpl).bind<LocalStorage>()
    singleOf(::GsonDataConverter).bind<DataConverter>()

}