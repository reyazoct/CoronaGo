package com.reyaz.coronago.statewise.models

import com.google.gson.annotations.SerializedName

data class CoronaData(
    @SerializedName("cases_time_series") val casesTimeSeries: List<CasesTimeSery>?,
    @SerializedName("key_values") val keyValues: List<KeyValue>?,
    @SerializedName("statewise") val statewise: List<Statewise>?,
    @SerializedName("tested") val tested: List<Tested>?
)