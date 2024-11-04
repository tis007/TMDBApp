package com.example.apptest

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowSizeClass


@Composable
fun SeriesScreen(
    windowClass: WindowSizeClass,
    mainViewModel: MainViewModel,
    navController: NavHostController,
    searchQuery: String,
) {
    LaunchedEffect(Unit) {
        if (searchQuery.isNotEmpty()) {
            mainViewModel.searchSerie(searchQuery)
        } else {
            mainViewModel.getSeries()
        }
    }

    val series by mainViewModel.series.collectAsStateWithLifecycle()

    if (series.isNotEmpty()) {
        GridComponent(canBeCardedList = series, ::cardClickAction, navController)
    } else if (searchQuery.isNotEmpty() && series.isEmpty()) {
        // Affichez un indicateur de chargement ou un message d'erreur
        Text("Aucun résultat trouvé")
    }
}


@Composable
fun SerieDetailsScreen(
    windowClass: WindowSizeClass,
    mainViewModel: MainViewModel,
    navController: NavHostController,
    detailsId: String
) {
    LaunchedEffect(Unit) {
        mainViewModel.getSerieDetails(detailsId)
    }


    val serie by mainViewModel.serie.collectAsStateWithLifecycle()

    if (serie.getLinkToToDetails() == detailsId) {
        DetailsComponent(navController, canBeDetailed = serie)
    } else {
        // Affichez un indicateur de chargement ou un message d'erreur
        Text("Loading...")
    }
}

fun cardClickActionSeries(navController: NavHostController, detailsId: String) {
    navController.navigate(SerieDetailsDestionation(detailsId))

}