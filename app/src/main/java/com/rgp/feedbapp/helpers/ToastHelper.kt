package com.rgp.feedbapp.helpers

import android.content.Context
import android.widget.Toast

class ToastHelper(private val context: Context) {
    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}