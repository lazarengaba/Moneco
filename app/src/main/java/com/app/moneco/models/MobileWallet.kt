package com.app.moneco.models

import com.google.gson.annotations.SerializedName

data class MultipleResourceWallet (
    @SerializedName("wallets")
    var wallets: List<Wallet>
    )

data class Wallet(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String
    )