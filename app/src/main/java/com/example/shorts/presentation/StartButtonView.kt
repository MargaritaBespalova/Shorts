package com.example.shorts.presentation
//
//import android.os.Handler
//import android.util.Log
//import android.widget.Toast
//import androidx.activity.ComponentActivity
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.Card
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.MutableState
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.shorts.R
//import com.example.shorts.domain.model.FirstRunStateModel
//import com.example.shorts.domain.model.Timestamps
//import com.example.shorts.tools.DELAY_1000
//
//@Composable
//fun StartButton(
//    handler: Handler,
//    clickEnable: MutableState<Boolean>,
//    timestamps: MutableState<Timestamps>,
//    firstRun: MutableState<FirstRunStateModel>,
//) {
//    Card(
//        modifier = Modifier.padding(32.dp).clickable {
//            if (firstRun.value.isFirst) {
//                onClickStart(
//                    handler,
//                    clickEnable,
//                    timestamps,
//                )
//                Log.d("log", "++++firstRun++++")
//                } else Log.d("log", "++++Button is blocked++++")
//            },
//        shape = RoundedCornerShape(10.dp),
//        elevation = 15.dp,
//    ) {
//        Box(modifier = Modifier.background(Color.Cyan)) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(start = 16.dp, top = 4.dp, end = 16.dp, bottom = 4.dp),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Column(
//                    verticalArrangement = Arrangement.SpaceBetween,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Text(text = "${timestamps.value.aboveTime}", color = Color.DarkGray, fontSize = 13.sp)
//                    Text(text = "${timestamps.value.currentTime}", fontSize = 17.sp)
//                    Text(text = "${timestamps.value.belowTime}", color = Color.DarkGray, fontSize = 13.sp)
//                }
//                Text(text = firstRun.value.text, fontSize = 24.sp)
//                Image(
//                    painter = painterResource(id = R.drawable.checked),
//                    contentDescription = "check",
//                    modifier = Modifier.size(32.dp),
//                    alignment = Alignment.CenterEnd,
//                )
//            }
//        }
//
//    }
//}
//
//private fun onClickStart(
//    handler: Handler,
//    clickEnable: MutableState<Boolean>,
//    timestamps: MutableState<Timestamps>,
//) {
//    clickEnable.value = false
//    timestamps.value.currentTime++
//    Log.d("log", "++++${timestamps.value.currentTime}++++")
////    handler.post(object : Runnable {
////        override fun run() {
////            if (!clickEnable.value) {
////                timestamps.value.currentTime++
////                Log.d("log", "++++${timestamps.value.currentTime}++++")
////                handler.postDelayed(this, DELAY_1000)
////            }
////            else {
////                handler.removeCallbacksAndMessages(null)
//////                above.value = (current.value * 0.9).toInt()
//////                below.value = current.value + 3
//////                current.value = (current.value * 0.8).toInt()
////            }
////        }
////    })
//
//}