package com.example.shorts.ui.widgets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.shorts.ui.view_model.MainViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StopButton(
    showStopButton: Boolean,
    viewModel: MainViewModel,
) {
    if (showStopButton) {
        Card(
            modifier = Modifier
                .combinedClickable(
                    onClick = { viewModel.stopExercise() },
                    onLongClick = { viewModel.onLongPress() },
                ),
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
}