package com.njagi.breakinggood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.njagi.breakinggood.ui.theme.BreakingGoodTheme
import com.njagi.breakinggood.viewmodels.CharacterState
import com.njagi.breakinggood.viewmodels.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BreakingGoodTheme {
                Column(modifier = Modifier.padding(10.dp)) {
                    AppHead()
                    FetchData()
                }

            }
        }
    }
}

@Composable
fun FetchData(
    characterViewModel: CharacterViewModel = viewModel()
){
 Column {
     when(val state = characterViewModel.characterstate.collectAsState().value){
         is CharacterState.Empty -> Text(text = "Nothing to see")
         is CharacterState.Loading -> Text(text = "Loading...")
         is CharacterState.Success -> Text(text = "Successful")
         is CharacterState.Error -> Text(text = "Error")
     }
 }

}

@Composable
fun AppHead(){
    Row(modifier = Modifier.fillMaxSize(),
      horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
        ) {
        IconButton(onClick = { /*TODO*/ }) {
           Icon(imageVector = Icons.Default.Create, contentDescription = "create")
        }
        
        Text(text = "Breaking Good ")

        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = "refresh")
        }

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BreakingGoodTheme {
    AppHead()
        FetchData()
    }
}