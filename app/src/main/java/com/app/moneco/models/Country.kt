package com.app.moneco.models

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("id")
    var id: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("currency_code")
    var currencyCode: String
)