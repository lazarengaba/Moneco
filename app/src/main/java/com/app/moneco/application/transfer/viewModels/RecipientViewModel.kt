package com.app.moneco.application.transfer.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.moneco.application.transfer.repositories.RecipientRepository
import com.app.moneco.models.Country
import com.app.moneco.models.Recipient
import kotlinx.coroutines.launch

class RecipientViewModel: ViewModel() {
    private val repository = RecipientRepository()

    private val _recipients = MutableLiveData<List<Recipient>>()
    private val _isRecipientsFetched = MutableLiveData<Boolean>()
    val recipients: LiveData<List<Recipient>> = _recipients
    var isRecipientsFetched: LiveData<Boolean> = _isRecipientsFetched

    fun fetchRecipients() {
        viewModelScope.launch {
            try {
                val recipients = repository.fetchRecipients()
                _recipients.value = recipients
            } catch (e: Exception) {
                // Handle error
            }
            _isRecipientsFetched.value = true
        }
    }

    private val _countries = MutableLiveData<List<Country>>()
    private val _isCountriesFetched = MutableLiveData<Boolean>()
    val countries: LiveData<List<Country>> = _countries
    var isCountiesFetched: LiveData<Boolean> = _isCountriesFetched

    fun fetchCountries() {
        viewModelScope.launch {
            try {
                val countries = repository.fetchCountries()
                _countries.value = countries
            } catch (e: Exception) {
                // Handle error
            }
            _isRecipientsFetched.value = true
        }
    }
}