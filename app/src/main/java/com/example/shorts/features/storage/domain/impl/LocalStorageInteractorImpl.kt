package com.example.shorts.features.storage.domain.impl


import com.example.shorts.features.storage.domain.api.LocalStorage
import com.example.shorts.features.storage.domain.api.LocalStorageInteractor

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