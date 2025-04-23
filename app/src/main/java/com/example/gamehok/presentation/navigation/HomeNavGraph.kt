
package com.example.gamehok.presentation.navigation
import android.net.Uri
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.gamehok.presentation.ui.screens.home.HomeScreen
import com.example.gamehok.presentation.ui.screens.home.HomeScreenEvents
import com.example.gamehok.presentation.ui.screens.home.HomeViewModel


fun NavGraphBuilder.homeNavGraph(navController: NavController){
    navigation(
        route = NavGraph.HOME,
        startDestination = HomeScreens.DashBoardScreen.route
    ){
        composable(route = HomeScreens.DashBoardScreen.route) {
            val  viewmodel : HomeViewModel = hiltViewModel()
            val tournamentList by viewmodel.tournamentList.collectAsState()
            val gameList by viewmodel.gameList.collectAsState()
            HomeScreen(gameList =gameList ,tournamentList = tournamentList){
                when(it){
                    is HomeScreenEvents.OnNavigate -> {

                    }
                }
            }
        }
//        composable(route = HomeScreens.DetailsScreens.route+"/{id}",
//            arguments = listOf(navArgument("id") { type = NavType.StringType }))
//        {backStackEntry->
//            val id = backStackEntry.arguments?.getString("id")?.let { Uri.decode(it) }
//
//            if (id != null) {
//                //details screen
//            }
//        }

    }
}

sealed class HomeScreens(val route : String){
    data object DashBoardScreen : HomeScreens(route = "home_list_screen")
    data object DetailsScreens : HomeScreens(route = "web_view_screen")
}

//viewmdoel should be in screens