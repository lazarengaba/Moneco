package com.app.moneco.application.transfer.repositories

import com.app.moneco.api.ApiClient
import com.app.moneco.models.Country
import com.app.moneco.models.Recipient

class RecipientRepository {
    private val apiService = ApiClient.apiService

    suspend fun fetchRecipients(): List<Recipient> {
        return apiService.fetchRecipients()
    }

    suspend fun fetchCountries(): List<Country> {
        return apiService.fetchCountries()
    }
}