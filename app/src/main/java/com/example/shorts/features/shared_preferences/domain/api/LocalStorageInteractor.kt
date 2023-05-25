package com.example.shorts.features.shared_preferences.domain.api

import com.example.shorts.model.domain.TimeBox


interface LocalStorageInteractor {

    fun getBooleanState (key: String): Boolean
    fun saveBooleanState(key: String, nightThemeState: Boolean)

    fun getTimeBoxState (key: String): TimeBox
    fun saveTimeBoxState(key: String, timeBoxState: TimeBox)

    fun clearPreferencesByKey(key: String)
}