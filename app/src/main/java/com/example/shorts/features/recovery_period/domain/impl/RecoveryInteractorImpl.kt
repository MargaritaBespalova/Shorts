package com.example.shorts.features.recovery_period.domain.impl

import com.example.shorts.features.recovery_period.domain.api.RecoveryInteractor
import com.example.shorts.features.recovery_period.model.CompletionStatus
import com.example.shorts.features.storage.domain.api.LocalStorage

class RecoveryInteractorImpl(
    private val localStorage: LocalStorage
): RecoveryInteractor {

    override fun getRecoverTime(key: String): Int {
        val completionStatus = localStorage.readData(key = key, defaultValue = CompletionStatus())
        val currentTime = System.currentTimeMillis() / 1000L
        val elapsedTime = if (completionStatus.newTrainingStage) {
            completionStatus.lastEndTime + BIG_RECOVER_TIME - currentTime
        } else {
            completionStatus.lastEndTime + NORMAL_RECOVER_TIME - currentTime
        }
        return if (elapsedTime > 0) elapsedTime.toInt() else 0
    }

    override fun <T> getDataFromSharedPref(key: String, defaultValue: T): T {
        return localStorage.readData(key = key, defaultValue = defaultValue)
    }

    override fun <T> saveDataInSharedPref(key: String, data: T) {
        localStorage.writeData(key = key, data = data)
    }

    companion object {
        private const val BIG_RECOVER_TIME = 144000
        private const val NORMAL_RECOVER_TIME = 72000
    }
}