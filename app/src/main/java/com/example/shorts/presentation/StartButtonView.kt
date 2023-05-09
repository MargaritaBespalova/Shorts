package com.example.shorts.presentation

import android.content.res.ColorStateList
import android.os.Handler
import android.widget.Button
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.shorts.App
import com.example.shorts.R
import com.example.shorts.domain.model.Timestamps
import com.example.shorts.tools.DELAY_1000
import com.example.shorts.ui.theme.Orange

@Composable
fun StartButton(
    handler: Handler,
    clickEnable: MutableState<Boolean>,
    timestamps: MutableState<Timestamps>,
) {
    Card(
        modifier = Modifier
            .padding(32.dp)
            .clickable {
                if (!timestamps.value.retryStart) {
                    if (clickEnable.value) {
                        onClickGo(handler, clickEnable, timestamps)
                    }
                } else if (clickEnable.value) {
                    onClickStart(handler, clickEnable, timestamps)
                }
            },
        shape = RoundedCornerShape(10.dp),
        elevation = 15.dp,
    ) {
        Box(modifier = Modifier
            .background(if (timestamps.value.currentTime % 9 in (0..1)) Orange else Color.Cyan)
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
                    Text(text = "${timestamps.value.aboveTime}", color = Color.DarkGray, fontSize = 13.sp)
                    Text(text = "${timestamps.value.currentTime}", fontSize = 17.sp)
                    Text(text = "${timestamps.value.belowTime}", color = Color.DarkGray, fontSize = 13.sp)
                }
                Text(text = timestamps.value.text, fontSize = 24.sp)
                Image(
                    painter = painterResource(id = R.drawable.checked),
                    contentDescription = "check",
                    modifier = Modifier.size(32.dp),
                    alignment = Alignment.CenterEnd,
                )
            }
        }
    }
}

private fun onClickGo(
    handler: Handler,
    clickEnable: MutableState<Boolean>,
    timestamps: MutableState<Timestamps>,
) {
    clickEnable.value = false
    handler.post(object : Runnable {
        override fun run() {
            if (!clickEnable.value) {
                timestamps.value = timestamps.value.copy(currentTime = timestamps.value.currentTime + 1)
                handler.postDelayed(this, DELAY_1000)
            } else {
                handler.removeCallbacksAndMessages(null)
                timestamps.value = timestamps.value.copy(
                    aboveTime = (timestamps.value.currentTime * 0.9).toInt(),
                    currentTime = (timestamps.value.currentTime * 0.8).toInt(),
                    belowTime = timestamps.value.currentTime + 3,
                    retryStart = true,
                    text = "Start exercise",
                )
                App.instance.timestamps.addTimestamps(timestamps.value)
            }
        }
    })
}

private fun onClickStart(
    handler: Handler,
    clickEnable: MutableState<Boolean>,
    timestamps: MutableState<Timestamps>,
) {
    clickEnable.value = false
    val aboveTemp = timestamps.value.aboveTime
    val currentTemp = timestamps.value.currentTime
    val belowTemp = timestamps.value.belowTime
    handler.post(object : Runnable {
        override fun run() {
            if (timestamps.value.currentTime != 0) {
                timestamps.value = timestamps.value.copy(currentTime = timestamps.value.currentTime - 1)
                handler.postDelayed(this, DELAY_1000)
            }
            else if (currentTemp > aboveTemp && currentTemp > belowTemp) {
                handler.removeCallbacksAndMessages(null)
                timestamps.value = timestamps.value.copy(
                    aboveTime = (currentTemp * 0.9).toInt(),
                    currentTime = (currentTemp * 0.8).toInt(),
                    belowTime = currentTemp + 3,
                )
                clickEnable.value = true
                App.instance.timestamps.addTimestamps(timestamps.value)
            }
            else {
                handler.removeCallbacksAndMessages(null)
                timestamps.value = timestamps.value.copy(
                    aboveTime = timestamps.value.belowTime,
                    currentTime = timestamps.value.aboveTime,
                    belowTime = currentTemp,
                )
                clickEnable.value = true
                App.instance.timestamps.addTimestamps(timestamps.value)
            }
        }
    })
}