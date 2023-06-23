package com.app.moneco.api.services

import com.app.moneco.models.Country
import com.app.moneco.models.Recipient
import retrofit2.http.GET

interface ApiService {
    @GET("recipients")
    suspend fun fetchRecipients(): List<Recipient>

    @GET("countries")
    suspend fun fetchCountries(): List<Country>
}
