package com.reyaz.coronago.statewise.models

import com.google.gson.annotations.SerializedName

data class KeyValue(
    @SerializedName("confirmeddelta") val confirmeddelta: String?,
    @SerializedName("counterforautotimeupdate") val counterforautotimeupdate: String?,
    @SerializedName("deceaseddelta") val deceaseddelta: String?,
    @SerializedName("lastupdatedtime") val lastUpdatedTime: String?,
    @SerializedName("recovereddelta") val recovereddelta: String,
    @SerializedName("statesdelta") val statesdelta: String
)