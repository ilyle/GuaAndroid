package com.xiaoqi.base.web

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.just.agentweb.AgentWeb
import com.xiaoqi.base.BaseFragment
import com.xiaoqi.base.R
import kotlinx.android.synthetic.main.fragment_web.*


open class WebFragment : BaseFragment(), View.OnClickListener {


    /**
     * 携带url的对象
     */
    private var mUrl: String? = null

    /**
     *  标题
     */
    private var mTitle: String? = null

    /**
     * 实现Parcelable的对象
     */
    private var mObj: Parcelable? = null

    /**
     * Web控件
     */
    private lateinit var mAgentWeb: AgentWeb

    override fun getLayoutId(): Int {
        return R.layout.fragment_web
    }

    override fun initView(view: View) {
        super.initView(view)
        tb_web.title = mTitle
        tb_web.inflateMenu(R.menu.menu_more)
    }

    override fun setListener() {
        super.setListener()
        tb_web.setNavigationOnClickListener { activity?.finish() }
        tb_web.setOnMenuItemClickListener { onMenuItemClickListener() }
    }

    open fun onMenuItemClickListener(): Boolean {
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = activity?.intent
        intent?.let {
            mUrl = it.getStringExtra(WebActivity.url)
            mTitle = it.getStringExtra(WebActivity.title)
            mObj = it.getParcelableExtra(WebActivity.obj)
        }
    }

    override fun onResume() {
        super.onResume()
        load(mUrl)
    }

    override fun onPause() {
        super.onPause()
        mAgentWeb.webLifeCycle.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mAgentWeb.webLifeCycle.onDestroy()
    }


    override fun onClick(p0: View?) {

    }

    private fun load(url: String?) {
        mAgentWeb = AgentWeb.with(this)
            .setAgentWebParent(
                fl_web,
                FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            )
            .useDefaultIndicator()
            .createAgentWeb()
            .ready()
            .go(url)
        val webView = mAgentWeb.webCreator.webView
        val settings = webView.settings
        settings.setSupportZoom(true)
        settings.builtInZoomControls = true
        settings.displayZoomControls = false
        settings.useWideViewPort = true
        settings.loadsImagesAutomatically = true
        // settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
    }

    companion object {
        fun newInstance(): WebFragment {
            return WebFragment()
        }
    }
}