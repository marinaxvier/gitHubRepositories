package com.example.githubrepositories.data.repository

import com.example.githubrepositories.data.remote.api.Retrofit
import com.example.githubrepositories.model.RepositoriesResponse
import retrofit2.Response

class Repository {

    suspend fun getRepositories(page: Int): Response<RepositoriesResponse>{
       return Retrofit.gitHubService.getRepositories(page)
    }
}