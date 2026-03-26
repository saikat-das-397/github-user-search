package com.example.githubusersearch.util

import android.view.View
import com.example.githubusersearch.domain.NetworkResult
import com.example.githubusersearch.data.model.UserDetailDto
import com.example.githubusersearch.data.model.UserDto
import retrofit2.Response

fun UserDto.toDomain() = UserDto(
        username = username,
        avatarUrl = avatarUrl,
        profileUrl = profileUrl)

fun UserDetailDto.toDomain() = UserDetailDto(
        username = username,
        avatarUrl = avatarUrl,
        name = name,
        bio = bio,
        followers = followers,
        following = following,
        publicRepos = publicRepos,
        profileUrl = profileUrl)

fun <T> Response<T>.toResult(): NetworkResult<T> {
    return if (isSuccessful&& body() != null) {
        NetworkResult.Success(body()!!)
    } else {
        NetworkResult.Error(code(), message())
    }
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

