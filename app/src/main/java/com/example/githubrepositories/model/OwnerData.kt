package com.example.githubrepositories.model

import com.google.gson.annotations.SerializedName

data class OwnerData (
    @SerializedName("login")
    var name: String,

    @SerializedName("avatar_url")
    var avatar: String
)
