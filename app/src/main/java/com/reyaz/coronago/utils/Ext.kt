package com.reyaz.coronago.utils

import android.content.Context
import android.os.Handler
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.reyaz.coronago.network.retrofit.RetrofitProvider
import com.reyaz.coronago.network.retrofit.RetrofitService

fun retrofitService(): RetrofitService {
    return RetrofitProvider.instance.services
}

fun EditText.closeKeyboard(context: Context) {
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)
        ?.hideSoftInputFromWindow(windowToken, 0)
}

fun EditText.openKeyboard(context: Context) {
    Handler().postDelayed({
        requestFocus()
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)
            ?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }, 50L)
}