package com.example.apptest

import ActorDetailsScreen
import ActorsScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.toRoute
import androidx.window.core.layout.WindowWidthSizeClass


@Serializable
class ProfilDestination

@Serializable
class MoviesDestination

@Serializable
data class MovieDetailsDestination(val detailsId: String)


@Serializable
class SeriesDestination

@Serializable
data class SerieDetailsDestionation(val detailsId: String)

@Serializable
class ActorsDestination

@Serializable
data class ActorDetailsDestination(val detailsId: String)

@Serializable
class HorrorDestination

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainViewModel: MainViewModel by viewModels()
            val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            var searchQuery = remember { mutableStateOf("") }

            var isSearchActive = remember { mutableStateOf(false) }





            Scaffold(

                floatingActionButton = {
                    if (windowSizeClass.windowWidthSizeClass != WindowWidthSizeClass.COMPACT && currentDestination?.hasRoute<ProfilDestination>() == false) {

                        FloatingSearchButton(searchQuery, isSearchActive)
                    }
                },

                topBar = {
                    if (currentDestination?.hasRoute<ProfilDestination>() == false && !currentDestination.hasRoute<MovieDetailsDestination>() && !currentDestination.hasRoute<SerieDetailsDestionation>() && !currentDestination.hasRoute<ActorDetailsDestination>() && windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT || isSearchActive.value) {

                        TopBarWithSearch(searchQuery, isSearchActive, navController)
                    }
                },

                bottomBar = {

                    if (currentDestination?.hasRoute<ProfilDestination>() == false && windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT) {
                        NavigationBar {
                            NavigationBarItem(icon = {
                                Icon(
                                    painterResource(id = R.drawable.baseline_movie_24),
                                    contentDescription = null
                                )
                            },
                                label = { Text("Films") },
                                selected = currentDestination.hasRoute<MoviesDestination>(),
                                onClick = {
                                    navController.navigate(MoviesDestination())
                                    searchQuery.value = ""
                                })
                            NavigationBarItem(icon = {
                                Icon(
                                    painterResource(id = R.drawable.baseline_tv_24),
                                    contentDescription = null
                                )
                            },
                                label = { Text("Séries") },
                                selected = currentDestination.hasRoute<SeriesDestination>(),
                                onClick = {
                                    navController.navigate(SeriesDestination())
                                    searchQuery.value = ""
                                })
                            NavigationBarItem(icon = {
                                Icon(
                                    painterResource(id = R.drawable.baseline_person_24),
                                    contentDescription = null
                                )
                            },
                                label = { Text("Acteurs") },
                                selected = currentDestination.hasRoute<ActorsDestination>(),
                                onClick = {
                                    navController.navigate(ActorsDestination())
                                    searchQuery.value = ""
                                })

                            NavigationBarItem(icon = {
                                Icon(
                                    painterResource(id = R.drawable.baseline_self_improvement_24),
                                    contentDescription = null
                                )
                            },
                                label = { Text("Horror") },
                                selected = currentDestination.hasRoute<HorrorDestination>(),
                                onClick = {
                                    navController.navigate(HorrorDestination())
                                    searchQuery.value = ""
                                })
                        }
                    }
                }

            ) { innerPadding ->
                Row {
                    when (windowSizeClass.windowWidthSizeClass) {
                        WindowWidthSizeClass.COMPACT -> {}
                        else -> {
                            if (currentDestination?.hasRoute<ProfilDestination>() == false) {
                                LeftNavigationRail(navController, currentDestination)
                            }
                        }
                    }

                    NavHost(
                        //navController = navController, startDestination = ProfilDestination(),
                        navController = navController, startDestination = ProfilDestination(),

                        Modifier.padding(innerPadding),
                    ) {
                        composable<ProfilDestination> {
                            ProfilScreen(
                                windowSizeClass,
                                navController
                            )
                        }

                        composable<MoviesDestination> {
                            MoviesScreen(
                                windowSizeClass, mainViewModel, navController, searchQuery.value
                            )
                        }
                        composable<MovieDetailsDestination> { backStackEntry ->
                            val filmDetail: MovieDetailsDestination = backStackEntry.toRoute()
                            MovieDetailsScreen(
                                windowSizeClass, mainViewModel, navController, filmDetail.detailsId
                            )

                        }

                        composable<SeriesDestination> {
                            SeriesScreen(
                                windowSizeClass, mainViewModel, navController, searchQuery.value
                            )
                        }
                        composable<SerieDetailsDestionation> { backStackEntry ->
                            val serieDetail: SerieDetailsDestionation = backStackEntry.toRoute()
                            SerieDetailsScreen(
                                windowSizeClass, mainViewModel, navController, serieDetail.detailsId
                            )
                        }

                        composable<ActorsDestination> {
                            ActorsScreen(
                                windowSizeClass, mainViewModel, navController, searchQuery.value
                            )
                        }
                        composable<ActorDetailsDestination> { backStackEntry ->
                            val actorDetail: ActorDetailsDestination = backStackEntry.toRoute()
                            ActorDetailsScreen(
                                windowSizeClass, mainViewModel, navController, actorDetail.detailsId
                            )
                        }
                        composable<HorrorDestination> {
                            HorrorScreen(
                                windowSizeClass, mainViewModel, navController
                            )
                        }
                    }
                }
            }
        }
    }
}


