package com.reyaz.coronago.statewise.repositories

import com.reyaz.coronago.utils.retrofitService

class StateWiseRepository() {

    suspend fun fetchCoronaData() = retrofitService().fetchCoronaData()

    suspend fun fetchStateSpecificData() = retrofitService().fetchStateSpecificData()
}