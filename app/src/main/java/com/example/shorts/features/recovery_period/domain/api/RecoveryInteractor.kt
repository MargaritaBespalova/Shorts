package com.example.shorts.features.recovery_period.domain.api

interface RecoveryInteractor {

    fun getRecoverTime(key: String): Int

    fun <T> getDataFromSharedPref(key: String, defaultValue: T): T
    fun <T> saveDataInSharedPref(key: String, data: T)
}