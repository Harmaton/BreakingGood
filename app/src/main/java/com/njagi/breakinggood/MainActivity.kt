package com.njagi.breakinggood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.njagi.breakinggood.common.HeaderText
import com.njagi.breakinggood.models.CharactersItem
import com.njagi.breakinggood.models.DeathsItem
import com.njagi.breakinggood.ui.theme.BreakingGoodTheme
import com.njagi.breakinggood.viewmodels.CharacterState
import com.njagi.breakinggood.viewmodels.CharacterViewModel
import com.njagi.breakinggood.viewmodels.DeathState
import com.njagi.breakinggood.viewmodels.DeathsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BreakingGoodTheme {

                Scaffold(topBar = { AppHead() }) { paddingValues ->

                    Column(modifier = Modifier.padding(paddingValues)
                        ) {
                        HeaderText(text = "Random Death")
                        FetchDeath()
                        HeaderText(text = "Characters")
                        FetchData(modifier = Modifier.padding(top = 2.dp))
                    }

                }
            }
        }
    }
}


@Composable
fun FetchData(
    characterViewModel: CharacterViewModel = viewModel(),
   modifier: Modifier
) {

    Column(modifier = Modifier.fillMaxSize()) {
        when (val state = characterViewModel.characterstate.collectAsState().value) {
            is CharacterState.Empty -> Text(text = "Nothing to see here")

            is CharacterState.Loading -> Loading(text = "Relax, Loading ...")

            is CharacterState.Success -> BadCharacters(characters = state.data)

            is CharacterState.Error -> Error(text = state.message)
        }


    }
}

@Composable
fun FetchDeath(
    deathsViewModel: DeathsViewModel = viewModel()
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(1.dp)
        .height(180.dp)) {

       when(val state = deathsViewModel.randomdeathstate.collectAsState().value) {

           is DeathState.Empty -> Text(text = "Nothing to see here")

           is DeathState.Loading -> Loading(text = "Relax, Loading ...")

           is DeathState.Success2 -> RandomDeathCard(deathsItem = state.data)

           is DeathState.Error -> Text(text = state.message)

           else -> {
               Text(text = "No viewModel")
           }
       }
    }

}

@Composable
fun AppHead() {

    TopAppBar(
        contentPadding = PaddingValues(10.dp),
        backgroundColor = Color.Transparent
    )
    {

        Text(
            text = "Breaking Good",
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            softWrap = true,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.padding(12.dp),
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.width(65.dp))

        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Rounded.Notifications,
                contentDescription = "notify new", tint = Color.Black
            )

        }

        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "refresh",
                tint = MaterialTheme.colors.onSecondary
            )


        }


    }
}



@Composable
fun Loading(text: String) {

    Column(modifier = Modifier.padding(150.dp)) {
        CircularProgressIndicator(
            progress = 0.45f,
            color = Color.Black,
            modifier = Modifier.size(30.dp)
        )

        Row(modifier = Modifier.fillMaxSize()) {
            Text(text = text, color = Color.Green)
        }
    }

}

@Composable
fun Error(text: String) {

    Column(modifier = Modifier.padding(150.dp)) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp), verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Error",
                tint = Color.Red,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize()
                    .size(4.dp)
            )

            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp),
                text = text,
                color = Color.Red
            )
        }
    }
}

@Composable
fun BadCharacters(characters: ArrayList<CharactersItem>) {

    val state1 = rememberLazyGridState(0)

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(12.dp),
            state = state1
        ) {
            items(characters.size) {
                ItemDisplay(charactersItem = characters[it])
            }
        }
    }


@Composable
fun ItemDisplay(charactersItem: CharactersItem) {
    // character item
    Card(elevation = 2.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
            .background(Color.White)
            .clickable { }
            .padding(4.dp),
        border = BorderStroke(1.dp, color = Color.Gray)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = charactersItem.img)
                        .apply(block = fun ImageRequest.Builder.() {
                            placeholder(coil.base.R.drawable.notification_template_icon_bg)
                        }).build()

                ), contentDescription = null,
                modifier = Modifier
                    .width(120.dp)
                    .height(120.dp)
                    .align(CenterHorizontally),
                contentScale = ContentScale.FillWidth
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = charactersItem.nickname,
                maxLines = 1,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = charactersItem.portrayed,
                maxLines = 1,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = charactersItem.status,
                maxLines = 1,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )

            Text(text = charactersItem.category,
                maxLines = 2,
                fontWeight = FontWeight.Light,
                fontSize = 12.sp,
                color = Color.Gray

            )

        }
    }
}

@Composable
fun RandomDeathCard(deathsItem: DeathsItem ) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(180.dp), shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.LightGray,
        border = BorderStroke(1.dp, Color.Cyan)
    ) {


        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = deathsItem.img)
                    .apply(block = fun ImageRequest.Builder.() {
                        placeholder(coil.base.R.drawable.notification_template_icon_bg)
                    }).build()

            ), contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds



        )

        Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ){
            Column(modifier = Modifier.padding(4.dp)) {

                    Text(text = deathsItem.death,fontSize = 15.sp, color = Color.Red, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "lastwords:"+" "+" "+deathsItem.lastWords,fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Red,
                        fontStyle = FontStyle.Italic)
            }

            Column(modifier = Modifier.padding(4.dp)) {
                Text(text = deathsItem.cause,fontSize = 14.sp, color = Color.Red, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Responsible:"+" "+deathsItem.responsible, fontSize = 12.sp, color = Color.Red,fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Italic )
            }

    }
}


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BreakingGoodTheme {
        AppHead()
    }
}