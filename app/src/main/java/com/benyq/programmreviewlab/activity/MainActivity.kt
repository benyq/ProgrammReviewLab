package com.benyq.programmreviewlab.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.os.postDelayed
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import com.benyq.programmreviewlab.R
import com.benyq.programmreviewlab.databinding.ActivityMainBinding
import com.benyq.programmreviewlab.fragment.ArticleFragment

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private var title: ObservableField<String> = ObservableField("title")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        _binding.title = title
        supportFragmentManager.beginTransaction()
            .add(R.id.fl_root, ArticleFragment(), "ArticleFragment")
            .commit()

        Handler(Looper.getMainLooper()).postDelayed(2000) {
            title.set("TITLE")
        }

        _binding.tvNext.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}