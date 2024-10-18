package com.example.apptest

import android.util.Log
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

    GridComponent(canBeCardedList = movies)

}

@Composable
fun MovieDetailsScreen(
    windowClass: WindowSizeClass,
    mainViewModel: MainViewModel,
    navController: NavHostController,

) {

}

fun cardClickAction(navController: NavHostController) {
    navController.navigate("movieDetails")

}