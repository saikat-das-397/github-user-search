package com.example.githubusersearch.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubusersearch.data.model.UserDto
import com.example.githubusersearch.data.repository.GitHubRepository
import com.example.githubusersearch.domain.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: GitHubRepository = GitHubRepository()) : ViewModel() {
    val _state = MutableStateFlow<NetworkResult<List<UserDto>>>(NetworkResult.Loading)
    val state : StateFlow<NetworkResult<List<UserDto>>> = _state

    fun searchUsers(query: String) {
        viewModelScope.launch {
            _state.value = NetworkResult.Loading
            val result = repository.searchUsers(query)
            _state.value = result
        }
    }
}