package com.example.githubrepositories.model

import com.google.gson.annotations.SerializedName

data class RepositoriesResponse (
    @SerializedName("items") var repositoryList: ArrayList<RepositoryData>
)
