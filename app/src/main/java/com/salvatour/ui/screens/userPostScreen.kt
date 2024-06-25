package com.salvatour.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.salvatour.MainViewModel
import com.salvatour.R
import com.salvatour.ui.component.TourCard
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserPostScreen(
    viewModel: MainViewModel,
    navController: NavController

) {

    val allTours by viewModel.allTours.collectAsState()

    val user by viewModel.authUser.collectAsState()

    val isAdmin by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = colorResource(id = R.color.secondary_background)
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("TourDisplayView")}) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back",
                            modifier = Modifier.size(24.dp),
                            tint = colorResource(id = R.color.primary_text_color)
                        )
                    }
                },

                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .background(
                                    colorResource(id = R.color.primary_background),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(horizontal = 48.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "Usuario",
                                color = colorResource(id = R.color.primary_text_color),
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
                //action aqui
            )
        },
        bottomBar = {
            if (isAdmin) {
                BottomAppBar(containerColor = Color(0xFF37474F)) {
                    Spacer(modifier = Modifier.weight(0.25f))
                    Button(
                        onClick = { /*TODO*/ },
                        shape = CircleShape,
                        colors = ButtonDefaults
                            .buttonColors(containerColor = colorResource(id = R.color.cancel_action_button))
                    ) {
                        Text(text = "Bloquear Usuario")
                    }
                    Spacer(modifier = Modifier.weight(0.25f))
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(colorResource(id = R.color.primary_background)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Image(
                imageVector = Icons.Filled.AccountCircle,
                /*painter = painterResource(id = R.drawable.img),*/ // Asegúrate de tener esta imagen en tus recursos
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .border(2.dp, colorResource(id = R.color.primary_text_color), CircleShape)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = user.username, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Nombre: ${user.name}", color = Color.White, fontSize = 13.sp)

            Text(text = "Apellido: ${user.lastname}", color = Color.White, fontSize = 13.sp)

            Text(text = "Email: ${user.email}", color = Color.White, fontSize = 13.sp)

            Text(text = "Numero celular: ${user.telephoneNumber}", color = Color.White, fontSize = 13.sp)


            LazyColumn(
                modifier = Modifier.padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Text(
                        text = "Publicaciones:",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }


                    itemsIndexed(allTours) {index, item->
                        TourCard(onClick = {}, item, navController )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
            }


        }
    }
}

