package com.benyq.programmreviewlab.vm

import android.app.Application
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

/**
 *
 * @author benyq
 * @date 2021/9/27
 * @email 1520063035@qq.com
 *
 */
class LoginViewModel(application: Application) : AndroidViewModel(application){

    var userName: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()

    var visible: ObservableField<Boolean> = ObservableField(false)

    fun login() {
        val name = userName.value
        val pwd = password.value

        if (name.isNullOrEmpty()) {
            Toast.makeText(getApplication(), "name 空", Toast.LENGTH_SHORT).show()
        }
        if (pwd.isNullOrEmpty()) {
            Toast.makeText(getApplication(), "pwd 空", Toast.LENGTH_SHORT).show()
        }
        visible.set(true)
    }
}