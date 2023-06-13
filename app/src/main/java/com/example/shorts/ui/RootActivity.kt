package com.example.shorts.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import com.example.shorts.ui.theme.ShortsTheme
import com.example.shorts.ui.theme.dark
import com.example.shorts.ui.theme.light
import com.example.shorts.ui.view_model.MainViewModel
import com.example.shorts.ui.widgets.Background
import com.example.shorts.ui.widgets.StartButton
import com.example.shorts.ui.widgets.StopButton
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RootActivity : ComponentActivity() {

    private val handler = Handler(Looper.getMainLooper())
    private val viewModel: MainViewModel by viewModel { parametersOf(handler) }
    override fun onCreate(savedInstanceState: Bundle?) {
        this.window.navigationBarColor = dark.toArgb()
        this.window.statusBarColor = light.toArgb()
        super.onCreate(savedInstanceState)
        setContent {
            ShortsTheme {
                //val timeBox = viewModel.timeBox
                Background()
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    StartButton(viewModel)
                    StopButton(viewModel)
                }
            }
        }
    }
}