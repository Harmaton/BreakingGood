package com.njagi.breakinggood.viewmodels

import com.njagi.breakinggood.models.DeathsItem

sealed class DeathState {
    object Empty: DeathState()
    object Loading: DeathState()
    class Success(val data: ArrayList<DeathsItem>) : DeathState()
    class Success2(val data: DeathsItem): DeathState()
    class Error(val message: String) : DeathState()
}