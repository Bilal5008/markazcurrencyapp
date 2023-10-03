package com.markaz.currencyapp.utils

import android.content.Context
import android.widget.Toast

object Utility {


    fun showToast(context: Context, message: String) {
        if (message.isBlank()) return
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}