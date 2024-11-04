package com.example.apptest

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithSearch(
    searchQuery: MutableState<String>,
    isSearchActive: MutableState<Boolean>,
    navController: NavController
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    TopAppBar(
        modifier = Modifier.padding(top = 8.dp),
        title = {
            if (isSearchActive.value) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = searchQuery.value,
                        onValueChange = { searchQuery.value = it },
                        placeholder = { Text("Rechercher...") },
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(onClick = {
                        isSearchActive.value = false
                        if (currentDestination?.hasRoute<MoviesDestination>() == true) {
                            navController.navigate(MoviesDestination())
                        } else if (currentDestination?.hasRoute<SeriesDestination>() == true) {
                            navController.navigate(SeriesDestination())
                        } else if (currentDestination?.hasRoute<ActorsDestination>() == true) {
                            navController.navigate(ActorsDestination())
                        }                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_forward_24),
                            contentDescription = "Search"
                        )
                    }
                }
            } else {
                Text("TMDB App")
            }
        },
        actions = {
            if (!isSearchActive.value) {
                IconButton(onClick = { isSearchActive.value = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_search_24),
                        contentDescription = "Search"
                    )
                }
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FloatingSearchButton(searchQuery: MutableState<String>, isSearchActive: MutableState<Boolean>) {

    if (isSearchActive.value) {
        /*
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextField(
                                value = searchQuery.value,
                                onValueChange = { searchQuery.value = it },
                                placeholder = { Text("Rechercher...") },
                                modifier = Modifier.weight(1f),
                                singleLine = true,
                                shape = MaterialTheme.shapes.small,
                            )
                            IconButton(onClick = { isSearchActive.value = false }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_arrow_forward_24),
                                    contentDescription = "Search"
                                )
                            }
                        }


         */

    } else {
        SmallFloatingActionButton(
            onClick = { isSearchActive.value = true },
            shape = CircleShape,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = null
            )
        }
    }
}

@Composable
fun LeftNavigationRail(navController: NavController, currentDestination: NavDestination?) {
    NavigationRail {
        if (currentDestination != null) {
            NavigationRailItem(
                icon = {
                    Icon(
                        painterResource(id = R.drawable.baseline_movie_24),
                        contentDescription = null
                    )
                },
                label = { Text("Films") },
                selected = currentDestination.hasRoute<MoviesDestination>(),
                onClick = { navController.navigate(MoviesDestination()) },
                modifier = Modifier.weight(1f)
            )
        }
        if (currentDestination != null) {
            NavigationRailItem(
                icon = {
                    Icon(
                        painterResource(id = R.drawable.baseline_tv_24),
                        contentDescription = null
                    )
                },
                label = { Text("Séries") },
                selected = currentDestination.hasRoute<SeriesDestination>(),
                onClick = { navController.navigate(SeriesDestination()) },
                modifier = Modifier.weight(1f)
            )
        }
        if (currentDestination != null) {
            NavigationRailItem(
                icon = {
                    Icon(
                        painterResource(id = R.drawable.baseline_person_24),
                        contentDescription = null
                    )
                },
                label = { Text("Acteurs") },
                selected = currentDestination.hasRoute<ActorsDestination>(),
                onClick = { navController.navigate(ActorsDestination()) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

