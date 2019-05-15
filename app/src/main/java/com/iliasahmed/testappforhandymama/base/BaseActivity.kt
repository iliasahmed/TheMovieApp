package com.iliasahmed.testappforhandymama.base

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.iliasahmed.testappforhandymama.R
import com.iliasahmed.testappforhandymama.utils.CommonUtils

class BaseActivity : AppCompatActivity() {

    var pd: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    fun showLoading() {
        hideLoading()
        pd = CommonUtils.showLoadingDialog(this)
    }

    fun hideLoading() {
        if (pd != null && pd!!.isShowing()) {
            pd!!.cancel()
        }
    }
}
