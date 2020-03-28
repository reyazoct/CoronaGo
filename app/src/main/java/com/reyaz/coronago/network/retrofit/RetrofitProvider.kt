package com.reyaz.coronago.network.retrofit

import com.reyaz.coronago.network.utils.ApiConstants.API_CONNECT_TIMEOUT_VALUE
import com.reyaz.coronago.network.utils.ApiConstants.API_READ_TIMEOUT_VALUE
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitProvider private constructor() {
    val services: RetrofitService

    private object Holder {
        val INSTANCE = RetrofitProvider()
    }

    init {
        services = defaultRetrofitClient.create(RetrofitService::class.java)
    }

    companion object {

        val instance: RetrofitProvider by lazy { Holder.INSTANCE }
        val defaultRetrofitClient: Retrofit = createRetrofitClient(defaultOkHttpClientBuilder)

        private fun createRetrofitClient(okhttpClientBuilder: OkHttpClient.Builder) =
            Retrofit.Builder()
                .baseUrl("https://api.covid19india.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpClientBuilder.build())
                .build()

        private val defaultOkHttpClientBuilder: OkHttpClient.Builder
            get() {
                val okHttpClientBuilder = OkHttpClient.Builder()
                with(okHttpClientBuilder) {
                    connectTimeout(API_CONNECT_TIMEOUT_VALUE, TimeUnit.SECONDS)
                    readTimeout(API_READ_TIMEOUT_VALUE, TimeUnit.SECONDS)
                }
                return okHttpClientBuilder
            }
    }
}