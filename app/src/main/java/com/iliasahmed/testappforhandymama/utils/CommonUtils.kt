package com.iliasahmed.testappforhandymama.utils

import android.app.Activity
import android.app.Notification
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.iliasahmed.testappforhandymama.R
import com.orhanobut.logger.Logger

public class CommonUtils {

    companion object {
        fun showLoadingDialog(context: Activity): ProgressDialog {
            val progressDialog = ProgressDialog(context)

            try {
                progressDialog.show()
                if (progressDialog.window != null) {
                    progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                }
            } catch (e: Exception) {
                Logger.d(e.message)
            }

            progressDialog.setContentView(R.layout.progress_dialog)
            progressDialog.isIndeterminate = true
            progressDialog.setCancelable(false)
            progressDialog.setCanceledOnTouchOutside(false)
            return progressDialog
        }
    }

}