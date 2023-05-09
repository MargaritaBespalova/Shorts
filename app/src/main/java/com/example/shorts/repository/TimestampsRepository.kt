package com.example.shorts.repository

import com.example.shorts.domain.model.Timestamps

interface TimestampsRepository {
    fun addTimestamps(timestamps: Timestamps)
    fun getTimestamps(): Timestamps
    fun clearTimestamps()
}