package com.example.apptest

import ActorsScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import androidx.navigation.NavDestination.Companion.hasRoute


@Serializable
class ProfilDestination

@Serializable
class MoviesDestination
@Serializable
class MovieDetailsDestination

@Serializable
class SeriesDestination
@Serializable
class SerieDetailsDestionation

@Serializable
class ActorsDestination
@Serializable
class ActorDetailsDestination



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainViewModel : MainViewModel by viewModels()
            val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination


            Scaffold(


                bottomBar = {

                    if (currentDestination?.hasRoute<ProfilDestination>() == false) {
                        NavigationBar {
                            NavigationBarItem(
                                icon = { Icon(painterResource(id = R.drawable.baseline_movie_24), contentDescription = null) },
                                label = { Text("Films") },
                                selected = currentDestination.hasRoute<MoviesDestination>(),
                                onClick = { navController.navigate(MoviesDestination()) })
                            NavigationBarItem(
                                icon = { Icon(painterResource(id = R.drawable.baseline_tv_24), contentDescription = null)},
                                label = { Text("Séries") },
                                selected = currentDestination.hasRoute<SeriesDestination>(),
                                onClick = { navController.navigate(SeriesDestination()) })
                            NavigationBarItem(
                                icon = {  Icon(painterResource(id = R.drawable.baseline_person_24), contentDescription = null) },
                                label = { Text("Acteurs") },
                                selected = currentDestination.hasRoute<ActorsDestination>(),
                                onClick = { navController.navigate(ActorsDestination()) })
                        }
                    }
                }
            ) { innerPadding ->
                NavHost(
                    //navController = navController, startDestination = ProfilDestination(),
                    navController = navController, startDestination = SerieDetailsDestionation(),

                    Modifier.padding(innerPadding),
                ) {
                    composable<ProfilDestination> { ProfilScreen(windowSizeClass, navController) }

                    composable<MoviesDestination> { MoviesScreen(windowSizeClass, mainViewModel, navController) }
                    composable<MovieDetailsDestination> {  }

                    composable<SeriesDestination> { SeriesScreen(windowSizeClass, mainViewModel, navController) }
                    composable<SerieDetailsDestionation> { SeriesDetailsScreen(windowSizeClass, mainViewModel, navController) }

                    composable<ActorsDestination> { ActorsScreen(windowSizeClass, mainViewModel, navController) }
                    composable<ActorDetailsDestination> {  }
                }
            }
        }
    }
}


