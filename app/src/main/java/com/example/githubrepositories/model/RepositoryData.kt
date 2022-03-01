package com.example.githubrepositories.model

import com.google.gson.annotations.SerializedName

data class RepositoryData(

    var name: String,

    @SerializedName ("stargazers_count")
    var stars: String,

    var forks: String,

    var owner: OwnerData
)
