package com.example.apptest

import actorCardClickAction
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import kotlin.math.log


@Composable
fun CardComponent(
    posterPath: String?,
    title: String,
    subTitle: String,
    cardClickAction: () -> Unit,

    ) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val padding = screenWidth / 50



    Card(
        modifier = Modifier
            .padding(padding)
            .fillMaxWidth()
            .fillMaxHeight()
            .clickable { cardClickAction() },
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            val poster: Painter
            if (posterPath != null) {
                poster =
                    rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w780$posterPath.jpg")
            } else {
                poster = painterResource(id = R.drawable.baseline_question_mark_24)
            }


            Image(
                painter = poster,
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
fun GridComponent(
    canBeCardedList: List<CanBeCarded>,
    cardClickAction: (NavHostController, String) -> Unit,
    navController: NavHostController

) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.screenWidthDp < configuration.screenHeightDp
    val columns = if (isPortrait) 2 else 4

    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = Modifier.padding(16.dp)
    ) {
        items(canBeCardedList) { canBeCarded ->
            CardComponent(
                posterPath = canBeCarded.getPosterPath(),
                title = canBeCarded.getTitleName(),
                subTitle = canBeCarded.getDate(),
                cardClickAction = {
                    cardClickAction(navController, canBeCarded.getLinkToToDetails())
                }
            )
        }
    }
}


@Composable
fun DetailsComponent(
    navController: NavHostController,
    canBeDetailed: CanBeDetailed
) {

    Log.i("DetailsComponent", canBeDetailed.toString())


    val backdropPath: String = canBeDetailed.getBackdropPath()
    val posterPath: String = canBeDetailed.getPosterPath() ?: ""
    val title: String = canBeDetailed.getTitleName()
    val subTitle: String = canBeDetailed.getDate()
    val genres: List<String> = canBeDetailed.getGenresNames()
    val synopsis: String = canBeDetailed.getSynopsis()
    val castList: List<Cast> = canBeDetailed.getCastProfilPath()

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val backdropPainter =
        rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w780$backdropPath.jpg")

    val isPortrait = configuration.screenWidthDp < configuration.screenHeightDp
    val columns = if (isPortrait) 2 else 4

    val widthImages = if (isPortrait) screenWidth / 3 else screenWidth / 6


    val posterPainter: Painter
    if (posterPath != null) {
        posterPainter =
            rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w780$posterPath.jpg")
    } else {
        posterPainter = painterResource(id = R.drawable.baseline_question_mark_24)
    }


    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = Modifier.padding(16.dp)
    ) {


        item(span = { GridItemSpan(columns) }) {
            BackdropAndPoster(backdropPainter, posterPainter, widthImages)
        }

        item(span = { GridItemSpan(columns) }) {
            TextForDetails(
                title, subTitle,
                customTextBuilder("Genres: ", genres.joinToString(", ")),
                customTextBuilder("Synopsis: ", synopsis)
            )


        }
        item(span = { GridItemSpan(columns) }) {
            Text(
                text = "Acteurs",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }



        items(castList) { castList ->
            CardComponent(
                posterPath = castList.getPosterPath(),
                title = castList.getTitleName(),
                subTitle = castList.getDate(),
                cardClickAction = {
                    actorCardClickAction(navController, castList.getLinkToToDetails())
                }
            )

        }
    }
}


@Composable
fun ActorDetailsComponent(
    navController: NavHostController,
    actor: Actor
) {

    val backdropPath: String = actor.getPosterPath()
    val posterPath: String = actor.getPosterPath()
    val title: String = actor.getTitleName()
    val birthDate: String = actor.getBirthDate() ?: "N/A"
    val biography: String = actor.biography
    val moviesList = actor.credits.cast.take(10)
    val seriesList = actor.credits.crew.take(10)


    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val backdropPainter =
        rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w780$backdropPath.jpg")
    val posterPainter =
        rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w780$posterPath.jpg")

    val isPortrait = configuration.screenWidthDp < configuration.screenHeightDp
    val columns = if (isPortrait) 2 else 4

    val widthImages = if (isPortrait) screenWidth / 3 else screenWidth / 6

    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = Modifier.padding(16.dp)
    ) {

        item(span = { GridItemSpan(columns) }) {
            BackdropAndPoster(backdropPainter, posterPainter, widthImages)
        }

        item(span = { GridItemSpan(columns) }) {

            TextForDetails(
                title, "",
                customTextBuilder("Date de naissance: ", birthDate),
                customTextBuilder("Biographie: ", biography),
            )


        }

        item(span = { GridItemSpan(columns) }) {
            Text(
                text = "Films",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }


        items(moviesList) { movie ->

            CardComponent(
                posterPath = movie.getPosterPath(),
                title = movie.getTitleName() ?: "",
                subTitle = movie.getDate() ?: "",
                cardClickAction = {
                    moviesCardClickAction(navController, movie.getLinkToToDetails())
                }
            )
        }

        /*
        item(span = { GridItemSpan(columns) }) {
            Text(
                text = "Series",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Log.i("ActorDetailsComponent", seriesList.toString())
        items(seriesList) { serie ->
            CardComponent(
                posterPath = serie.getPosterPath(),
                title = serie.getTitleName() ?: "",
                subTitle = serie.getDate() ?: "",
                cardClickAction = {

                }
            )
        }

         */
    }

}


fun customTextBuilder(firstText: String, secondText: String): AnnotatedString {
    var secondWritenText = secondText
    if (secondText.isEmpty()) {
        secondWritenText = "N/A"
    }
    return buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(firstText)
        }
        append(secondWritenText)
    }
}

@Composable
fun BackdropAndPoster(
    backdropPainter: Painter,
    posterPainter: Painter,
    widthImages: Dp
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(widthImages * 2.1f)
    ) {
        Image(
            painter = backdropPainter,
            contentDescription = "Backdrop",
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer { alpha = 0.99f }
                .drawWithContent {
                    val colors = listOf(
                        Color.Black, Color.Transparent
                    )
                    drawContent()
                    drawRect(
                        brush = Brush.verticalGradient(colors), blendMode = BlendMode.DstIn
                    )
                },
            contentScale = ContentScale.Crop
        )
        Image(
            painter = posterPainter,
            contentDescription = "Poster",
            modifier = Modifier
                .width(widthImages)
                .aspectRatio(1 / 1.66f)
                .border(2.dp, Color.White)
                .align(Alignment.BottomCenter),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun TextForDetails(
    title: String,
    subTitle: String,
    genres: AnnotatedString,
    desciption: AnnotatedString,

    ) {
    Column {
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

        if (genres.isNotEmpty()) {
            Text(
                text = genres,
                fontSize = 18.sp,
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = desciption,
            fontSize = 19.sp,
            textAlign = TextAlign.Justify

        )

        Spacer(modifier = Modifier.height(18.dp))

    }
}

