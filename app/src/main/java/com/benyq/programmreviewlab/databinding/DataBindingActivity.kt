package com.benyq.programmreviewlab.databinding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import com.benyq.programmreviewlab.R

class DataBindingActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityDataBindingBinding
    private var title: ObservableField<String> = ObservableField("登录")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding)
        _binding.title = title
        supportFragmentManager.beginTransaction()
            .add(R.id.fl_root, ArticleFragment(), "ArticleFragment")
            .commit()

        _binding.tvNext.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}