package com.example.shorts.features.shared_preferences.domain.impl


import com.example.shorts.features.shared_preferences.domain.api.LocalStorage
import com.example.shorts.features.shared_preferences.domain.api.LocalStorageInteractor
import com.example.shorts.model.domain.TimeBox

class LocalStorageInteractorImpl(
    private val localStorage: LocalStorage
): LocalStorageInteractor {

    override fun <T> getDataFromSharedPref(key: String, defaultValue: T): T {
        return localStorage.readData(key = key, defaultValue = defaultValue)
    }

    override fun <T> saveDataInSharedPref(key: String, data: T) {
        localStorage.writeData(key = key, data = data)
    }

    override fun clearPreferencesByKey(key: String) {
        localStorage.writeData(key = key, data = null)
    }

}