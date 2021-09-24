package com.benyq.programmreviewlab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Executors.newCachedThreadPool()
    }
}