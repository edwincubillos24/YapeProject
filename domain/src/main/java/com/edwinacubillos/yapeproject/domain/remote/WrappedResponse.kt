package com.edwinacubillos.yapeproject.domain.remote

import com.edwinacubillos.domain.remote.Status
import com.google.gson.annotations.SerializedName

data class WrappedResponse<D>(
    @SerializedName("status") val status: Status,
    @SerializedName("data") val data: D? = null,
    @SerializedName("error") val error: ErrorResponse? = null
)
