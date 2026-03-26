package com.example.githubusersearch.data.model

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("login") val username: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("html_url") val profileUrl: String
)
data class SearchResponseDto(
    @SerializedName("items") val items: List<UserDto>
)