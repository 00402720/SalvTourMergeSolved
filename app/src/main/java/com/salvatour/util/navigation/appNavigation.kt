package com.salvatour.util.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.salvatour.MainViewModel
import com.salvatour.ui.screens.CreatePost
import com.salvatour.ui.screens.EditProfileView
import com.salvatour.ui.component.LogIn
import com.salvatour.ui.component.SignUp
import com.salvatour.ui.component.TourCard
import com.salvatour.ui.screens.HomeView
import com.salvatour.ui.screens.Home_View
import com.salvatour.ui.screens.LoginView
import com.salvatour.ui.screens.MyTours
import com.salvatour.ui.screens.ProfileView
import com.salvatour.ui.screens.SignUpView
import com.salvatour.ui.screens.TourDisplayView
import com.salvatour.ui.screens.UserPostScreen


@Composable
fun AppNavigation(
    viewModel: MainViewModel
){
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "HomeView") {

        composable(route = "HomeView"){
            HomeView(navController)
        }
        composable(Screen.SignUp.route){
            SignUpView(viewModel, navController)
            //SignUp(navController)
        }
        composable(Screen.LogIn.route){
            LoginView(viewModel, navController)
            //LogIn(navController)
        }
        composable(Screen.CreatePost.route){
            CreatePost(viewModel, onPublishClick = { /*TODO*/ }, navController, token = viewModel.tokenState.collectAsState().value)
        }
        composable(Screen.ProfileView.route){
            ProfileView(viewModel, navController)
        }
        composable(Screen.EditProfile.route){
            EditProfileView(viewModel, navController, token = "")
        }
        composable(route = Screen.HomeMenu.route){
            Home_View(viewModel, navController)
            //TourCard()
        }
        composable(route = Screen.TourDisplay.route){
            TourDisplayView(viewModel, navController, viewModel.selectedPostState.collectAsState().value ,token = viewModel.tokenState.collectAsState().value)
        }
        composable(route = Screen.CreatePost.route){
            CreatePost(viewModel, onPublishClick = { /*TODO*/ }, navController, token = viewModel.tokenState.collectAsState().value)

        }
        composable(route = "MyTours"){
            MyTours(navController)
        }
    }
}

/*UserPostScreen*/