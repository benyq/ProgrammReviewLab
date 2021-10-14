package com.benyq.programmreviewlab

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.benyq.programmreviewlab.handler.HandlerActivity
import com.benyq.programmreviewlab.sohotfix.HotFixSoActivity
import com.benyq.programmreviewlab.databinding.DataBindingActivity
import com.benyq.programmreviewlab.databinding.model.EntranceData
import com.drake.brv.utils.grid
import com.drake.brv.utils.setup

/**
 * @author benyq
 * @date 2021/10/9
 * @email 1520063035@qq.com
 * 测试工程入口
 */
class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val rvEntrance = findViewById<RecyclerView>(R.id.rv_entrance)

        rvEntrance.grid(3)
            .setup {
                addType<EntranceData>(R.layout.item_entrance)
                R.id.item.onClick {
                    val data: EntranceData = mutable[layoutPosition] as EntranceData
                    startActivity(Intent(this@WelcomeActivity, data.clz))
                }
            }.models = getData()

    }

    private fun getData() = listOf(
        EntranceData("DataBinding", DataBindingActivity::class.java),
        EntranceData("Handler内存屏障", HandlerActivity::class.java),
        EntranceData("修改so加载地址", HotFixSoActivity::class.java),
        EntranceData("Handler", HandlerActivity::class.java),
        EntranceData("Handler", HandlerActivity::class.java),
    )




}