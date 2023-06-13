package com.example.shorts.ui.widgets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.shorts.ui.view_model.MainViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StopButton(viewModel: MainViewModel) {
    if (viewModel.stopButtonState) {
        Card(
            modifier = Modifier.combinedClickable(
                onClick = { viewModel.stopFirstTraining() },
                onLongClick = { viewModel.onLongPress() },
            ),
            shape = RoundedCornerShape(8.dp),
            backgroundColor = Color.Cyan,
            elevation = 5.dp,
        ) {
            Text(
                text = viewModel.getText(),
                modifier = Modifier.padding(start = 10.dp, top = 11.dp, end = 10.dp, bottom = 10.dp),
                style = MaterialTheme.typography.caption,
            )
        }
    }
}