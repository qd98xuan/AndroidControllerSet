package com.me.loginmodel

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter


class App:Application() {
    override fun onCreate() {
        super.onCreate()
        ARouter.init(this)
        ARouter.openDebug()
        ARouter.openDebug()
    }
}