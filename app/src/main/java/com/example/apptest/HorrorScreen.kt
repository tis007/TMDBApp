package com.example.apptest

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowSizeClass

@Composable
fun HorrorScreen(
    windowClass: WindowSizeClass,
    mainViewModel: MainViewModel,
    navController: NavHostController,
    ){


    Text("Horror Screen")
}