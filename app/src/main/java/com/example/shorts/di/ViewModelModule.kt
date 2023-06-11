package com.example.shorts.di

import android.os.Handler
import com.example.shorts.ui.view_model.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { (handler: Handler) ->
        MainViewModel(handler,get(),get(),get())
    }
}

