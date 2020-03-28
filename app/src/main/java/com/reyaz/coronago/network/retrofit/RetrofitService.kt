package com.reyaz.coronago.network.retrofit

import com.reyaz.coronago.statewise.models.CoronaData
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {

    @GET("/data.json")
    suspend fun fetchCoronaData(): Response<CoronaData>
}