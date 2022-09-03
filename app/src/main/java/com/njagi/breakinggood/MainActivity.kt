@file:Suppress("OPT_IN_IS_NOT_ENABLED")

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.njagi.breakinggood.models.CharactersItem
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
                    AppHead()
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
            is CharacterState.Empty -> Text(text = "Nothing to see",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp),
                color = Color.Red)

            is CharacterState.Loading -> Text(text = "Loading....loading anim coming")

            is CharacterState.Success -> BadCharacters(characters = state.data)

            is CharacterState.Error -> Text(
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp),
                text = state.message,
                color = Color.Red
            )
        }
    }
}

@Composable
fun AppHead() {

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {

        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "buy me a coffee",
                tint = Color.Blue
            )
        }

        Text(
            text = "Breaking Saul ",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.paddingFromBaseline(top = 20.dp),
            fontStyle = FontStyle.Italic,
            fontSize = 26.sp,
            textAlign = TextAlign.Center)

        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "refresh",
                tint = Color.Blue
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
        Chip(onClick = { /*TODO*/ }, enabled = true) {
            Text(text = "Breaking Bad", fontWeight = FontWeight.Black)
        }
        Chip(onClick = { /*TODO*/ }) {
            Text(text = "Better Call Saul", fontWeight = FontWeight.Black)
        }
        Chip(onClick = { /*TODO*/ }) {
            Text(text = "Deaths", fontWeight = FontWeight.Black)
        }
    }
}

@Composable
fun BadCharacters(characters: ArrayList<CharactersItem>) {
    val state1 = rememberLazyGridState(0)
    Column(modifier = Modifier.padding(top = 80.dp)) {
        Text(
            text = "Breaking Bad Characters",
            fontWeight = FontWeight.Thin,
            modifier = Modifier.padding(top = 15.dp),
            textAlign = TextAlign.Justify)
        Box(modifier = Modifier
            .padding(16.dp)
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
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .background(Color.White)
            .clickable { }
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Image(painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = charactersItem.img)
                    .apply (block = fun ImageRequest.Builder.() {
                        placeholder(coil.base.R.drawable.notification_template_icon_bg)
                        crossfade(true)
                    }).build()

            ), contentDescription = null,
                modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .align(CenterHorizontally),
                contentScale = ContentScale.Inside)

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = charactersItem.status,
                maxLines = 1,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = charactersItem.category,
                maxLines = 1,
                fontSize = 12.sp,
                fontWeight = FontWeight.ExtraLight
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom,
            ) {
                
                Icon(imageVector = Icons.Default.FavoriteBorder,
                    tint = Color.Blue,
                    contentDescription = null,
                    modifier = Modifier
                        .size(18.dp)
                        .align(CenterVertically))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BreakingGoodTheme {
        AppHead()
        ChipSelect()
    }
}