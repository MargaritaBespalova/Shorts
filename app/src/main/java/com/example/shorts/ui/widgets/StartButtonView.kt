package com.example.shorts.ui.widgets

import android.os.Handler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shorts.R
import com.example.shorts.model.domain.TimeBox
import com.example.shorts.utils.DELAY_1000
import com.example.shorts.ui.theme.Orange
import com.example.shorts.ui.view_model.MainViewModel

@Composable
fun StartButton(
    viewModel: MainViewModel,
    handler: Handler,
    showStopButton: Boolean,
    timeBox: TimeBox,
    recoverTime: String
) {
    Column(
        modifier = Modifier
            .padding(32.dp)
    ) {
        Card(
            modifier = Modifier
                //.padding(32.dp)
                .clickable {
                    if (timeBox.firstStart) {
                        //viewModel.changeVisibleState()
                        onClickGo(viewModel, handler, showStopButton, timeBox)
                    } else {
                        //viewModel.changeVisibleState()
                        //onClickStart(handler, showStopButton, timeBox)
                    }
                },
            shape = RoundedCornerShape(10.dp),
            elevation = 15.dp,
        ) {
            Box(modifier = Modifier
                .background(if (timeBox.currentTime % 8 in (0..1)) Orange else Color.Cyan)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 4.dp, end = 16.dp, bottom = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "${timeBox.aboveTime}", color = Color.DarkGray, fontSize = 13.sp)
                        Text(text = "${timeBox.currentTime}", fontSize = 17.sp)
                        Text(text = "${timeBox.belowTime}", color = Color.DarkGray, fontSize = 13.sp)
                    }
                    Text(text = timeBox.text, fontSize = 24.sp)
                    Image(
                        painter = painterResource(id = R.drawable.checked),
                        contentDescription = "check",
                        modifier = Modifier.size(32.dp),
                        alignment = Alignment.CenterEnd,
                    )
                }
            }
        }
        RecoverTimer(time = recoverTime)
    }
}

private fun onClickGo(
    viewModel: MainViewModel,
    handler: Handler,
    showStopButton: Boolean,
    timeBox: TimeBox,
) {
    viewModel.startCountUpTrainingTime()
    //clickEnable.value = false
//    handler.post(object : Runnable {
//        override fun run() {
//            if (showStopButton) {
//
//                timeBox = timeBox.copy(currentTime = timeBox.currentTime + 1)
//                handler.postDelayed(this, DELAY_1000)
//            } else {
//                handler.removeCallbacksAndMessages(null)
//                timeBox.value = timeBox.value.copy(
//                    aboveTime = (timeBox.value.currentTime * 0.9).toInt(),
//                    currentTime = (timeBox.value.currentTime * 0.8).toInt(),
//                    belowTime = timeBox.value.currentTime + 3,
//                    firstStart = true,
//                    text = "Start exercise",
//                )
//                //App.instance.timestamps.addTimestamps(timeBox.value)
//            }
//        }
//    })
}

private fun onClickStart(
    handler: Handler,
    clickEnable: MutableState<Boolean>,
    timeBox: MutableState<TimeBox>,
) {
    //clickEnable.value = false
    val aboveTemp = timeBox.value.aboveTime
    val currentTemp = timeBox.value.currentTime
    val belowTemp = timeBox.value.belowTime
    handler.post(object : Runnable {

        override fun run() {
            if (timeBox.value.currentTime != 0) {
                timeBox.value = timeBox.value.copy(currentTime = timeBox.value.currentTime - 1)
                handler.postDelayed(this, DELAY_1000)
            }
            else if (currentTemp > aboveTemp && currentTemp > belowTemp) {
                handler.removeCallbacksAndMessages(null)
                timeBox.value = timeBox.value.copy(
                    aboveTime = (currentTemp * 0.9).toInt(),
                    currentTime = (currentTemp * 0.8).toInt(),
                    belowTime = currentTemp + 3,
                )
                //clickEnable.value = true
                //App.instance.timestamps.addTimestamps(timeBox.value)
            }
            else {
                handler.removeCallbacksAndMessages(null)
                timeBox.value = timeBox.value.copy(
                    aboveTime = timeBox.value.belowTime,
                    currentTime = timeBox.value.aboveTime,
                    belowTime = currentTemp,
                )
                //clickEnable.value = true
                //App.instance.timestamps.addTimestamps(timeBox.value)
            }
        }
    })
}