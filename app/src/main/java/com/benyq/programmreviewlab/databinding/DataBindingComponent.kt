package com.benyq.programmreviewlab.databinding

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContextWrapper
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.view.View.NO_ID
import android.webkit.WebView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.button.MaterialButton
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * @author benyq
 * @date 2021/9/27
 * @email 1520063035@qq.com
 *
 */

@BindingAdapter(
    value = ["imageUrl", "placeholder", "error", "fallback", "loadWidth", "loadHeight", "cacheEnable"],
    requireAll = false
)
fun setImageUrl(
    view: ImageView,
    source: Any? = null,
    placeholder: Drawable? = null,
    error: Drawable? = null,
    fallback: Drawable? = null,
    width: Int? = -1,
    height: Int? = -1,
    cacheEnable: Boolean? = true
) {
    // 计算位图尺寸，如果位图尺寸固定，加载固定大小尺寸的图片，如果位图未设置尺寸，那就加载原图，Glide加载原图时，override参数设置 -1 即可。
    val widthSize = (if ((width ?: 0) > 0) width else view.width) ?: -1
    val heightSize = (if ((height ?: 0) > 0) height else view.height) ?: -1
    // 根据定义的 cacheEnable 参数来决定是否缓存
    val diskCacheStrategy = if (cacheEnable == true) DiskCacheStrategy.AUTOMATIC else DiskCacheStrategy.NONE
    // 设置编码格式，在Android 11(R)上面使用高清无损压缩格式 WEBP_LOSSLESS ， Android 11 以下使用PNG格式，PNG格式时会忽略设置的 quality 参数。
    val encodeFormat = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) Bitmap.CompressFormat.WEBP_LOSSLESS else Bitmap.CompressFormat.PNG
    Glide.with(view.context)
        .asDrawable()
        .load(source)
        .placeholder(placeholder)
        .error(error)
        .fallback(fallback)
        .thumbnail(0.33f)
        .override(widthSize, heightSize)
        .skipMemoryCache(false)
        .sizeMultiplier(0.5f)
        .format(DecodeFormat.PREFER_ARGB_8888)
        .encodeFormat(encodeFormat)
        .encodeQuality(80)
        .diskCacheStrategy(diskCacheStrategy)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(view)
}


//<editor-fold desc="间距">
@BindingAdapter("paddingStart", "paddingEnd", requireAll = false)
fun View.setPaddingRtl(start: View?, end: View?) {
    post {
        val startFinal = (start?.width ?: 0) + paddingStart
        val endFinal = (end?.width ?: 0) + paddingEnd
        setPaddingRelative(startFinal, paddingTop, endFinal, paddingBottom)
    }
}
//</editor-fold>

// <editor-fold desc="图片">

@BindingAdapter(
    value = ["leftDrawable", "topDrawable", "rightDrawable", "bottomDrawable"],
    requireAll = false
)
fun TextView.setImageDrawable(
    drawableLeft: Int, drawableTop: Int, drawableRight: Int, drawableBottom: Int
) {
    if (drawableLeft != 0 || drawableTop != 0 || drawableRight != 0 || drawableBottom != 0) {
        setCompoundDrawablesWithIntrinsicBounds(
            drawableLeft, drawableTop, drawableRight, drawableBottom
        )
    }
}

@BindingAdapter("android:background")
fun View.setBackgroundRes(drawableId: Int) {
    if (drawableId != 0 && drawableId != NO_ID) {
        setBackgroundResource(drawableId)
    }
}

@BindingAdapter("android:backgroundTint")
fun MaterialButton.setBackgroundTintRes(color: Int) {
    if (color != 0 && color != NO_ID) {
        backgroundTintList = ColorStateList(arrayOf(intArrayOf()), intArrayOf(color))
    }
}


@BindingAdapter("android:src")
fun ImageView.setImageRes(drawableId: Int) {
    if (drawableId != 0 && drawableId != NO_ID) {
        setImageResource(drawableId)
    }
}

// </editor-fold>


// <editor-fold desc="隐藏">

/**
 * 隐藏控件
 */
