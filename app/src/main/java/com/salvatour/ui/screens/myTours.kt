package com.salvatour.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.salvatour.ui.component.MyTourCard
import com.salvatour.util.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTours(navController: NavController){
    var isMenuExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color(0xFF00BFA6)
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("Home_Menu") }) {
                        Icon(imageVector = Icons.Filled.Home, contentDescription = "Back",
                            modifier = Modifier.size(24.dp),
                            tint = Color.White)
                    }
                },
                title = {},
                actions = {
                    IconButton(
                        onClick = { isMenuExpanded = !isMenuExpanded }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Hamburger Menu",
                            tint = Color(55,58,52)
                        )
                    }
                    DropdownMenu(
                        expanded = isMenuExpanded,
                        onDismissRequest = { isMenuExpanded = false }
                    ) {
                        DropdownMenuItem(
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = null
                                )
                            },
                            text = { Text("Perfil") },
                            onClick = {
                                // Acción para "Perfil"
                                navController.navigate(Screen.ProfileView.route)
                                isMenuExpanded = false
                            }
                        )
                        Divider(color = Color.White, thickness = 1.dp)
                        DropdownMenuItem(
                            text = { Text("Cerrar sesión") },
                            onClick = {
                                // Acción para "Cerrar sesión"
                                isMenuExpanded = false
                            }
                        )
                        DropdownMenuItem(
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = null
                                )
                            },
                            text = { Text("Reservas") },
                            onClick = {
                                navController.navigate(Screen.ProfileView.route)
                                isMenuExpanded = false
                            }
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(10){
                MyTourCard(onClick = {}, navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyToursPreview()
{
    MyTours(navController = rememberNavController())
}