package com.salvatour.util.navigation

sealed class Screen(val route : String) {
    object HomeView : Screen(route = "HomeView")
    object TourDisplay : Screen("TourDisplay/{tourId}?token={token}") {
        fun createRoute(tourId: String, token: String): String = "TourDisplay/$tourId?token=$token"
    }
    object SignUp : Screen(route = "SignUpView")
    object LogIn : Screen(route = "LogInView")
    object HomeMenu : Screen(route = "HomeMenu")
    object TourCard : Screen(route = "TourCard")
    object CreatePost : Screen(route = "CreatePost")
    object EditProfile : Screen(route = "EditProfile")
    object ProfileView : Screen(route = "ProfileView")
    object UserPosts : Screen(route = "UserPosts")
}