@BindingAdapter("invisible")
fun View.setInvisible(visibilityVar: Boolean) {
    visibility = if (visibilityVar) {
        View.VISIBLE
    } else {
        View.INVISIBLE
    }
}

@BindingAdapter("invisible")
fun View.setInvisible(visibilityVar: Any?) {
    visibility = if (visibilityVar != null) {
        View.VISIBLE
    } else {
        View.INVISIBLE
    }
}

/**
 * 取消控件
 */
@BindingAdapter("gone")
fun View.setGone(visibilityVar: Boolean) {
    visibility = if (visibilityVar) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("gone")
fun View.setGone(visibilityVar: Any?) {
    visibility = if (visibilityVar != null) {
        View.VISIBLE
    } else {
        View.GONE
    }
}


// </editor-fold>


// <editor-fold desc="阴影">

@BindingAdapter("android:elevation")
fun View.setElevationOf(dp: Int) {
    ViewCompat.setElevation(this, (dp * context.resources.displayMetrics.density).toInt().toFloat())
}

@BindingAdapter("android:elevation")
fun CardView.setElevationOf(dp: Int) {
    cardElevation = dp.toFloat()
}

// </editor-fold>

// <editor-fold desc="状态">

@BindingAdapter("android:enabled")
fun View.setEnableBind(enable: Boolean) {
    isEnabled = enable
}

@BindingAdapter("android:enabled")
fun View.setEnableBind(enable: Any?) {
    isEnabled = enable != null
}

@BindingAdapter("selected")
fun View.setSelectedBind(selected: Boolean) {
    isSelected = selected
}

@BindingAdapter("selected")
fun View.setSelectedBind(selected: Any?) {
    isSelected = selected != null
}


@BindingAdapter("activated")
fun View.setActivatedBind(activated: Boolean) {
    isActivated = activated
}


@BindingAdapter("activated")
fun View.setActivatedBind(activated: Any?) {
    isActivated = activated != null
}

// </editor-fold>


// <editor-fold desc="点击事件">

/**
 * 防止暴力点击
 */
@SuppressLint("CheckResult")
@BindingAdapter("click")
fun View.setThrottleClickListener(onClickListener: View.OnClickListener?) {
    if (onClickListener != null) {
        throttleClick { onClickListener.onClick(this) }
    }
}


/**
 * 自动将点击事件映射到Activity上
 * @param throttle 是否只支持快速点击
 */
@SuppressLint("CheckResult")
@BindingAdapter("hit")
fun View.hit(throttle: Boolean = true) {
    var context = context
    while (context is ContextWrapper) {
        if (context is View.OnClickListener) {
            val clickListener = context as View.OnClickListener
            if (throttle) {
                throttleClick { clickListener.onClick(this) }
            } else {
                setOnClickListener(clickListener)
            }
        }
        context = context.baseContext
    }
}


/**
 * 关闭当前界面
 * @param enabled 是否启用
 */
@SuppressLint("CheckResult", "ObsoleteSdkInt")
@BindingAdapter("finish")
fun View.finishActivity(enabled: Boolean = true) {
    if (enabled) {
        var temp = context
        var activity: Activity? = null

        while (temp is ContextWrapper) {
            if (temp is Activity) {
                activity = temp
            }
            temp = temp.baseContext
        }

        val finalActivity = activity

        throttleClick {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                finalActivity!!.finishAfterTransition()
            } else {
                finalActivity!!.finish()
            }
        }
    }
}

// </editor-fold>


// <editor-fold desc="货币">

/**
 * 格式化RMB
 * @param number 货币数量
 * @param unit 货币种类, 默认为 ¥
 */
@SuppressLint("SetTextI18n")
@BindingAdapter("rmb", "rmbUnit", requireAll = false)
fun TextView.formatCNY(number: String?, unit: String?) {
    if (!number.isNullOrEmpty() && text.contentEquals(number)) {
        val format = "${unit ?: "¥"}${number.format()}"
        if (format != text.toString()) text = format
    }
}

// <editor-fold desc="时间">

