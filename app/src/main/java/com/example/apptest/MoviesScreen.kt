package com.example.apptest

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowSizeClass



@Composable
fun MoviesScreen(
    windowClass: WindowSizeClass,
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    LaunchedEffect(Unit) {
        mainViewModel.getMovies()

    }

    val movies by mainViewModel.movies.collectAsStateWithLifecycle()
    //Log.v("Movies", movies.joinToString { it.credits.cast.toString() })

    GridComponent(canBeCardedList = movies, ::cardClickAction, navController)

}

@Composable
fun MovieDetailsScreen(
    windowClass: WindowSizeClass,
    mainViewModel: MainViewModel,
    navController: NavHostController,
    detailsId: String,
) {
    LaunchedEffect(Unit) {
        mainViewModel.getMovieDetails(detailsId)
    }


    val movie by mainViewModel.movie.collectAsStateWithLifecycle()

    if (movie.getLinkToToDetails() == detailsId) {
        DetailsComponent(navController, canBeDetailed =  movie)
    } else {
        // Affichez un indicateur de chargement ou un message d'erreur
        Text("Loading...")
    }
}

fun cardClickAction(navController: NavHostController, detailsId: String) {
    navController.navigate(MovieDetailsDestination(detailsId))

}