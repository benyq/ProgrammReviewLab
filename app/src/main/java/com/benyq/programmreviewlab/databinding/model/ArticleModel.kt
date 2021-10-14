package com.benyq.programmreviewlab.databinding.model

import androidx.databinding.BaseObservable

/**
 * @author benyq
 * @time 2021/9/25
 * @e-mail 1520063035@qq.com
 * @note
 */
data class ArticleModel(var title: String, val desc: String, val imagePath: String): BaseObservable() {
}