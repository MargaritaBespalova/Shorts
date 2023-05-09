package com.example.shorts.repository.model


data class TimestampsDto(
    internal var lastTime: Int = 0,
    internal var currentTime: Int = 0,
    internal var oldestTime: Int = 0,
)
