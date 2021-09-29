package com.benyq.programmreviewlab.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.postDelayed
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.benyq.programmreviewlab.R
import com.benyq.programmreviewlab.databinding.FragmentArticleBinding
import com.benyq.programmreviewlab.model.ArticleModel
import com.drake.brv.annotaion.DividerOrientation
import com.drake.brv.utils.divider
import com.drake.brv.utils.linear
import com.drake.brv.utils.setup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * @author benyq
 * @time 2021/9/25
 * @e-mail 1520063035@qq.com
 * @note
 */
class ArticleFragment : Fragment(){

    private var _binding: FragmentArticleBinding? = null
    private lateinit var data: List<ArticleModel>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_article, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.tvHeader?.text = "Banner"
        initView()
    }

    private fun initView(){
        _binding?.rvArticle?.run {
            linear().setup {
                addType<ArticleModel>(R.layout.item_article)
                divider(R.color.black, DividerOrientation.VERTICAL)
            }.models = getData().also {
                data = it
            }
        }
        Handler(Looper.getMainLooper()).postDelayed(2000) {
            data.forEach {
                it.title = it.title.uppercase()
            }
        }
    }


    private fun getData(): List<ArticleModel> {
        val json = requireActivity().assets.open("article.json").use {
            it.reader().readText()
        }
        return Gson().fromJson(json, object : TypeToken<List<ArticleModel>>() {}.type)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
