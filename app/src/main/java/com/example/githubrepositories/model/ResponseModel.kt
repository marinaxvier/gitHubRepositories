package com.example.githubrepositories.model

import com.google.gson.annotations.SerializedName

data class ResponseModel (
    @SerializedName("items") var repositoryList: List<RepositoryData>
)
