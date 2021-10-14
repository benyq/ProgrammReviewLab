package com.benyq.programmreviewlab.sohotfix

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.benyq.programmreviewlab.R
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.lang.Exception

/**
 * @author benyq
 * @date 2021/10/12
 * @email 1520063035@qq.com
 * 这个是反射修改 so 加载地址
 */

class HotFixSoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hot_fix_so)

        val path = Environment.getExternalStorageDirectory().absolutePath + File.separator + "so路径"

        val dir: File = getDir("libs", Context.MODE_PRIVATE)
        if (!isLoadSoFile(dir)) {
            copy(path, dir.absolutePath)
        }

        RetroLoadLibrary.installNativeLibraryPath(baseContext.classLoader, dir)
        //之后就可以根据自定义的so路径去加载
    }


    /**
     * 判断 so 文件是否存在
     *
     * @param dir
     * @return
     */
    private fun isLoadSoFile(dir: File): Boolean {
        val currentFiles = dir.listFiles() ?: return false
        for (i in currentFiles.indices) {
            if (currentFiles[i].name.contains("so库名字")) {
                return true
            }
        }
        return false
    }


    /**
     * @param fromFile 指定的下载目录
     * @param toFile   应用的包路径
     * @return
     */
    private fun copy(fromFile: String, toFile: String): Int {
        val root = File(fromFile)
        //如同判断SD卡是否存在或者文件是否存在,如果不存在则 return出去
        if (!root.exists()) {
            return -1
        }
        //如果存在则获取当前目录下的全部文件 填充数组
        val currentFiles = root.listFiles() ?: return 0
        //目标目录
        val targetDir = File(toFile)
        //创建目录
        if (!targetDir.exists()) {
            targetDir.mkdirs()
        }
        //遍历要复制该目录下的全部文件
        for (i in currentFiles.indices) {
            if (currentFiles[i].isDirectory) {
                //如果当前项为子目录 进行递归
                copy(
                    currentFiles[i].path + "/",
                    toFile + File.separator + currentFiles[i].name + "/"
                )
            } else {
                //如果当前项为文件则进行文件拷贝
                if (currentFiles[i].name.contains(".so")) {
                    val id: Int = copySdcardFile(
                        currentFiles[i].path,
                        toFile + File.separator + currentFiles[i].name
                    )
                }
            }
        }
        return 0
    }

    //文件拷贝
    //要复制的目录下的所有非子目录(文件夹)文件拷贝
    private fun copySdcardFile(fromFile: String, toFile: String?): Int {
        return try {
            val fosfrom = FileInputStream(fromFile)
            val fosto = FileOutputStream(toFile)
            val baos = ByteArrayOutputStream()
            val buffer = ByteArray(1024)
            var len = -1
            while (fosfrom.read(buffer).also { len = it } != -1) {
                baos.write(buffer, 0, len)
            }
            // 从内存到写入到具体文件
            fosto.write(baos.toByteArray())
            // 关闭文件流
            baos.close()
            fosto.close()
            fosfrom.close()
            0
        } catch (ex: Exception) {
            -1
        }
    }
}