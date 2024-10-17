import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowSizeClass
import com.example.apptest.GridComponent
import com.example.apptest.MainViewModel

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

    GridComponent(canBeCardedList = actors)
}

@Composable
fun ActorDetailsScreen(
    windowClass: WindowSizeClass,
    mainViewModel: MainViewModel,
    navController: NavHostController
) {

}