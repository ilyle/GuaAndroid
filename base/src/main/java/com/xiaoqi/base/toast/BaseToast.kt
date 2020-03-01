package com.xiaoqi.base.toast

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.xiaoqi.base.R

class BaseToast {
    companion object {
        fun showMsg(ctx: Context, msg: String ) {
            val toast = Toast.makeText(ctx, "", Toast.LENGTH_SHORT)
            val view = LayoutInflater.from(ctx).inflate(R.layout.toast, null)
            view.findViewById<TextView>(R.id.tv_toast).text = msg
            toast.view = view
            toast.show()
        }

        fun showMsg(ctx: Context, resId: Int) {
            val toast = Toast.makeText(ctx, "", Toast.LENGTH_SHORT)
            val view = LayoutInflater.from(ctx).inflate(R.layout.toast, null)
            view.findViewById<TextView>(R.id.tv_toast).text = ctx.resources.getText(resId)
            toast.view = view
            toast.show()
        }
    }
}