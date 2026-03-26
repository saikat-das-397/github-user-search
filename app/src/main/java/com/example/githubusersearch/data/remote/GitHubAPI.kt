package com.example.githubusersearch.data.remote

import com.example.githubusersearch.data.model.SearchResponseDto
import com.example.githubusersearch.data.model.UserDetailDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubAPI {
    @GET("search/users")
    suspend fun searchUsers(@Query("q") query: String): Response<SearchResponseDto>

    @GET("users/{username}")
    suspend fun getUserDetail(@Path("username") username: String): Response<UserDetailDto>
}