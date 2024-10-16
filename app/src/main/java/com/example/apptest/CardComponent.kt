package com.example.apptest

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter

@Composable
fun CardComponent(
    posterPath: String?,
    title: String,
    subTitle: String,
    onClick: () -> Unit,

    ) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val padding = screenWidth / 50



    Card(
        modifier = Modifier
            .padding(padding)
            .fillMaxWidth()
            .fillMaxHeight()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Log.i("TEST", "posterPath: $posterPath")
            val painter: Painter
            if (posterPath != null) {
                painter =
                    rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w780$posterPath.jpg")
            } else {
                painter = painterResource(id = R.drawable.baseline_question_mark_24)
            }


            Image(
                painter = painter,
                contentDescription = "$title poster",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1 / 1.66f),
                contentScale = ContentScale.Crop // Set the content scale to crop
            )

            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = title,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp)) // Add some space between title and subtext
            if (subTitle.isNotEmpty()) {
                Text(
                    text = subTitle, // Add the subtext
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun GridComponent(canBeCardedList: List<CanBeCarded>) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.screenWidthDp < configuration.screenHeightDp
    val columns = if (isPortrait) 2 else 3

    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = Modifier.padding(16.dp)
    ) {
        items(canBeCardedList) { canBeCarded ->
            CardComponent(
                posterPath = canBeCarded.getPosterPathForCard(),
                title = canBeCarded.getTitleForCard(),
                subTitle = canBeCarded.getSubTitleForCard(),
                onClick = {
                    // Your onClick action here
                    println("Card clicked!")
                }
            )
        }
    }
}
