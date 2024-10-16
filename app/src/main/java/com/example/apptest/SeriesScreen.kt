package com.example.apptest

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.window.core.layout.WindowSizeClass



@Composable
fun SeriesScreen(windowClass: WindowSizeClass, mainViewModel: MainViewModel) {
    LaunchedEffect(Unit) {
        mainViewModel.getSeries()

    }

    val series by mainViewModel.series.collectAsStateWithLifecycle()

    GridComponent(canBeCardedList = series)

}