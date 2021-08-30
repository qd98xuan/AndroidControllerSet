package com.me.composelist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateListOf
import com.me.composelist.ui.theme.AndroidControllerSetTheme

class MainActivity : ComponentActivity() {
    val list = mutableStateListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidControllerSetTheme {
                Column() {
                    Button(onClick = {
                        list.add(list.size)
                    }) {
                        Text("我想增加一个")
                    }
                    for (num in list){
                        Text("这是第$num 个")
                    }
                }
            }
        }
    }
}