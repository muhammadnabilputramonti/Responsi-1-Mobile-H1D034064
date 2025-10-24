package com.example.responsi1mobileh1d023063.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.responsi1mobileh1d023063.api.RetrofitClient
import com.example.responsi1mobileh1d023063.model.TeamResponse
import kotlinx.coroutines.launch

class TeamViewModel : ViewModel() {

    private val _teamData = MutableLiveData<TeamResponse?>()
    val teamData: LiveData<TeamResponse?> = _teamData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    init {
        fetchTeamData(71)
    }

    fun fetchTeamData(teamId: Int) {
        _isLoading.value = true
        _errorMessage.value = null

        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.getTeamDetails(teamId)

                if (response.isSuccessful) {
                    _teamData.value = response.body()
                } else {
                    _errorMessage.value = "Error: ${response.code()} ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Koneksi Gagal: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}