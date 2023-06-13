package com.example.shorts.ui.widgets

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shorts.R
import com.example.shorts.ui.theme.Orange
import com.example.shorts.ui.theme.cyrillic
import com.example.shorts.ui.view_model.MainViewModel

@Composable
fun StartButton(
    viewModel: MainViewModel,
) {
    Column(
        modifier = Modifier.padding(32.dp)
    ) {
        Card(
            modifier = Modifier.clickable { viewModel.startTraining() },
            shape = RoundedCornerShape(10.dp),
            elevation = 15.dp,
        ) {
            Box(
                modifier = Modifier.background(
                    if (viewModel.timeBox.currentTime % 8 in (6..7)) Orange
                    else Color.Cyan)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, top = 4.dp, end = 16.dp, bottom = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.width(46.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "${viewModel.timeBox.aboveTime}",
                            style = MaterialTheme.typography.caption
                        )
                        Text(
                            text = "${viewModel.timeBox.currentTime}",
                            style = MaterialTheme.typography.h6
                        )
                        Text(
                            text = "${viewModel.timeBox.belowTime}",
                            style = MaterialTheme.typography.caption
                        )
                    }
                    Text(
                        text = viewModel.startPhrase,
                        modifier = Modifier.widthIn(max = 200.dp),
                        style = TextStyle.Default,
                        fontSize = if (viewModel.startPhrase.length > 14) 19.sp else 22.sp,
                        fontFamily = cyrillic,
                        maxLines = 1,
                    )
                    Image(
                        painter = painterResource(id = R.drawable.checked),
                        contentDescription = "check",
                        modifier = Modifier
                            .size(32.dp)
                            .graphicsLayer { alpha = viewModel.checkIconState },
                        alignment = Alignment.CenterEnd,
                    )
                }
            }
        }
        RecoverTimer(time = viewModel.recoverTime)
    }
}