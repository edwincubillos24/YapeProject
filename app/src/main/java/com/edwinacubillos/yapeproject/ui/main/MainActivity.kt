package com.edwinacubillos.yapeproject.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.edwinacubillos.yapeproject.R
import com.edwinacubillos.yapeproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(1000)
        setTheme(R.style.Theme_YapeProject)

        super.onCreate(savedInstanceState)

        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
    }
}
