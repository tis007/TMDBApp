import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.window.core.layout.WindowSizeClass
import com.example.apptest.GridComponent
import com.example.apptest.MainViewModel

@Composable
fun ActorsScreen(windowClass: WindowSizeClass, mainViewModel: MainViewModel) {
    LaunchedEffect(Unit) {
        mainViewModel.getActors()

    }

    val actors by mainViewModel.actors.collectAsStateWithLifecycle()

    GridComponent(canBeCardedList = actors)
}