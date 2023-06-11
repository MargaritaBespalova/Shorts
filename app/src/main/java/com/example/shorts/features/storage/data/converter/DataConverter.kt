package com.example.shorts.features.storage.data.converter


interface DataConverter {
    fun <T> dataToJson(data: T): String
    fun <T> dataFromJson(json: String, type: Class<T>): T
}