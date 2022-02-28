package com.example.githubrepositories.data.remote.api

import com.example.githubrepositories.model.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("?q=language:kotlin&sort=stars&page={page}")
    suspend fun getRepositories(@Path("page") page: Int): Response
}
