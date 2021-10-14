package com.benyq.programmreviewlab.handler

import android.os.*
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.benyq.programmreviewlab.R
import androidx.annotation.RequiresApi
import java.lang.Exception
import java.lang.reflect.Method

/**
 * @author benyq
 * @date 2021/10/9
 * @email 1520063035@qq.com
 * Handler内存屏障测试
 */
class HandlerActivity : AppCompatActivity() {

    private lateinit var mHandler: Handler
    val MESSAGE_TYPE_SYNC = 1
    val MESSAGE_TYPE_ASYN = 2
    private var token = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler)
        initHandler()
    }


    fun onClick(v: View) {
        if (v.id == R.id.button1) {
            sendSyncMessage()
        }else if (v.id == R.id.button2) {
            sendAsynMessage()
        }else if (v.id == R.id.button3) {
            sendSyncBarrier()
        }else if (v.id == R.id.button4) {
            removeSyncBarrier()
        }
    }

    private fun initHandler() {
        Thread {
            Looper.prepare()
            mHandler = object : Handler(Looper.myLooper()!!) {
                override fun handleMessage(msg: Message) {
                    //super.handleMessage(msg);
                    if (msg.what === MESSAGE_TYPE_SYNC) {
                        Log.d("HandlerActivity", "收到普通消息")
                    } else if (msg.what === MESSAGE_TYPE_ASYN) {
                        Log.d("HandlerActivity", "收到异步消息")
                    }
                }
            }
            Looper.loop()
        }.start()
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    private fun sendAsynMessage() {
        Log.d("HandlerActivity", "插入异步消息")
        val message: Message = Message.obtain()
        message.what = MESSAGE_TYPE_ASYN
        message.setAsynchronous(true) //3
        mHandler.sendMessageDelayed(message, 1000)
    }

    private fun sendSyncMessage() {
        Log.d("HandlerActivity", "插入普通消息")
        val message: Message = Message.obtain()
        message.what = MESSAGE_TYPE_SYNC
        mHandler.sendMessageDelayed(message, 1000)
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private fun removeSyncBarrier() {
        try {
            Log.d("HandlerActivity", "移除屏障")
            val queue: MessageQueue = mHandler.getLooper().getQueue()
            val method: Method = MessageQueue::class.java.getDeclaredMethod(
                "removeSyncBarrier",
                Int::class.javaPrimitiveType
            )
            method.setAccessible(true)
            method.invoke(queue, token) //2
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 插入同步屏障
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private fun sendSyncBarrier() {
        try {
            Log.d("HandlerActivity", "插入同步屏障")
            val queue: MessageQueue = mHandler.getLooper().getQueue()
            val method: Method = MessageQueue::class.java.getDeclaredMethod("postSyncBarrier")
            method.setAccessible(true)
            token = method.invoke(queue) as Int //1
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}