package com.njagi.breakinggood.viewmodels

import com.njagi.breakinggood.models.CharactersItem

sealed class CharacterState{
    object Empty: CharacterState()
    object Loading: CharacterState()
    class Success(val data: ArrayList<CharactersItem>): CharacterState()
    class Error(val message: String): CharacterState()
}
