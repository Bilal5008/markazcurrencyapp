package com.markaz.currencyapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.markaz.currencyapp.base.application.MarkazCurrencyApplication

object NetworkUtils {
    const val UTF_8 = "UTF-8"
    val isNetworkAvailable: Boolean
        get() {
            val appContext: Context? = MarkazCurrencyApplication.instance
            val connMgr: ConnectivityManager = appContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo: NetworkInfo = connMgr.activeNetworkInfo!!
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

    private fun getNetworkInfo(context: Context): NetworkInfo {
        val cm: ConnectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo!!
    }

    fun isConnectedMobile(context: Context): Boolean {
        val info: NetworkInfo = getNetworkInfo(context)
        return info != null && info.isConnected && info.type == ConnectivityManager.TYPE_MOBILE
    }
}