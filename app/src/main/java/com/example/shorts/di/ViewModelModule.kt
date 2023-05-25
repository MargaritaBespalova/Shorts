package com.example.shorts.di

import androidx.lifecycle.ViewModel
import com.example.shorts.ui.view_model.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val viewModelModule = module {

    viewModelOf(::MainViewModel).bind<ViewModel>()
}
