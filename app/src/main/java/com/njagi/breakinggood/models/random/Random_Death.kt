package com.njagi.breakinggood.models.random


import com.google.gson.annotations.SerializedName

data class Random_Death(
    @SerializedName("appearance")
    val appearance: List<Int>,
    @SerializedName("cause")
    val cause: String,
    @SerializedName("death")
    val death: String,
    @SerializedName("death_id")
    val deathId: Int,
    @SerializedName("episode")
    val episode: Int,
    @SerializedName("img")
    val img: String,
    @SerializedName("last_words")
    val lastWords: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("occupation")
    val occupation: List<String>,
    @SerializedName("responsible")
    val responsible: String,
    @SerializedName("season")
    val season: Int
)