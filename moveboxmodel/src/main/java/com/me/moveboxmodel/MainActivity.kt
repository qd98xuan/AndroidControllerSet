package com.me.moveboxmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.me.moveboxmodel.ui.theme.AndroidControllerSetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var offsetX by mutableStateOf(0f)
        var offsetY by mutableStateOf(0f)
        setContent {
            AndroidControllerSetTheme {
                Box(Modifier.fillMaxSize()) {
                    Box(modifier = Modifier
                        .offset { IntOffset(offsetX.toInt(), offsetY.toInt()) }
                        .size(100.dp)
                        .pointerInput(Unit) {
                            detectDragGestures { change, dragAmount ->
                                change.consumeAllChanges()
                                offsetX += dragAmount.x
                                offsetY += dragAmount.y
                            }
                        }
                        .background(Color.Blue)){
                        Text(text = "$offsetX,$offsetY",textAlign = TextAlign.Center,color = Color.White)
                    }
                }
            }
        }
    }
}