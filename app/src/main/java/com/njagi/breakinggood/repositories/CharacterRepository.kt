package com.njagi.breakinggood.repositories

import com.njagi.breakinggood.api.BreakingApi
import com.njagi.breakinggood.models.CharactersItem
import javax.inject.Inject

class CharacterRepository @Inject constructor(private val api: BreakingApi) {

     suspend fun getAllCharacters(): ArrayList<CharactersItem> {
          return api.getCharacters()
     }

}