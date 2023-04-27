package com.example.shorts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
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
                StartItem("126", "Take a new height!")
                StopItem()
            }
        }
    }
}

//@Preview(showBackground = true)
@Composable
private fun StartItem(time: String, exercise: String) {
    Card(
        modifier = Modifier
            .padding(32.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp,
    ) {
        Box(modifier = Modifier.background(Color.Cyan)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = time, fontSize = 22.sp)
                Text(text = exercise, fontSize = 22.sp)
                Image(
                    painter = painterResource(id = R.drawable.checked),
                    contentDescription = "check",
                    modifier = Modifier.size(30.dp),
                    alignment = Alignment.CenterEnd,
                )
            }
        }

    }
}

@Composable
private fun StopItem() {
    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.Cyan,
        elevation = 5.dp,
    ) {
        Text(
            text = "   Stop   ",
            modifier = Modifier.padding(16.dp)
        )
    }
}
