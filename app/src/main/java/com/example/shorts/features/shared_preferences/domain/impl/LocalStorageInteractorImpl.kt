package com.example.shorts.features.shared_preferences.domain.impl


import com.example.shorts.features.shared_preferences.domain.api.LocalStorage
import com.example.shorts.features.shared_preferences.domain.api.LocalStorageInteractor
import com.example.shorts.model.domain.TimeBox

class LocalStorageInteractorImpl(
    private val localStorage: LocalStorage
): LocalStorageInteractor {


    override fun getBooleanState(key: String): Boolean {
        return localStorage.readData(key = key, defaultValue = false)
    }

    override fun saveBooleanState(key: String, nightThemeState: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getTimeBoxState(key: String): TimeBox {
        return localStorage.readData(key = key, defaultValue = TimeBox())
    }

    override fun saveTimeBoxState(key: String, timeBoxState: TimeBox) {
        localStorage.writeData(key = key, data = timeBoxState)
    }

    override fun clearPreferencesByKey(key: String) {
        localStorage.writeData(key = key, data = null)
    }


}