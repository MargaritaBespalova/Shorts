package com.example.shorts.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.shorts.domain.model.FirstRunStateModel
import com.example.shorts.domain.model.Timestamps
import com.example.shorts.presentation.Background
import com.example.shorts.tools.DELAY_1000
import com.example.shorts.ui.theme.ShortsTheme

class MainActivity : ComponentActivity() {
    private val handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShortsTheme {
                val data = App.instance.timestamps.getTimestamps().copy()
                val timestamps = remember { mutableStateOf(data) }
                val firstRunStateModel = remember { mutableStateOf(FirstRunStateModel()) }
                val clickEnable = remember { mutableStateOf(true) }

                Background()
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    StartButton(handler,clickEnable,timestamps,firstRunStateModel)
                    StopItem(clickEnable)
                }
            }


        }

    }
}


@Composable
private fun StopItem(clickEnable: MutableState<Boolean>) {
    Card(
        modifier = Modifier.clickable { onClickStop(clickEnable = clickEnable)},
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.Cyan,
        elevation = 5.dp,
    ) {
        Text(
            text = "Stop",
            modifier = Modifier.padding(start = 22.dp, top = 12.dp, end = 22.dp, bottom = 12.dp)
        )
    }
}



private fun onClickStop(clickEnable: MutableState<Boolean>) {
    clickEnable.value = true
}

@Composable
fun StartButton(
    handler: Handler,
    clickEnable: MutableState<Boolean>,
    timestamps: MutableState<Timestamps>,
    firstRun: MutableState<FirstRunStateModel>,
) {
    Card(
        modifier = Modifier.padding(32.dp).clickable {
            if (firstRun.value.isFirst) {
                onClickStart(
                    handler,
                    clickEnable,
                    timestamps,
                )
                Log.d("log", "++++firstRun++++")
            } else Log.d("log", "++++Button is blocked++++")
        },
        shape = RoundedCornerShape(10.dp),
        elevation = 15.dp,
    ) {
        Box(modifier = Modifier.background(Color.Cyan)) {
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
                Text(text = firstRun.value.text, fontSize = 24.sp)
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

private fun onClickStart(
    handler: Handler,
    clickEnable: MutableState<Boolean>,
    timestamps: MutableState<Timestamps>,
) {
    clickEnable.value = false
    handler.post(object : Runnable {
        override fun run() {
            if (!clickEnable.value) {
                timestamps.value.currentTime++
                Log.d("log", "++++${timestamps.value}++++")
                handler.postDelayed(this, DELAY_1000)
            }
            else {
                handler.removeCallbacksAndMessages(null)
            }
        }
    })

}
