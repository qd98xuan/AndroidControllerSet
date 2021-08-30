package com.me.loginmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.alibaba.android.arouter.launcher.ARouter
import com.me.base_model.routers.Routers
import com.me.loginmodel.ui.theme.AndroidControllerSetTheme
import com.me.loginmodel.ui.theme.Orange

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var userName by mutableStateOf("")
        var password by mutableStateOf("")
        var text by mutableStateOf("")
        setContent {
            AndroidControllerSetTheme {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_cat),
                        contentDescription = "头像",
                        Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .border(
                                brush = Brush.linearGradient(
                                    listOf(Color.Yellow, Orange),
                                    Offset(0f, 0f),
                                    Offset(280f, 280f)
                                ), width = 4.dp, shape = CircleShape
                            )
                            .border(color = Color.White, width = 6.dp, shape = CircleShape)
                            .clip(CircleShape)
                    )
                    OutlinedTextField(value = userName, onValueChange = { userName = it }, label = {
                        Text(
                            text = "用户名"
                        )
                    }, modifier = Modifier.padding(0.dp, 30.dp, 0.dp, 0.dp))
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = {
                            Text(
                                text = "密码"
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.padding(0.dp, 30.dp, 0.dp, 0.dp)
                    )
                    AnimateTextField(R.drawable.ic_cat, text, { text = it })
                    val buttonWidth = remember {
                        mutableStateOf(100f)
                    }
                    val buttonHeight = remember {
                        mutableStateOf(50f)
                    }
                    val animationSize = animateSizeAsState(targetValue = Size(buttonWidth.value,buttonHeight.value),finishedListener = {
                        buttonWidth.value = 100f
                        buttonHeight.value=50f
                    })
                    Box(Modifier.padding(30.dp)) {
                        Button(onClick = {
                            buttonWidth.value=110f
                            buttonHeight.value=60f
                            ARouter.getInstance().build(Routers.ComposeAnimationModulePage1).navigation()
                        },Modifier.size(animationSize.value.width.dp,animationSize.value.height.dp)) {
                            Text("登录")
                        }
                    }
                }
            }
        }
    }


    /**
     * 动画文本框
     */
    @Composable
    fun AnimateTextField(
        @DrawableRes id: Int = R.drawable.ic_cat,
        value: String,
        onValueChange: (String) -> Unit
    ) {
        val height = remember {
            mutableStateOf(30f)
        }
        val width = remember {
            mutableStateOf(30f)
        }
        val scale = animateSizeAsState(Size(width.value, height.value),finishedListener = {
            height.value = 30f
            width.value = 30f
        })
        Box(
            Modifier.padding(0.dp, 30.dp, 0.dp, 0.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(5.dp),
                    )
                    .background(Color.White)
            ) {
                TextField(
                    value = value,
                    onValueChange = onValueChange,
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                    singleLine = true
                )
                Image(
                    imageVector = ImageVector.vectorResource(id = id),
                    contentDescription = "",
                    Modifier
                        .size(scale.value.width.dp, scale.value.height.dp)
                        .clickable {
                            height.value = 50f
                            width.value = 50f
                        },
                )
            }
        }
    }
}