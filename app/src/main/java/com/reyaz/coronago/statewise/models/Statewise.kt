package com.reyaz.coronago.statewise.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Statewise(
    @SerializedName("active") val active: String?,
    @SerializedName("confirmed") val confirmed: String?,
    @SerializedName("deaths") val deaths: String?,
    @SerializedName("delta") val delta: Delta?,
    @SerializedName("lastupdatedtime") val lastupdatedtime: String?,
    @SerializedName("recovered") val recovered: String?,
    @SerializedName("state") val state: String?
) : Parcelable