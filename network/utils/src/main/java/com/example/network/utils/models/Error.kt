package com.example.network.utils.models

import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("status")
    var status: String? = "",
    @SerializedName("code")
    var code: Int? = 0,
    @SerializedName("message")
    override var message: String? = ""
) : Exception()
