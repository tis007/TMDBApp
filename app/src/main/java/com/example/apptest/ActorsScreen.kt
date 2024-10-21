import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowSizeClass
import com.example.apptest.GridComponent
import com.example.apptest.MainViewModel
import com.example.apptest.MovieDetailsDestination

@Composable
fun ActorsScreen(
    windowClass: WindowSizeClass,
    mainViewModel: MainViewModel,
    navController: NavHostController

) {
    LaunchedEffect(Unit) {
        mainViewModel.getActors()

    }

    val actors by mainViewModel.actors.collectAsStateWithLifecycle()

    GridComponent(canBeCardedList = actors, ::cardClickAction, navController)
}

@Composable
fun ActorDetailsScreen(
    windowClass: WindowSizeClass,
    mainViewModel: MainViewModel,
    navController: NavHostController,
    detailsId: String,


    ) {


}

fun cardClickAction(navController: NavHostController, detailsId: String) {
    //navController.navigate(MovieDetailsDestination(detailsId))

}