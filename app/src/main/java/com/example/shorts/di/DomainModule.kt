package com.example.shorts.di

import com.example.shorts.features.recovery_period.domain.api.RecoveryInteractor
import com.example.shorts.features.recovery_period.domain.impl.RecoveryInteractorImpl
import com.example.shorts.features.storage.domain.api.LocalStorageInteractor
import com.example.shorts.features.storage.domain.impl.LocalStorageInteractorImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val domainModule = module {

    singleOf(::LocalStorageInteractorImpl).bind<LocalStorageInteractor>()
    singleOf(::RecoveryInteractorImpl).bind<RecoveryInteractor>()
}