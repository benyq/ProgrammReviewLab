package com.benyq.programmreviewlab

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.benyq.programmreviewlab.databinding.FragmentArticleBinding
import com.drake.brv.utils.linear
import com.drake.brv.utils.models
import com.drake.brv.utils.setup

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

    }

    private fun initView(){
        _binding?.rvArticle?.run {
            linear().setup {
                addType<ArticleModel>(R.layout.item_article)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
