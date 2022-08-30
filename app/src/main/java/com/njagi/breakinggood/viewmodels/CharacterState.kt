package com.njagi.breakinggood.viewmodels

import com.njagi.breakinggood.models.CharacterItem

sealed class CharacterState{
    object Empty: CharacterState()
    object Loading: CharacterState()
    class Success(val data: List<CharacterItem>): CharacterState()
    class Error(val message: String): CharacterState()
}
