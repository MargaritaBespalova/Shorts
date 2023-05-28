package com.example.shorts.ui.view_model

import android.os.Handler
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.shorts.model.domain.TimeBox
import androidx.compose.runtime.setValue
import com.example.shorts.features.recovery_period.domain.api.RecoveryInteractor
import com.example.shorts.features.recovery_period.model.CompletionStatus
import com.example.shorts.features.shared_preferences.domain.api.LocalStorageInteractor
import com.example.shorts.utils.DELAY_1000
import com.example.shorts.utils.ENABLE
import com.example.shorts.utils.toTimeFormat

class MainViewModel(
    private val handler: Handler,
    private val localStorageInteractor: LocalStorageInteractor,
    private val recoveryInteractor: RecoveryInteractor,
): ViewModel() {


    var stopButtonState by mutableStateOf(false)
        private set
    var timeBox by mutableStateOf(
        localStorageInteractor.getDataFromSharedPref(key = TIME_BOX_KEY, defaultValue = TimeBox()))
        private set
    var recoverTime by mutableStateOf(ENABLE)
        private set

    init { countdownRecoverTime() }

    private fun countdownRecoverTime() {
        var time = recoveryInteractor.getRemainingTime(key = COMPLETION_STATUS_KEY)
        handler.post(object : Runnable {
            override fun run() {
                if (time > 0) {
                    time -= 1
                    recoverTime = time.toTimeFormat()
                    handler.postDelayed(this, DELAY_1000)
                } else handler.removeCallbacksAndMessages(null)
            }
        })
    }

    private fun visibilityStopButton() { stopButtonState = !stopButtonState }

    fun startCountUpTrainingTime() {
        if (recoverTime == ENABLE) {
            visibilityStopButton()
            handler.post(object : Runnable {
                override fun run() {
                    timeBox = timeBox.copy(currentTime = timeBox.currentTime + 1)
                    handler.postDelayed(this, DELAY_1000)
                }
            })
        }
    }
    fun stopTraining() {
        visibilityStopButton()
        handler.removeCallbacksAndMessages(null)
        //visibilityDoneMark()
        localStorageInteractor.saveDataInSharedPref(key = TIME_BOX_KEY, data = timeBox)
        visibilityTimer()


//        tempTimeBox = timeBox.copy(
//            aboveTime = (timeBox.currentTime * 0.9).toInt(),
//            currentTime = (timeBox.currentTime * 0.8).toInt(),
//            belowTime = timeBox.currentTime + 3,
//            firstStart = false,
//            text = "Start exercise",
//            lastExerciseEndTime = (System.currentTimeMillis() / 3600000).toInt()
//        )
    }

    private fun visibilityTimer() {
        //showTimer()
        recoveryInteractor.saveDataInSharedPref(
            key = COMPLETION_STATUS_KEY,
            data = CompletionStatus(
                outcome = timeBox.currentTime,
                lastEndTime = (System.currentTimeMillis() / 1000).toInt(),
                newWorkoutStage = true
            )
        )
        //recoverTime = recoveryInteractor.getRemainingTime(key = COMPLETION_STATUS_KEY)
        countdownRecoverTime()
    }

    private fun showTimer() {
        TODO("Not yet implemented")
    }

    private fun visibilityDoneMark() {
        TODO("Not yet implemented")
    }

    fun onLongPress() {
        localStorageInteractor.clearPreferencesByKey(key = TIME_BOX_KEY)
        timeBox = TimeBox()
    }

    companion object {
        private const val COMPLETION_STATUS_KEY = "completion_status_key"
        //private const val STOP_BTN_VISIBILITY_KEY = "stop_btn_visibility_pref"
        private const val TIME_BOX_KEY = "time_box_preferences"
    }
}