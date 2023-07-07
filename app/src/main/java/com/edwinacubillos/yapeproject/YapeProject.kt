package com.edwinacubillos.yapeproject

import android.app.Application
import com.edwinacubillos.yapeproject.di.initDI

class YapeProject : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

}
