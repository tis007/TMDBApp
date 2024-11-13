package com.example.apptest

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowSizeClass
import coil.compose.rememberAsyncImagePainter

@Composable
fun HorrorScreen(
    windowClass: WindowSizeClass,
    mainViewModel: MainViewModel,
    navController: NavHostController,
) {
    LaunchedEffect(Unit) {
        mainViewModel.getHorrorMovies()
    }


    val horrorMovies by mainViewModel.horrorMovies.collectAsStateWithLifecycle()
    //Log.v("Movies", movies.joinToString { it.credits.cast.toString() })

    if (horrorMovies.isNotEmpty()) {
        val configuration = LocalConfiguration.current
        val isPortrait = configuration.screenWidthDp < configuration.screenHeightDp
        val columns = if (isPortrait) 2 else 4
        val screenWidth = configuration.screenWidthDp.dp

        val padding = screenWidth / 50

        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            modifier = Modifier.padding(16.dp)
        ) {
            items(horrorMovies) { horrorMovie ->

                Card(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxWidth()
                        .wrapContentHeight()

                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        val posterPath = horrorMovie.poster_path
                        val poster = rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w780$posterPath.jpg")

                        if (posterPath != null) {
                            Image(
                                painter = poster,
                                contentDescription = "poster",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp),
                                contentScale = ContentScale.Fit
                            )
                        }

                        Text(
                            text = horrorMovie.name,
                            fontSize = 20.sp,
                        )

                    }
                }
            }
        }
    } else {
        // Affichez un indicateur de chargement ou un message d'erreur
        Text("Aucun résultat trouvé")
    }
}