@BindingAdapter(value = ["dateMilli", "dateFormat"], requireAll = false)
fun TextView.setDateFromMillis(milli: Long, format: String? = "yyyy-MM-dd") {
    /**
     * 格式化毫秒
     */
    val finalFormat = if (format.isNullOrBlank()) "yyyy-MM-dd" else format
    val date = Date(milli)
    val sf = SimpleDateFormat(finalFormat, Locale.CHINA)
    val formatText = sf.format(date)
    if (text.contentEquals(formatText)) return
    text = formatText
}


/**
 * 根据毫秒值来显示时间
 */
@BindingAdapter(value = ["dateMilli", "dateFormat"], requireAll = false)
fun TextView.setDateFromMillis(milli: String?, format: String? = "yyyy-MM-dd") {
    val finalFormat = if (format.isNullOrBlank()) "yyyy-MM-dd" else format
    val finalMilli = milli?.toLongOrNull() ?: return
    val date = Date(finalMilli)
    val sf = SimpleDateFormat(finalFormat, Locale.CHINA)
    val formatText = sf.format(date)
    if (text.contentEquals(formatText)) return
    text = formatText
}

/**
 * 格式化毫秒
 */
@BindingAdapter(value = ["dateSecond", "dateFormat"], requireAll = false)

fun TextView.setDateFromSecond(second: Long, format: String? = "yyyy-MM-dd") {
    val finalFormat = if (format.isNullOrBlank()) "yyyy-MM-dd" else format
    val date = Date(second * 1000)
    val sf = SimpleDateFormat(finalFormat, Locale.CHINA)
    val formatText = sf.format(date)
    if (text.contentEquals(formatText)) return
    text = formatText
}

/**
 * 格式化毫秒
 */
@BindingAdapter(value = ["dateSecond", "dateFormat"], requireAll = false)
fun TextView.setDateFromSecond(second: String, format: String? = "yyyy-MM-dd") {
    val finalFormat = if (format.isNullOrBlank()) "yyyy-MM-dd" else format
    val finalSecond = second.toLongOrNull() ?: return
    val date = Date(finalSecond * 1000)
    val sf = SimpleDateFormat(finalFormat, Locale.CHINA)
    val formatText = sf.format(date)
    if (text.contentEquals(formatText)) return
    text = formatText
}

// </editor-fold>

//<editor-fold desc="字符串">
@BindingAdapter("del")
fun TextView.setDel(isAdd: Boolean) {
    if (isAdd) {
        paint.flags = Paint.STRIKE_THRU_TEXT_FLAG or Paint.ANTI_ALIAS_FLAG   // 设置中划线并加清晰
    }
}

@BindingAdapter("android:text")
fun TextView.setTextOfNumber(number: Int) {
    val finalText = number.toString()
    if (!text.contentEquals(finalText)) {
        text = finalText
    }
}

@BindingAdapter("android:text")
fun TextView.setTextOfNumber(number: Long) {
    val finalText = number.toString()
    if (!text.contentEquals(finalText)) {
        text = finalText
    }
}

@BindingAdapter("android:text")
fun TextView.setTextOfNumber(number: Double) {
    val finalText = number.toString()
    if (!text.contentEquals(finalText)) {
        text = finalText
    }
}

@BindingAdapter("android:text")
fun TextView.setTextOfNumber(number: Float) {
    val finalText = number.toString()
    if (!text.contentEquals(finalText)) {
        text = finalText
    }
}

//</editor-fold>

//<editor-fold desc="网页">
@BindingAdapter("url")
fun WebView.setUrl(url: String?) {
    if (!url.isNullOrEmpty()) {
        loadDataWithBaseURL(null, url, "text/html", "UTF-8", null)
    }
}
//</editor-fold>

//<editor-fold desc="回调">
/**
 * 在绑定视图时可以用于Model来处理UI, 由于破坏视图和逻辑解耦的规则不是很建议使用
 *
 * @see OnBindListener 该接口支持泛型定义具体视图
 *
 * @receiver View
 * @param listener OnBindListener<View>
 */
@BindingAdapter("onBind")
fun View.setOnBindListener(listener: OnBindListener) {
    listener.onBind(this)
}

/**
 * 在绑定视图时可以用于Model来处理UI, 由于破坏视图和逻辑解耦的规则不是很建议使用
 */
interface OnBindListener {
    fun onBind(v: View)
}
//</editor-fold>