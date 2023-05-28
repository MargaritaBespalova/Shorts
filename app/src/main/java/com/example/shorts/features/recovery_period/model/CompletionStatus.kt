package com.example.shorts.features.recovery_period.model

data class CompletionStatus(

    val outcome: Int = 0,
    val lastEndTime: Int = 0,
    val newWorkoutStage: Boolean = true,
)
