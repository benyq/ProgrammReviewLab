package com.benyq.programmreviewlab

import android.app.Application
import com.drake.brv.utils.BRV

/**
 * @author benyq
 * @time 2021/9/25
 * @e-mail 1520063035@qq.com
 * @note
 */
class StudyApp : Application(){

    override fun onCreate() {
        super.onCreate()

        BRV.modelId = BR.m

    }
}