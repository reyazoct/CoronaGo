package com.reyaz.coronago.statewise.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Statewise(
    @SerializedName("active") val active: String?,
    @SerializedName("confirmed") val confirmed: String?,
    @SerializedName("deaths") val deaths: String?,
    @SerializedName("deltaconfirmed") val deltaConfirmed: String?,
    @SerializedName("deltadeaths") val deltaDeaths: String?,
    @SerializedName("deltarecovered") val deltaRecovered: String?,
    @SerializedName("lastupdatedtime") val lastUpdatedTime: String?,
    @SerializedName("recovered") val recovered: String?,
    @SerializedName("state") val state: String?
) : Parcelable