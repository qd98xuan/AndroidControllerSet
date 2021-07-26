package com.me.composeview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.me.composeview.ui.theme.AndroidControllerSetTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.reflect.KProperty

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        var name by mutableStateOf("name")
//        var num by mutableStateOf(1)
        var nums by mutableStateOf(mutableListOf(1,2,3))
        super.onCreate(savedInstanceState)
        setContent {
//            Text("当前的值是 $num",Modifier.clickable {
//                num++
//            })
            Column {
                for (num in nums) {
                    Text("这是第 $num 个数字")
                }
            }

        }

    }
}