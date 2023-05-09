package com.example.shorts.domain.model

data class Timestamps(
    internal var aboveTime: Int = 0,
    internal var currentTime: Int = 0,
    internal var belowTime: Int = 0,
    internal var retryStart: Boolean = false,
    internal var text: String = "Let's begin",
)
