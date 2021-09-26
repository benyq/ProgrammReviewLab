package com.benyq.programmreviewlab

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.benyq.programmreviewlab.databinding.FragmentArticleBinding
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
class ArticleFragment : Fragment(R.layout.fragment_article){


    private var _binding: FragmentArticleBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentArticleBinding.bind(view)
        _binding?.tvHeader?.text = "Banner"
        initView()
    }

    private fun initView(){
        _binding?.rvArticle?.run {
            linear().setup {
                addType<ArticleModel>(R.layout.item_article)
                divider(R.color.black, DividerOrientation.VERTICAL)
            }.models = getData()
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
