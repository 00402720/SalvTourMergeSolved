package com.salvatour

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.salvatour.ui.screens.CreatePost
import com.salvatour.ui.screens.ProfileView
import com.salvatour.ui.screens.UserPostScreen
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.salvatour.ui.screens.HomeView
import com.salvatour.ui.screens.ProfileView
import com.salvatour.ui.screens.TourDisplayView
import com.salvatour.ui.theme.SalvaTourTheme
import com.salvatour.util.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SalvaTourTheme {
                AppNavigation(viewModel)
            }
        }
    }
}

