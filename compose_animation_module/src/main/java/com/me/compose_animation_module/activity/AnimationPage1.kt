package com.me.compose_animation_module.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.alibaba.android.arouter.facade.annotation.Route
import com.me.base_model.routers.Routers

@Route(path = Routers.ComposeAnimationModulePage1)
class AnimationPage1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}

@Composable
fun imageBox(image: ImageVector) {
    val height = remember {
        mutableStateOf(30f)
    }
    val width = remember {
        mutableStateOf(30f)
    }
    val boxAnimateSize = animateSizeAsState(Size(width.value,height.value),finishedListener = {})
    Image(contentDescription = "image",imageVector = image, modifier = Modifier.size(boxAnimateSize.value.width.dp,boxAnimateSize.value.height.dp))
}
