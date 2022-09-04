package com.njagi.breakinggood.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.njagi.breakinggood.repositories.DeathsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class DeathsViewModel @Inject constructor(private val deathsRepository: DeathsRepository): ViewModel() {
    private var _deathstate = MutableStateFlow<DeathState>(DeathState.Empty)
    val deathState: StateFlow<DeathState> = _deathstate

    init {

    }

    private fun getAllDeaths(){
        _deathstate.value = DeathState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            val deathResponse = deathsRepository.getAllDeaths()
            try {
                _deathstate.value = DeathState.Success(deathResponse)

            }catch (e: IOException){
                _deathstate.value = DeathState.Error("Gotta Be a Problem (; ")
            }
            catch (e: HttpException){
                _deathstate.value = DeathState.Error("No Network Connection ")
            }
        }
    }

}