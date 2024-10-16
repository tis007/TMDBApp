package com.example.apptest

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController


@Composable
fun ShowImage(MaxWidth: Float) {
    Image(
        painter = painterResource(id = R.drawable.jebave),
        contentDescription = "jebave",
        modifier = Modifier
            .fillMaxWidth(MaxWidth)
            .aspectRatio(1f)
            .clip(CircleShape)
            .border(2.dp, Color.Black, CircleShape),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ShowName(name: String) {
    Text(
        text = name,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        modifier = Modifier.padding(top = 16.dp),
    )
}

@Composable
fun ShowSubText(subText: String) {
    Text(
        text = subText,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Italic,
        fontSize = 16.sp,
        modifier = Modifier.padding(top = 4.dp),
    )
}

@Composable
fun  ShowLinkAndImage(link: String, painter: Painter) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 4.dp)
    )
    {
        Image(
            painter = painter,
            contentDescription = link,
            modifier = Modifier
                .size(36.dp).padding(end = 8.dp),
            contentScale = ContentScale.Fit
        )
        Column {
            Text(
                text = link,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
            )
        }
    }
}


@Composable
fun DemarrerButton(navController: NavController) {
    val mainViewModel : MainViewModel = viewModel()

    Button(
        onClick = { navController.navigate(FilmsDestination())
                  mainViewModel.getMovies()
        },
        modifier = Modifier.padding(top = 8.dp)
    ) {
        Text(
            text = "Demarrer",
            fontSize = 16.sp
        )
    }
}
