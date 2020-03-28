package com.reyaz.coronago.utils

import com.reyaz.coronago.network.retrofit.RetrofitProvider
import com.reyaz.coronago.network.retrofit.RetrofitService

fun retrofitService(): RetrofitService {
    return RetrofitProvider.instance.services
}

fun Float?.orZero() = this ?: 0.0f