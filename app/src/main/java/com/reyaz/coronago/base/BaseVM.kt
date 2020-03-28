package com.reyaz.coronago.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class BaseVM : ViewModel() {
    val coroutineScope = CoroutineScope(Dispatchers.Main)
}