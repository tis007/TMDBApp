package com.example.apptest

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.window.core.layout.WindowSizeClass



@Composable
fun MoviesScreen(windowClass: WindowSizeClass, mainViewModel: MainViewModel) {
    LaunchedEffect(Unit) {
        mainViewModel.getMovies()

    }

    val movies by mainViewModel.movies.collectAsStateWithLifecycle()
    Log.v("Movies", movies.toString())

    GridComponent(canBeCardedList = movies)

}