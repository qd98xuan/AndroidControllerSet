package com.me.recyclerviewpaging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.me.recyclerviewpaging.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding = DataBindingUtil.bind<ActivityMainBinding>(
        LayoutInflater.from(this).inflate(R.layout.activity_main, null, false)
    )
    val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}