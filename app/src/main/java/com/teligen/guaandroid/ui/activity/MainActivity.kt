package com.teligen.guaandroid.ui.activity

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.teligen.guaandroid.viewmodel.MainViewModel
import com.teligen.guaandroid.R
import com.teligen.guaandroid.databinding.ActivityMainBinding
import com.xiaoqi.base.mvvm.BaseMvvmActivity
import com.xiaoqi.guagua.mvp.model.bean.Banner
import com.youth.banner.loader.ImageLoader

class MainActivity : BaseMvvmActivity<MainViewModel, ActivityMainBinding>() {
    override fun getVMClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        super.initView()
        mBinding.banner.setImageLoader(object : ImageLoader() {
            override fun displayImage(context: Context, path: Any, imageView: ImageView) {
                Glide.with(context).load(path).into(imageView)
            }
        })
    }

    override fun setListener() {
        super.setListener()
        mBinding.btnBanner.setOnClickListener {
            getBanners()
        }
    }

    private fun getBanners() {
        mViewModel.getBanners().observe(this, Observer {
            observeStateLiveData(it)
        })
    }

    private fun showBanners(bannerList: List<Banner>) {
        val imageUrlList = mutableListOf<String>()
        for (banner in bannerList) {
            banner.imagePath?.let { imageUrlList.add(it) }
        }
        mBinding.banner.setImages(imageUrlList)
        mBinding.banner.start()
    }

    override fun onResume() {
        super.onResume()
        mBinding.banner.startAutoPlay()
    }

    override fun onStop() {
        super.onStop()
        mBinding.banner.stopAutoPlay()
    }
}
