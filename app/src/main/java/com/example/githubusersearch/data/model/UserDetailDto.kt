package com.example.githubusersearch.data.model

import com.google.gson.annotations.SerializedName

data class UserDetailDto(
    @SerializedName("login") val username: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("name") val name: String?,
    @SerializedName("bio") val bio: String?,
    @SerializedName("followers") val followers: Int,
    @SerializedName("following") val following: Int,
    @SerializedName("public_repos") val publicRepos: Int,
    @SerializedName("html_url") val profileUrl: String
)