package com.njagi.breakinggood.api


import com.njagi.breakinggood.models.Characters
import com.njagi.breakinggood.utils.Constants.CHARACTER_ENDPOINT
import retrofit2.http.GET

interface BreakingApi {

    @GET(CHARACTER_ENDPOINT)
    suspend fun getCharacters() : Characters

}