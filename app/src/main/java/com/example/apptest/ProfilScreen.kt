package com.example.apptest

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

@Composable
fun ProfilScreen(windowClass: WindowSizeClass, navController: NavController) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    when (windowClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TopInfos(0.50f)
                BottomInfos(navController)

            }
        }

        else -> {
            val padding = screenWidth * 0.12f

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically,

                ) {
                TopInfos(0.25f)
                Spacer(modifier = Modifier.padding(padding))
                BottomInfos(navController)
            }
        }
    }
}

@Composable
fun TopInfos(MaxWidth: Float) {
    Box(modifier = Modifier.padding(10.dp)) {
        Column(
            modifier = Modifier
                .wrapContentSize(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ShowImage(MaxWidth)
            ShowName("Matis MP")
            ShowSubText("Dieux vivant, maître des mondes")
            Spacer(modifier = Modifier.padding(32.dp))
        }
    }
}

@Composable
fun BottomInfos(navController: NavController) {
    Column(
        modifier = Modifier
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column {
            ShowLinkAndImage(
                "moi@orange.fr",
                painterResource(id = R.drawable.baseline_email_24)
            )
            ShowLinkAndImage(
                "https://linkedin.com/in/matis",
                painterResource(id = R.drawable.linkedin)
            )
        }

        Spacer(modifier = Modifier.padding(64.dp))
        DemarrerButton(navController)
    }

}