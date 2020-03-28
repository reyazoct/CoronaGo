package com.reyaz.coronago.statewise.models

import com.google.gson.annotations.SerializedName

data class Tested(
    @SerializedName("source") val source: String?,
    @SerializedName("totalindividualstested") val totalindividualstested: String?,
    @SerializedName("totalpositivecases") val totalpositivecases: String,
    @SerializedName("totalsamplestested") val totalsamplestested: String,
    @SerializedName("updatetimestamp") val updatetimestamp: String
)