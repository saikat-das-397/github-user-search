package com.example.githubusersearch.data.repository

import com.example.githubusersearch.domain.NetworkResult
import com.example.githubusersearch.data.model.UserDetailDto
import com.example.githubusersearch.data.model.UserDto
import com.example.githubusersearch.data.remote.APIClient
import com.example.githubusersearch.data.remote.GitHubAPI
import com.example.githubusersearch.util.toDomain
import com.example.githubusersearch.util.toResult

class GitHubRepository(private val api: GitHubAPI = APIClient.api) {
    suspend fun searchUsers(query: String): NetworkResult<List<UserDto>>{
        return try {
            when(val result = api.searchUsers(query).toResult()){
                is NetworkResult.Success ->{
                    val users = result.data.items.map { it.toDomain() }
                    if (users.isEmpty()){
                        NetworkResult.Empty
                    }else{
                        NetworkResult.Success(users)
                    }
                }
                is NetworkResult.Error -> result
                else -> NetworkResult.Empty
            }

        }catch (e: Exception){
            NetworkResult.Error(-1,e.message ?: "Unknown error")
        }
    }

    suspend fun getUserDetail(username: String): NetworkResult<UserDetailDto>{
        return try {
            when(val result = api.getUserDetail(username).toResult()){
                is NetworkResult.Success -> NetworkResult.Success(result.data.toDomain())
                is NetworkResult.Error -> result
                else -> NetworkResult.Empty
            }
        }catch (e: Exception){
            NetworkResult.Error(-1,e.message ?: "Unknown error")

        }
    }
}