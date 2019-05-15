package com.iliasahmed.testappforhandymama.appController

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.AsyncTask
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

class AppController : Application() {

    var context: Context? = null
        private set


    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        Logger.addLogAdapter(AndroidLogAdapter())
    }


}
