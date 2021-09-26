package com.benyq.programmreviewlab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.benyq.programmreviewlab.databinding.ActivityMainBinding
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).root)

        supportFragmentManager.beginTransaction()
            .add(R.id.fl_root, ArticleFragment(), "ArticleFragment")
            .commit()

    }
}