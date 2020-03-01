package com.teligen.guaandroid.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.teligen.guaandroid.R
import com.teligen.guaandroid.ui.fragment.CollectionFragment
import com.teligen.guaandroid.ui.fragment.SuggestionFragment

/**
 * Created by xujie on 2020-02-20.
 * Mail : 617314917@qq.com
 */
class ArticleFragmentAdapter(manager: FragmentManager, context: Context) :
    FragmentPagerAdapter(manager) {

    private val mCount = 2
    private val mContext = context
    private val mTitle: Array<String> = arrayOf(mContext.getString(R.string.suggestion), mContext.getString(
        R.string.collection
    ))

    private val mSuggestionFragment =
        SuggestionFragment.newInstance()
    private val mCollectionFragment =
        CollectionFragment.newInstance()

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> mSuggestionFragment
            1 -> mCollectionFragment
            else -> mSuggestionFragment
        }

    override fun getCount(): Int {
        return mCount
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitle[position]
    }
}