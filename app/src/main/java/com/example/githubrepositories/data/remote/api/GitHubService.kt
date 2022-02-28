package com.example.githubrepositories.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("?q=language:kotlin&sort=stars&page={page}")
    suspend fun getRepositories(@Path("page") page: Int): Response<com.example.githubrepositories.model.Response>
}
