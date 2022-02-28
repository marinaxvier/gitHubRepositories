package com.example.githubrepositories.data.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object Retrofit {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/search/repositories")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val gitHubService: GitHubService by lazy {
        retrofit.create(GitHubService::class.java)
    }
}