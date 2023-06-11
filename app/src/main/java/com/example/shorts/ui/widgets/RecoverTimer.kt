package com.example.shorts.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shorts.R
import com.example.shorts.ui.theme.best_bold
import com.example.shorts.ui.theme.best_italic
import com.example.shorts.utils.FINISHED

@Composable
fun RecoverTimer(time: String) {
    if (time != FINISHED) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = stringResource(R.string.restore_energy),
                    modifier = Modifier.padding(end = 8.dp),
                    color = Color.White,
                    fontSize = 13.sp,
                    fontFamily = best_italic,
                )
                Text(
                    text = time,
                    modifier = Modifier.padding(end = 12.dp),
                    color = Color.White,
                    fontSize = 12.sp,
                    fontFamily = best_italic,
                )
            }
        }
    }
}