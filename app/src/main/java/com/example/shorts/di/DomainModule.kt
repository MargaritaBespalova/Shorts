package com.example.shorts.di

import com.example.shorts.features.shared_preferences.domain.api.LocalStorageInteractor
import com.example.shorts.features.shared_preferences.domain.impl.LocalStorageInteractorImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val domainModule = module {

    singleOf(::LocalStorageInteractorImpl).bind<LocalStorageInteractor>()
}