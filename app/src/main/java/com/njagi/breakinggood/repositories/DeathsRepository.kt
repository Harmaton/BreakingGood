package com.njagi.breakinggood.repositories

import com.njagi.breakinggood.api.BreakingApi
import com.njagi.breakinggood.models.DeathsItem
import javax.inject.Inject

class DeathsRepository @Inject constructor(private val api: BreakingApi) {

    suspend fun getAllDeaths(): ArrayList<DeathsItem>{
        return api.getDeaths()
    }
    suspend fun getRandomDeath(): DeathsItem{
        return api.getRandomDeath()
    }
}