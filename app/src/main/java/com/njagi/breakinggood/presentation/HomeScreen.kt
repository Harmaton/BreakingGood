package com.njagi.breakinggood.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.njagi.breakinggood.common.*
import com.njagi.breakinggood.viewmodels.CharacterState
import com.njagi.breakinggood.viewmodels.CharacterViewModel
import com.njagi.breakinggood.viewmodels.DeathState
import com.njagi.breakinggood.viewmodels.DeathsViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen() {
      Column(modifier = Modifier.fillMaxSize()){
          AppHead()
          Spacer(modifier = Modifier.height(10.dp))
          HeaderText(text = "Random Death")
          Spacer(modifier = Modifier.height(10.dp))
          FetchRandomDeath()
          Spacer(modifier = Modifier.height(10.dp))
          HeaderText(text = "Characters")
          Spacer(modifier = Modifier.height(10.dp))
          FetchData(modifier = Modifier.padding(top = 8.dp))
      }


    }


@Composable
fun FetchData(
    characterViewModel: CharacterViewModel= viewModel(),
    modifier: Modifier
) {


        when (val state = characterViewModel.characterstate.collectAsState().value) {
        is CharacterState.Empty -> Text(text = "Nothing to see here")

        is CharacterState.Loading -> Loading(text = " ... ")

        is CharacterState.Success -> BadCharacters(characters = state.data)

        is CharacterState.Error -> Error(text = state.message)
    }


    }



@Composable
fun FetchRandomDeath(
    deathsViewModel: DeathsViewModel = viewModel(),
) {
    when (val state2 = deathsViewModel.randomdeathstate.collectAsState().value) {

        is DeathState.Empty -> Text(text = "Nothing to see here")

        is DeathState.Loading -> Loading(text = "Relax, Loading ...")

        is DeathState.Success2 -> RandomDeathCard(deathsItem = state2.data)

        is DeathState.Error -> Error(text = state2.message)
    }

}




