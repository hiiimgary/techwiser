package com.hiimgary.techwiser.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TechyNetworkEntity (
    @SerializedName("message")
    @Expose
    var quote: String
        )