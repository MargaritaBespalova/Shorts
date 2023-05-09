package com.example.shorts.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.shorts.domain.model.FirstRunStateModel
import com.example.shorts.domain.model.Timestamps

@Composable
fun Numbers() {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "0", color = Color.DarkGray, fontSize = 13.sp)
        Text(text = "0", fontSize = 17.sp)
        Text(text = "0", color = Color.DarkGray, fontSize = 13.sp)
    }
}
