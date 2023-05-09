package com.example.shorts

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.shorts.presentation.Background
import com.example.shorts.presentation.StartButton
import com.example.shorts.presentation.StopButton
import com.example.shorts.ui.theme.ShortsTheme

class MainActivity : ComponentActivity() {
    private val handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShortsTheme {
                //App.instance.timestamps.clearTimestamps()
                val timestamps = remember { mutableStateOf(App.instance.timestamps.getTimestamps()) }
                val clickEnable = remember { mutableStateOf(true) }

                Background()
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    StartButton(handler, clickEnable, timestamps)
                    StopButton(clickEnable)
                }
            }
        }
    }
}




