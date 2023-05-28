package com.example.shorts.features.shared_preferences.domain.api

interface LocalStorageInteractor {

    fun <T> getDataFromSharedPref (key: String, defaultValue: T): T
    fun <T> saveDataInSharedPref(key: String, data: T)

    fun clearPreferencesByKey(key: String)
}