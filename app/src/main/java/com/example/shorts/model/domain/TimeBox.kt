package com.example.shorts.model.domain

data class TimeBox(

    internal var aboveTime: Int = 0,
    internal var currentTime: Int = 0,
    internal var belowTime: Int = 0,

    internal var firstStart: Boolean = true,
    internal var text: String = "Let's begin",
)
