package com.example.shorts.features.storage.domain.model

data class TimeBox(

    internal var firstStart: Boolean = true,

    internal var aboveTime: Int = 0,
    internal var currentTime: Int = 0,
    internal var belowTime: Int = 0,
)
