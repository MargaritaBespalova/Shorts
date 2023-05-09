package com.example.shorts.data

import com.example.shorts.domain.model.Timestamps

interface TimestampsRepository {
    fun addTimestamps(timestamps: Timestamps)
    fun getTimestamps(): Timestamps
    fun clearTimestamps()
}