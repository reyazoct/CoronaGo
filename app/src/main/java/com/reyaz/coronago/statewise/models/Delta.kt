package com.reyaz.coronago.statewise.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Delta(
    @SerializedName("active") val active: Int?,
    @SerializedName("confirmed") val confirmed: Int?,
    @SerializedName("deaths") val deaths: Int?,
    @SerializedName("recovered") val recovered: Int?
) : Parcelable