package com.example.apptest

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
    navController: NavHostController
) {
    LaunchedEffect(Unit) {
        mainViewModel.getSeries()

    }

    val series by mainViewModel.series.collectAsStateWithLifecycle()

    GridComponent(canBeCardedList = series)

}



val backdropPath = "/6MKr3KgOLmzOP6MSuZERO41Lpkt"
val posterPath = "/s70jNqYx4OdTTI2rjhI3eDI4HTr"
val title = "traintraintain"
val subTitle = "13 septembre 2023"
val genres  = listOf("Action", "Adventure", "Comedy")
val synopsis = "Divers personnages, connus et inconnus, doivent faire face à la résurgence du mal en Terre du Milieu. Des profondeurs des Montagnes de Brume aux forêts majestueuses du Lindon, de l'île des Rois de Númenor aux contrées les plus éloignées du monde, ces royaumes et personnages vont forger un héritage qui demeurera bien après leur disparition."
val castList = listOf(null)

@Composable
fun SerieDetailsScreen(
    windowClass: WindowSizeClass,
    mainViewModel: MainViewModel,
    navController: NavHostController
) {

    DetailsComponent(backdropPath, posterPath, title, subTitle, genres, synopsis)
}