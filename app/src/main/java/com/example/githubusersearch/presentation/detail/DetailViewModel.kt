package com.example.githubusersearch.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubusersearch.data.model.UserDetailDto
import com.example.githubusersearch.data.repository.GitHubRepository
import com.example.githubusersearch.domain.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: GitHubRepository = GitHubRepository()) : ViewModel() {
    val _state = MutableStateFlow<NetworkResult<UserDetailDto>>(NetworkResult.Loading)
    val state : StateFlow<NetworkResult<UserDetailDto>> = _state

    fun loadUserDetail(username: String) {
        viewModelScope.launch {
            _state.value = NetworkResult.Loading
            val result = repository.getUserDetail(username)
            _state.value = result
        }
    }

}