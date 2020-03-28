package com.reyaz.coronago.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.net.ConnectException
import java.net.SocketTimeoutException

abstract class BaseVM : ViewModel() {
    val errorMessage = MutableLiveData<String>()

    val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        errorMessage.value = handleException(exception)
    }

    val coroutineScope = CoroutineScope(Dispatchers.Main + exceptionHandler)

    private fun handleException(e: Throwable): String {
        var errorMessage = String()

        if (e is java.net.UnknownHostException || e is ConnectException) {
            errorMessage = "No Internet"
        } else if (e is SocketTimeoutException) {
            errorMessage = "Slow Internet"
        } else {
            try {
                errorMessage = e.localizedMessage
            } catch (e: Exception) {
                e.localizedMessage
            }
        }
        return errorMessage
    }
}