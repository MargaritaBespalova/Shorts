package com.example.shorts.ui.view_model

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.shorts.model.domain.TimeBox
import com.example.shorts.features.shared_preferences.domain.impl.LocalStorageInteractorImpl
import androidx.compose.runtime.setValue
import com.example.shorts.features.shared_preferences.domain.api.LocalStorage
import com.example.shorts.utils.DELAY_1000


class MainViewModel(
    private val localStorageInteractor: LocalStorageInteractorImpl,
): ViewModel() {


    private val handler = Handler(Looper.getMainLooper())
    var stopButtonState by mutableStateOf(false)
        private set
    var timeBox by mutableStateOf(localStorageInteractor.getTimeBoxState(key = TIME_BOX_KEY))
        private set
    var tempTimeBox by mutableStateOf(TimeBox())
        private set


    private var exerciseEnable: Boolean = false
    init {
        val currentTime = (System.currentTimeMillis() / 3600000).toInt()
        val saveTime =
            localStorageInteractor.getTimeBoxState(key = TEMP_TIME_BOX_KEY).lastExerciseEndTime
        exerciseEnable = (currentTime - saveTime - 36) > 0
    }


    fun changeVisibleState() { stopButtonState = !stopButtonState }

    fun startIncrement() {
        if (exerciseEnable) {
            changeVisibleState()
            handler.post(object : Runnable {
                override fun run() {
                    timeBox = timeBox.copy(currentTime = timeBox.currentTime + 1)
                    handler.postDelayed(this, DELAY_1000)
                }
            })
        }
    }
    fun stopExercise() {
        changeVisibleState()
        handler.removeCallbacksAndMessages(null)
        tempTimeBox = timeBox.copy(
            aboveTime = (timeBox.currentTime * 0.9).toInt(),
            currentTime = (timeBox.currentTime * 0.8).toInt(),
            belowTime = timeBox.currentTime + 3,
            firstStart = false,
            text = "Start exercise",
            lastExerciseEndTime = (System.currentTimeMillis() / 3600000).toInt()
        )
        localStorageInteractor.saveTimeBoxState(
            key = TEMP_TIME_BOX_KEY,
            timeBoxState = tempTimeBox
        )
    }

    fun onLongPress() {
        localStorageInteractor.clearPreferencesByKey(key = TEMP_TIME_BOX_KEY)
        timeBox = TimeBox()
    }

    companion object {
        private const val STOP_BTN_VISIBILITY_KEY = "stop_btn_visibility_pref"
        private const val TIME_BOX_KEY = "time_box_preferences"
        private const val TEMP_TIME_BOX_KEY = "temp_time_box_preferences"
    }
}