package com.reyaz.coronago.statewise.models

import com.google.gson.annotations.SerializedName

data class Delta(
    @SerializedName("active") val active: Int?,
    @SerializedName("confirmed") val confirmed: Int?,
    @SerializedName("deaths") val deaths: Int?,
    @SerializedName("recovered") val recovered: Int?
)