package com.njagi.breakinggood.api


import com.njagi.breakinggood.models.Characters
import com.njagi.breakinggood.models.Deaths
import com.njagi.breakinggood.utils.Constants.CHARACTER_ENDPOINT
import com.njagi.breakinggood.utils.Constants.DEATH_ENDPOINT
import retrofit2.http.GET

interface BreakingApi {

    @GET(CHARACTER_ENDPOINT)
    suspend fun getCharacters() : Characters

    @GET(DEATH_ENDPOINT)
    suspend fun getDeaths(): Deaths

}