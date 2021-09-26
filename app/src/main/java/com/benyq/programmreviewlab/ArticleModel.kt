package com.benyq.programmreviewlab

import androidx.databinding.BaseObservable

/**
 * @author benyq
 * @time 2021/9/25
 * @e-mail 1520063035@qq.com
 * @note
 */
data class ArticleModel(val title: String, val desc: String, val imagePath: String): BaseObservable() {
}