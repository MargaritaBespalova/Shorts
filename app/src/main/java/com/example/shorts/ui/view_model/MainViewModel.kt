package com.example.shorts.ui.view_model

import android.app.Application
import android.os.Handler
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.shorts.R
import com.example.shorts.features.recovery_period.domain.api.RecoveryInteractor
import com.example.shorts.features.recovery_period.model.CompletionStatus
import com.example.shorts.features.storage.domain.api.LocalStorageInteractor
import com.example.shorts.features.storage.domain.model.TimeBox
import com.example.shorts.utils.DELAY_1000
import com.example.shorts.utils.FINISHED
import com.example.shorts.utils.toTimeFormat

const val TAG = "qqq"
class MainViewModel(
    private val handler: Handler,
    private val localStorageInteractor: LocalStorageInteractor,
    private val recoveryInteractor: RecoveryInteractor,
    private val application: Application,
): AndroidViewModel(application) {


    var startPhrase by mutableStateOf(application.getString(R.string.greeting))
        private set
    var stopButtonState by mutableStateOf(false)
        private set
    var checkIconState by mutableStateOf(0.1f)
        private set
    var timeBox by mutableStateOf(
        localStorageInteractor.getDataFromSharedPref(key = TIME_BOX_KEY, defaultValue = TimeBox()))
        private set
    var recoverTime by mutableStateOf(FINISHED)
        private set

    init { checkTrainingAvailable() }

    private fun checkTrainingAvailable() {
        var restTime = recoveryInteractor.getRecoverTime(key = COMPLETION_STATUS_KEY)
        if (restTime > 0) {
            isTrainingDone(done = true)
        }
        handler.post(object : Runnable {
            override fun run() {
                if (restTime > 0) {
                    restTime -= 1
                    recoverTime = restTime.toTimeFormat()
                    handler.postDelayed(this, DELAY_1000)
                } else {
                    handler.removeCallbacksAndMessages(null)
                    isTrainingDone(done = false)
                }
            }
        })
    }

    fun getText(): String {
        return if (timeBox.firstStart) application.getString(R.string.stop)
        else application.getString(R.string.maxed_out)
    }

    private fun isTrainingDone(done: Boolean) {
        if (done) {
            startPhrase = application.getString(R.string.done)
            checkIconState = 1f
        } else {
            startPhrase = application.getString(R.string.start)
            checkIconState = 0.1f
        }
    }

    private fun visibilityStopButton() {
        stopButtonState = !stopButtonState
    }

    fun startTraining() {
        if (recoverTime == FINISHED) {
            visibilityStopButton()
            if (timeBox.firstStart) {
                firstTraining()
            } else {
                standardTraining()
            }
        }
    }

    fun stopFirstTraining() {
        createAndSaveNewTrainingPlan(timeBox.firstStart)
        stopTrainingAndRest(newTrainingStage = false)
    }

    private fun firstTraining() {
        handler.post(object : Runnable {
            override fun run() {
                timeBox = timeBox.copy(currentTime = timeBox.currentTime + 1)
                startPhrase = modifyPrompt(timeBox.currentTime)
                handler.postDelayed(this, DELAY_1000)
            }
        })
    }

    private fun standardTraining() {
        val lastCurrentTime = timeBox.currentTime
        handler.post(object : Runnable {
            override fun run() {
                if (timeBox.currentTime != 0) {
                    timeBox = timeBox.copy(currentTime = timeBox.currentTime - 1)
                    startPhrase = modifyPrompt(timeBox.currentTime)
                    handler.postDelayed(this, DELAY_1000)
                }
                else if (lastCurrentTime > timeBox.aboveTime && lastCurrentTime >timeBox.belowTime) {
                    createAndSaveNewTrainingPlan(timeBox.firstStart)
                    stopTrainingAndRest(newTrainingStage = true)
                }
                else {
                    fixAndSaveTrainingPlan()
                    stopTrainingAndRest(newTrainingStage = false)
                }
            }
        })
    }

    private fun modifyPrompt(time: Int): String {
        return if (time % 8 in (6..7)) application.getString(R.string.do_it)
        else application.getString(R.string.stay)
    }

    private fun stopTrainingAndRest(newTrainingStage: Boolean) {
        visibilityStopButton()
        handler.removeCallbacksAndMessages(null)
        startRecoveryTimer(newTrainingStage)
    }

    fun onLongPress() {
        stopTrainingAndRest(newTrainingStage = false)
        localStorageInteractor.clearPreferencesByKey(key = TIME_BOX_KEY)
        timeBox = TimeBox()
    }

    private fun createAndSaveNewTrainingPlan(firstTraining: Boolean) {
        if (firstTraining) {
            timeBox.firstStart = false
            createNewTrainingPlan(timeBox.currentTime)
        } else {
            val lastTime = localStorageInteractor.getDataFromSharedPref(
                key = TIME_BOX_KEY,
                defaultValue = TimeBox()
            ).currentTime
            createNewTrainingPlan(lastTime - timeBox.currentTime)
        }
        saveNewTimeBox()
    }

    private fun createNewTrainingPlan(time: Int) {
        timeBox.apply {
            aboveTime = (time * 0.88).toInt()
            currentTime = (time * 0.75).toInt()
            belowTime = time + 2
        }
    }

    private fun fixAndSaveTrainingPlan() {
        val lastSavedTimeBox = localStorageInteractor.getDataFromSharedPref(
            key = TIME_BOX_KEY,
            defaultValue = TimeBox()
        )
        timeBox.apply {
            aboveTime = lastSavedTimeBox.belowTime
            currentTime = lastSavedTimeBox.aboveTime
            belowTime = lastSavedTimeBox.currentTime
        }
        saveNewTimeBox()
    }

    private fun saveNewTimeBox() {
        localStorageInteractor.saveDataInSharedPref(
            key = TIME_BOX_KEY,
            data = timeBox,
        )
    }

    private fun startRecoveryTimer(newTrainingStage: Boolean) {
        recoveryInteractor.saveDataInSharedPref(
            key = COMPLETION_STATUS_KEY,
            data = CompletionStatus(
                outcome = timeBox.currentTime,
                lastEndTime = (System.currentTimeMillis() / 1000).toInt(),
                newTrainingStage = newTrainingStage
            )
        )
        checkTrainingAvailable()
    }

    companion object {
        private const val COMPLETION_STATUS_KEY = "completion_status_key"
        private const val TIME_BOX_KEY = "time_box_preferences"
    }
}