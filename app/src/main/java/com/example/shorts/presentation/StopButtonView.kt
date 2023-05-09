package com.example.shorts.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StopButton(clickEnable: MutableState<Boolean>) {
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