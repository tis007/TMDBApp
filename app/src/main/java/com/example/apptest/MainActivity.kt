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
class FilmsDestination

@Serializable
class SeriesDestination

@Serializable
class ActeursDestination


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
                                selected = currentDestination.route == ProfilDestination().toString(),
                                onClick = { navController.navigate(FilmsDestination()) })
                            NavigationBarItem(
                                icon = { Icon(painterResource(id = R.drawable.baseline_tv_24), contentDescription = null)},
                                label = { Text("Séries") },
                                selected = currentDestination.route == FilmsDestination().toString(),
                                onClick = { navController.navigate(SeriesDestination()) })
                            NavigationBarItem(
                                icon = {  Icon(painterResource(id = R.drawable.baseline_person_24), contentDescription = null) },
                                label = { Text("Acteurs") },
                                selected = currentDestination.route == FilmsDestination().toString(),
                                onClick = { navController.navigate(ActeursDestination()) })
                        }
                    }
                }
            ) { innerPadding ->
                NavHost(
                    navController = navController, startDestination = ProfilDestination(),
                    Modifier.padding(innerPadding),
                ) {
                    composable<ProfilDestination> { ProfilScreen(windowSizeClass, navController) }
                    composable<FilmsDestination> { MoviesScreen(windowSizeClass, mainViewModel) }
                    composable<SeriesDestination> { SeriesScreen(windowSizeClass, mainViewModel) }
                    composable<ActeursDestination> { ActorsScreen(windowSizeClass, mainViewModel) }
                }
            }
        }
    }
}


