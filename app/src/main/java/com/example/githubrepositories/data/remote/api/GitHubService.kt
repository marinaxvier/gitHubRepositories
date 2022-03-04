package com.example.githubrepositories.data.remote.api

import com.example.githubrepositories.model.RepositoriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {
    @GET("repositories?q=language:kotlin&sort=stars")
    suspend fun getRepositories(@Query("page") page: Int): Response<RepositoriesResponse>
}
