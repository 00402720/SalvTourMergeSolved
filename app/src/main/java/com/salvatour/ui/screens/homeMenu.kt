package com.salvatour.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import com.salvatour.data.model.PostModel
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.salvatour.MainViewModel
import androidx.navigation.compose.rememberNavController
import com.salvatour.R
import com.salvatour.ui.component.TourCard
import com.salvatour.util.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home_View(
    viewModel: MainViewModel,
    navController: NavController){

    val allTours by viewModel.allTours.collectAsState()
    var isMenuExpanded by remember { mutableStateOf(false) }
    var isFilterExpanded by remember { mutableStateOf(false) }
    var isSearchBarExpanded by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    var minPrice by remember { mutableStateOf("") }
    var maxPrice by remember { mutableStateOf("") }

    var postModel by remember { mutableStateOf(PostModel("16447","Aventura Maya",
        "",
        "","4 horas", "$21.00", "", "", ""))}

    Scaffold(
        topBar = {

            TopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = colorResource(id = R.color.secondary_background)
                ),

                title = {
                },

                actions = {
                    IconButton(
                        onClick = { isFilterExpanded = !isFilterExpanded }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_filtrer),
                            contentDescription = "Filter Prices",
                            tint = colorResource(id = R.color.secondary_action_button),
                            modifier = Modifier.size(20.dp)
                        ) 
                    }
                    DropdownMenu(
                        expanded = isFilterExpanded,
                        onDismissRequest = { isFilterExpanded = false }
                    ) {
                        Column(
                            modifier = Modifier.padding(horizontal = 16.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                OutlinedTextField(
                                    value = minPrice,
                                    onValueChange = { minPrice = it },
                                    singleLine = true,
                                    label = { Text("Mínimo", fontSize = 12.sp) },
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Decimal,
                                        imeAction = ImeAction.Done
                                    ),
                                    modifier = Modifier
                                        .width(150.dp),
                                    colors = TextFieldDefaults.colors(
                                        unfocusedContainerColor = Color.Transparent,
                                        unfocusedLabelColor = colorResource(id = R.color.secondary_text_color_faded),
                                        unfocusedIndicatorColor = colorResource(id = R.color.secondary_text_color_faded),
                                        unfocusedTextColor = colorResource(id = R.color.secondary_text_color_faded),
                                        focusedContainerColor = Color.Transparent,
                                        focusedLabelColor = colorResource(id = R.color.secondary_text_color_faded),
                                        focusedIndicatorColor = colorResource(id = R.color.secondary_text_color_faded),
                                        focusedTextColor = colorResource(id = R.color.secondary_text_color_faded),
                                    )
                                )
                                OutlinedTextField(
                                    value = maxPrice,
                                    onValueChange = { maxPrice = it },
                                    singleLine = true,
                                    label = { Text("Máximo", fontSize = 12.sp) },
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Decimal,
                                        imeAction = ImeAction.Done
                                    ),
                                    modifier = Modifier
                                        .width(150.dp),
                                    colors = TextFieldDefaults.colors(
                                        unfocusedContainerColor = Color.Transparent,
                                        unfocusedLabelColor = colorResource(id = R.color.secondary_text_color_faded),
                                        unfocusedIndicatorColor = colorResource(id = R.color.secondary_text_color_faded),
                                        unfocusedTextColor = colorResource(id = R.color.secondary_text_color_faded),
                                        focusedContainerColor = Color.Transparent,
                                        focusedLabelColor = colorResource(id = R.color.secondary_text_color_faded),
                                        focusedIndicatorColor = colorResource(id = R.color.secondary_text_color_faded),
                                        focusedTextColor = colorResource(id = R.color.secondary_text_color_faded),
                                    )
                                )
                            }
                            TextButton(
                                onClick = {
                                    // Acción para aplicar el filtro
                                    isFilterExpanded = false
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(color = Color.Transparent)
                            ) {
                                Text("Aplicar", color = colorResource(id = R.color.tertiary_text_color))
                            }
                        }
                    }

                    IconButton(
                        onClick = { isMenuExpanded = !isMenuExpanded }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Hamburger Menu",
                            tint = colorResource(id = R.color.secondary_action_button)
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
                        Divider(color = Color.White, thickness = 1.dp)
                        DropdownMenuItem(
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = null
                                )
                            },
                            text = { Text("Reservas") },
                            onClick = {
                                navController.navigate("MyTours")
                                isMenuExpanded = false
                            }
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.CreatePost.route) },
                shape = RoundedCornerShape(100.dp),
                containerColor = colorResource(id = R.color.secondary_background)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "add",
                    tint = Color.White,
                    modifier = Modifier.size(46.dp)
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .background(colorResource(id = R.color.primary_background)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemsIndexed(allTours) { index, item ->
                TourCard(onClick = {}, item, navController = navController)
            }
        }
    }
}



