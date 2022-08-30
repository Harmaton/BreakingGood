package com.njagi.breakinggood.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.njagi.breakinggood.repositories.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val characterRepository: CharacterRepository) :ViewModel() {
    private var _characterstate = MutableStateFlow<CharacterState>(CharacterState.Empty)
     val characterstate : StateFlow<CharacterState> = _characterstate

    init {
        getAllCharacters()
    }

    private fun getAllCharacters(){
        _characterstate.value = CharacterState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val characterResponse = characterRepository.getAllCharacters()
                _characterstate.value = CharacterState.Success(characterResponse)

            }catch (exception: HttpException){
                _characterstate.value = CharacterState.Error("Check Internet")
            }catch (exception: IOException){
                _characterstate.value = CharacterState.Error("Gotta Be a Problem")
            }
        }
    }

}

