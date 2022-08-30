package com.njagi.breakinggood.repositories

import com.njagi.breakinggood.api.BreakingApi
import com.njagi.breakinggood.models.CharacterItem
import javax.inject.Inject

class CharacterRepository @Inject constructor(private val api: BreakingApi) {

     suspend fun getAllCharacters(): List<CharacterItem> = api.getCharacters().data

}