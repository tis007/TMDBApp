import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowSizeClass
import com.example.apptest.GridComponent
import com.example.apptest.MainViewModel
import com.example.apptest.MovieDetailsDestination
import com.example.apptest.cardClickAction

@Composable
fun ActorsScreen(
    windowClass: WindowSizeClass,
    mainViewModel: MainViewModel,
    navController: NavHostController,
    searchQuery: String,

    ) {
    LaunchedEffect(Unit) {
        if (searchQuery.isNotEmpty()) {
            mainViewModel.searchActor(searchQuery)
        } else {
            mainViewModel.getActors()
        }
    }

    val actors by mainViewModel.actors.collectAsStateWithLifecycle()

    if (actors.isNotEmpty()) {
        GridComponent(canBeCardedList = actors, ::cardClickAction, navController)
    } else if (searchQuery.isNotEmpty() && actors.isEmpty()) {
        // Affichez un indicateur de chargement ou un message d'erreur
        Text("Aucun résultat trouvé")
    }
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