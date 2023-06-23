package com.app.moneco.models

import com.google.gson.annotations.SerializedName

data class Recipient(
    @SerializedName("id")
    var id: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("country")
    var country: String,
    @SerializedName("mobile_wallet")
    var mobileWallet: String
)