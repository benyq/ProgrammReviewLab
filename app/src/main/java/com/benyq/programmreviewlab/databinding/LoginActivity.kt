package com.benyq.programmreviewlab.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.benyq.programmreviewlab.R
import com.benyq.programmreviewlab.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.vm = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

}