package com.me.testlistmodule

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class AnimateActivity : AppCompatActivity() {
    val tvExpand by lazy { findViewById<TextView>(R.id.tv_expand) }
    val llList by lazy { findViewById<LinearLayout>(R.id.ll_list) }
    var isExpand = true
    val listHeight by lazy { llList.measuredHeight }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animate)
        tvExpand.setOnClickListener {
            if (isExpand) {
                hidenList(llList)
            }else{
                showList(llList)
            }
            isExpand = !isExpand
        }
    }

    fun hidenList(view: View) {
        val valueAnimator = ValueAnimator.ofInt(listHeight, 0)
        valueAnimator.setDuration(100)
        valueAnimator.addUpdateListener {
            val layoutParams = view.layoutParams
            layoutParams.height = it.getAnimatedValue() as Int
            view.layoutParams = layoutParams
        }
        valueAnimator.start()
    }

    fun showList(view: View) {
        val measuredHeight = view.measuredHeight
        val valueAnimator = ValueAnimator.ofInt(0, listHeight)
        valueAnimator.setDuration(100)
        valueAnimator.addUpdateListener {
            val layoutParams = view.layoutParams
            layoutParams.height = valueAnimator.animatedValue as Int
            view.layoutParams = layoutParams
        }
        valueAnimator.start()
    }

    fun scaleAnimate() {

    }
}