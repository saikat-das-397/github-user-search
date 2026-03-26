package com.example.githubusersearch.domain

sealed class NetworkResult<out T> {
    object Loading : NetworkResult<Nothing>()
    object Empty : NetworkResult<Nothing>()
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Error(val code:Int, val message: String="Something went wrong") : NetworkResult<Nothing>()
}