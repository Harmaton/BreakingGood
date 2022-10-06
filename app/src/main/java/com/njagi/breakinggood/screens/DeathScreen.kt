package com.njagi.breakinggood.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.njagi.breakinggood.BadCharacters
import com.njagi.breakinggood.Error
import com.njagi.breakinggood.Loading
import com.njagi.breakinggood.models.DeathsItem
import com.njagi.breakinggood.viewmodels.CharacterState
import com.njagi.breakinggood.viewmodels.DeathState
import com.njagi.breakinggood.viewmodels.DeathsViewModel

@Composable
fun DeathScreen(deathsViewModel: DeathsViewModel = viewModel()) {
    Column(modifier = Modifier.fillMaxSize()) {
        
        when (val state = deathsViewModel.deathState.collectAsState().value) {
            
            is DeathState.Empty -> Text(text="Nothing to see here")

            is DeathState.Loading -> Loading(text = "Relax, Loading ...")

            is DeathState.Success -> Deaths(deaths = state.data)

            is DeathState.Error -> Error(text = state.message)
        }
        
    }
}

@Composable
fun Deaths(deaths: ArrayList<DeathsItem>){

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        Text(
            text = "Deaths",
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(10.dp),
            textAlign = TextAlign.Start)
        Box(modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()) {
        LazyRow(modifier = Modifier.fillMaxSize(),
        userScrollEnabled = true,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = CenterVertically)
        {

            items(deaths.size) {
                DeathCard(death = deaths[it])
            }
        }
        }
    }

}

@Composable
fun DeathCard(death: DeathsItem) {
    Card(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .background(color = androidx.compose.ui.graphics.Color.DarkGray)) {
                    Text(text = death.death)
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(text = death.responsible)

                }
               Column(modifier = Modifier
                   .fillMaxSize()
                   .padding(10.dp)) {
                   Text(text = death.lastWords)
                   Spacer(modifier = Modifier.padding(4.dp))
                   Text(text = death.cause)
               }
        }
    }

}