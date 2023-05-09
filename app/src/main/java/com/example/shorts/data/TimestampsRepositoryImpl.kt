package com.example.shorts.data

import android.content.SharedPreferences
import com.example.shorts.domain.model.Timestamps
import com.example.shorts.data.model.TimestampsDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TimestampsRepositoryImpl(
    private val gson: Gson,
    private val sharedPreferences: SharedPreferences,
): TimestampsRepository {

    companion object { private const val TIMESTAMPS_KEY = "timestamps" }

    override fun addTimestamps(timestamps: Timestamps) {
        mapToTimestampsDto(timestamps)
        sharedPreferences.edit().putString(TIMESTAMPS_KEY, gson.toJson(timestamps)).apply()
    }

    override fun getTimestamps(): Timestamps {
        val modelDto = getJsonString()?.toTimestampsDto() ?: TimestampsDto()
        return mapToTimestamps(modelDto)
    }

    override fun clearTimestamps() {
        TODO("Not yet implemented")
    }


    private fun getJsonString(): String? =
        sharedPreferences.getString(TIMESTAMPS_KEY, null)

    private fun String.toTimestampsDto(): TimestampsDto =
        gson.fromJson(this, object: TypeToken<TimestampsDto>() {}.type)

    private fun mapToTimestamps(timestamps: TimestampsDto): Timestamps {
        return Timestamps(
            aboveTime = timestamps.lastTime,
            currentTime = timestamps.currentTime,
            belowTime = timestamps.oldestTime,
        )
    }

    private fun mapToTimestampsDto(timestamps: Timestamps): TimestampsDto {
        return TimestampsDto(
            lastTime = timestamps.aboveTime,
            currentTime = timestamps.currentTime,
            oldestTime = timestamps.belowTime,
        )
    }
}