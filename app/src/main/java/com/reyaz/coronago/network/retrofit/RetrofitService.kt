package com.reyaz.coronago.network.retrofit

import com.reyaz.coronago.statewise.models.CoronaData
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {

    @GET("/data.json")
    suspend fun fetchCoronaData(): Response<CoronaData>

    @GET("/state_district_wise.json")
    suspend fun fetchStateSpecificData(): Response<ResponseBody>
}