package com.example.shorts.features.recovery_period.domain.impl

import com.example.shorts.features.recovery_period.domain.api.RecoveryInteractor
import com.example.shorts.features.recovery_period.model.CompletionStatus
import com.example.shorts.features.shared_preferences.domain.api.LocalStorage

class RecoveryInteractorImpl(
    private val localStorage: LocalStorage
): RecoveryInteractor {

    override fun getRemainingTime(key: String): Int {
        val lastTime =
            localStorage.readData(key = key, defaultValue = CompletionStatus()).lastEndTime
        val currentTime = System.currentTimeMillis() / 1000L
        val elapsedTime = (lastTime + 60) - currentTime
        return if (elapsedTime > 0) elapsedTime.toInt() else 0
    }

    override fun <T> getDataFromSharedPref(key: String, defaultValue: T): T {
        return localStorage.readData(key = key, defaultValue = defaultValue)
    }

    override fun <T> saveDataInSharedPref(key: String, data: T) {
        localStorage.writeData(key = key, data = data)
    }
}