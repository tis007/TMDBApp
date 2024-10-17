package com.example.apptest

import android.graphics.drawable.shapes.Shape
import android.util.LayoutDirection
import android.util.Log
import android.util.Size
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Density
import androidx.compose.ui.zIndex
import kotlin.io.path.Path


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


@Composable
fun DetailsComponent(
    backdropPath: String,
    posterPath: String,
    title: String,
    subTitle: String,
    genres: List<String>,
    synopsis: String,
    //castList: List<CanBeCarded>
) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    val backdropPainter =
        rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w780$backdropPath.jpg")
    val posterPainter =
        rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w780$posterPath.jpg")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            Image(
                painter = backdropPainter,
                contentDescription = "Backdrop",
                modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer { alpha = 0.99f }
                    .drawWithContent {
                        val colors = listOf(
                            Color.Black,
                            Color.Transparent
                        )
                        drawContent()
                        drawRect(
                            brush = Brush.verticalGradient(colors),
                            blendMode = BlendMode.DstIn
                        )
                    },
                contentScale = ContentScale.Crop
            )
            // Poster image
            Image(
                painter = posterPainter,
                contentDescription = "Poster",
                modifier = Modifier
                    .width(screenWidth / 3)
                    .aspectRatio(1 / 1.66f)
                    .border(2.dp, Color.White)
                    .align(Alignment.BottomCenter),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Title
        Text(
            text = title,
            fontSize = 30.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))


        Text(
            text = subTitle,
            fontSize = 15.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = customTextBuilder("Genres: ", genres.joinToString(", ")),
            fontSize = 18.sp,
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = customTextBuilder("Synopsis: ", synopsis),
            fontSize = 19.sp,
            textAlign = TextAlign.Justify

        )

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Acteurs",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        //GridComponent(canBeCardedList = castList)
    }
}


fun customTextBuilder(firstText: String, secondText: String): AnnotatedString = buildAnnotatedString {
    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
        append(firstText)
    }
    append(secondText)
}