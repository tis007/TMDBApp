import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowSizeClass
import com.example.apptest.ActorDetailsComponent
import com.example.apptest.ActorDetailsDestination
import com.example.apptest.GridComponent
import com.example.apptest.MainViewModel

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
        GridComponent(canBeCardedList = actors, ::actorCardClickAction, navController)
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

    LaunchedEffect(Unit) {
        mainViewModel.getActorDetails(detailsId)
    }


    val actor by mainViewModel.actor.collectAsStateWithLifecycle()

    if (actor.getLinkToToDetails() == detailsId) {
        ActorDetailsComponent(navController, actor)
    } else {
        // Affichez un indicateur de chargement ou un message d'erreur
        Text("Loading...")
    }


}

fun actorCardClickAction(navController: NavHostController, detailsId: String) {
    navController.navigate(ActorDetailsDestination(detailsId))

}