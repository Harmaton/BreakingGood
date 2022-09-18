

package com.njagi.breakinggood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
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
import com.njagi.breakinggood.models.CharactersItem
import com.njagi.breakinggood.ui.theme.BreakingGoodTheme
import com.njagi.breakinggood.ui.theme.Shapes
import com.njagi.breakinggood.viewmodels.CharacterState
import com.njagi.breakinggood.viewmodels.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BreakingGoodTheme {

                    AppHead()
                    ChipSelect()
                    FetchData()


            }
        }
    }
}

@Composable
fun FetchData(
    characterViewModel: CharacterViewModel = viewModel()
) {

    Column(modifier = Modifier.fillMaxSize()) {
        when (val state = characterViewModel.characterstate.collectAsState().value) {
            is CharacterState.Empty -> Text(text="Nothing to see here")

            is CharacterState.Loading -> Loading(text = "Relax, Loading ...")

            is CharacterState.Success -> BadCharacters(characters = state.data)

            is CharacterState.Error -> Error(text = state.message)
        }


    }
}

@Composable
fun AppHead() {

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = "Breaking Good",
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            softWrap = true,
            textDecoration = TextDecoration.LineThrough,
            modifier = Modifier.padding(12.dp),
            fontSize = 20.sp
        )

        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "refresh",
                tint = MaterialTheme.colors.
            )
        }

        IconButton(onClick = {  }) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Settings",
                tint = Color.Black
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipSelect() {
    

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp),
        horizontalArrangement = Arrangement.SpaceAround

    ) {

        Chip(onClick = { /*TODO*/ },
            enabled = true,
        shape = Shapes.medium) {
            Text(text = "Characters", fontWeight = FontWeight.Normal)
        }
        Chip(onClick = { /*TODO*/ }, shape = Shapes.medium) {
            Text(text = "Quotes", fontWeight = FontWeight.Normal,)
        }
        Chip(onClick = { /*TODO*/ }, shape = Shapes.medium) {
            Text(text = "Deaths", fontWeight = FontWeight.Normal)
        }
    }
}

@Composable
fun Loading(text:String){

    Column(modifier = Modifier.padding(50.dp)) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(25.dp), verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.Center){

            Icon(imageVector = Icons.Default.Refresh,
                contentDescription = "Loading",
                tint = Color.Red,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize()
                    .size(7.dp)
            )

                Text(text = text,
                    modifier = Modifier
                        .align(CenterVertically)
                        .padding(15.dp)
                        .fillMaxSize()
                        .size(6.dp)
                )

        }

    }
}

@Composable
fun Error(text:String){

    Column(modifier = Modifier.padding(50.dp)) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(15.dp), verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround){

            Icon(imageVector = Icons.Default.Info,
                contentDescription = "Error",
                tint = Color.Blue,
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
    Column(modifier = Modifier.padding(top = 100.dp)) {
        Text(
            text = "Characters",
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(12.dp),
            textAlign = TextAlign.Center,
        softWrap = true,
        style = MaterialTheme.typography.h5)
        Box(modifier = Modifier
            .padding(4.dp)
            .fillMaxSize()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                state = state1
            ) {
                items(characters.size) {
                    ItemDisplay(charactersItem = characters[it])
                }
            }
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
            .height(200.dp)
            .background(Color.White)
            .clickable { }
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Image(painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = charactersItem.img)
                    .apply (block = fun ImageRequest.Builder.() {
                        placeholder(coil.base.R.drawable.notification_template_icon_bg)
                    }).build()

            ), contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .align(CenterHorizontally),
                contentScale = ContentScale.FillWidth)

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = charactersItem.portrayed,
                maxLines = 1,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = charactersItem.nickname,
                maxLines = 1,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light
            )

            Spacer(modifier = Modifier.height(8.dp))


            Text(
                text = charactersItem.status,
                maxLines = 1,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )

        }
    }
}
//@Composable
//fun
//Death Details
//@Composable
//fun Deaths(deaths: ArrayList<DeathsItem>){
//
//    Column(modifier = Modifier
//        .fillMaxSize()
//        .padding(10.dp)) {
//        Text(
//            text = "Deaths",
//            fontWeight = FontWeight.Normal,
//            modifier = Modifier.padding(10.dp),
//            textAlign = TextAlign.Start)
//        Box(modifier = Modifier
//            .padding(8.dp)
//            .fillMaxSize()) {
//        LazyRow(modifier = Modifier.fillMaxSize(),
//        userScrollEnabled = true,
//        horizontalArrangement = Arrangement.SpaceEvenly,
//        verticalAlignment = CenterVertically)
//        {
//
//            items(deaths.size) {
//                DeathCard(death = deaths[it])
//            }
//        }
//        }
//    }
//
//}
//
//@Composable
//fun DeathCard(death: DeathsItem) {
//    Card(modifier = Modifier.fillMaxSize()) {
//            Row(modifier = Modifier
//                .fillMaxSize()
//                .padding(10.dp)) {
//                Column(modifier = Modifier
//                    .fillMaxSize()
//                    .padding(10.dp)
//                    .background(color = Color.LightGray)) {
//                    Text(text = death.death)
//                    Spacer(modifier = Modifier.padding(4.dp))
//                    Text(text = death.responsible)
//
//                }
//               Column(modifier = Modifier
//                   .fillMaxSize()
//                   .padding(10.dp)) {
//                   Text(text = death.lastWords)
//                   Spacer(modifier = Modifier.padding(4.dp))
//                   Text(text = death.cause)
//               }
//        }
//    }
//
//}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BreakingGoodTheme {
        AppHead()
        ChipSelect()
    }
}