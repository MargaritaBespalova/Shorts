package com.example.shorts

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    private val handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .paint(
                        painter = painterResource(id = R.drawable.background),
                        contentScale = ContentScale.FillBounds
                    ),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                StartItem(handler)
                StopItem()
            }
        }

    }
}

//@Preview(showBackground = true)
@Composable
private fun StartItem(handler: Handler) {
    val current = remember { mutableStateOf(126) }
    val previous = remember { mutableStateOf(108) }
    val oldest = remember { mutableStateOf(101) }
    val isFirstUpdate = remember { mutableStateOf(false) }




    Card(
        modifier = Modifier
            .padding(32.dp)
            .clickable {
                if (isFirstUpdate.value) {
                    isFirstUpdate.value = false
                    ++current.value
                }
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
                    Text(text = "${previous.value}", color = Color.DarkGray, fontSize = 13.sp)
                    Text(text = "${current.value}", fontSize = 16.sp)
                    Text(text = "${oldest.value}", color = Color.DarkGray, fontSize = 13.sp)
                }
                Text(text = "Start exercise", fontSize = 24.sp)
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


@Composable
private fun StopItem() {
    Card(
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
