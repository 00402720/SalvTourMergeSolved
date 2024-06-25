package com.salvatour.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.salvatour.MainViewModel
import com.salvatour.ui.component.TriangleButton
import com.salvatour.R
import com.salvatour.data.model.PostModel
import com.salvatour.data.model.UserModel
import com.salvatour.ui.component.CommentDisplay
import com.salvatour.ui.component.ReservaAlerta
import com.salvatour.ui.component.CreateComment
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TourDisplayView(
    viewModel: MainViewModel,
    navController: NavController,
    TourData: PostModel,
    token: String
) {


    val user by viewModel.authUser.collectAsState()

    val deleteTour by remember { mutableStateOf(false) }

    val isAdmin by remember { mutableStateOf(false) }
    var mostrarAlert by remember { mutableStateOf(false) }
    var reservado by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { /*TODO*/ },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = colorResource(id = R.color.secondary_background)
                )
            )
        },
        bottomBar = {
            BottomAppBar(containerColor = Color(0xFF2E2E2E)) {
                if (user.roles == "admin") {
                    Spacer(modifier = Modifier.weight(0.25f))
                    //Mandar a llamar la
                    Button(
                        onClick = { viewModel.deleteTour(TourData.id) },
                        shape = CircleShape,
                        colors = ButtonDefaults
                            .buttonColors(containerColor = colorResource(id = R.color.cancel_action_button))
                    ) {
                        Text(text = "Eliminar Publicacion")
                    }
                    Spacer(modifier = Modifier.weight(0.25f))
                }
                Spacer(modifier = Modifier.weight(0.25f))
                Button(
                    onClick = { navController.navigate("Home_Menu") },
                    shape = CircleShape,
                    colors = ButtonDefaults
                        .buttonColors(containerColor = colorResource(id = R.color.primary_action_button))
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                        contentDescription = "Regresar"
                    )
                }
                Spacer(modifier = Modifier.weight(0.25f))
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier

                .background(colorResource(id = R.color.primary_background))
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = TourData.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.W600,
                    color = colorResource(id = R.color.primary_text_color)
                )
            }
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.rotate(180f)) {
                        TriangleButton()
                    }
                    Image(
                        painter = painterResource(id = R.drawable.img_1),
                        contentDescription = "Imagen del tour"
                    )
                    Box {
                        TriangleButton()
                    }
                }
            }
            item {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .padding(start = 30.dp, end = 30.dp)
                        .fillMaxSize()
                ) {
                    Text(text = "Precio: ${TourData.price}", color = Color.White)
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(text = "Duración: ${TourData.duration}", color = Color.White)
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(text = "Ubicación: ${TourData.mapLink}", color = Color.White)
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(text = "Descripción: ", color = Color.White)
                    Spacer(modifier = Modifier.size(10.dp))
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = TourData.description,
                            modifier = Modifier.padding(7.dp)
                        )
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column {
                            Text(text = "Publicado por: ", color = Color.White)
                            Row {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_person_24),
                                    contentDescription = "Icono de usuario", tint = Color.White
                                )
                                Text(text = user.username, color = Color.White)
                            }
                        }
                        Button(
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(4.dp),
                            colors = ButtonDefaults
                                .buttonColors(containerColor = Color(0xFF00BFA6))
                        ) {
                            Text(text = "Reservar")
                            Spacer(modifier = Modifier.size(8.dp))
                        }
                        Spacer(modifier = Modifier.size(16.dp))
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .padding(horizontal = 24.dp)
                                .fillMaxWidth()
                        ) {
                            Column {
                                Text(
                                    text = "Publicado por: ",
                                    color = colorResource(id = R.color.primary_text_color)
                                )
                                Row {
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_person_24),
                                        contentDescription = "Icono de usuario",
                                        tint = colorResource(id = R.color.primary_text_color)
                                    )
                                    Text(
                                        text = /*tourCreator*/"User Name",
                                        color = colorResource(id = R.color.primary_text_color)
                                    )
                                }
                            }
                            Button(
                                onClick = {
                                    mostrarAlert = true
                                    reservado = true
                                },
                                shape = RoundedCornerShape(4.dp),

                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF00BFA6),
                                    disabledContainerColor = Color(0xFF00BFA6),
                                    contentColor = Color.White,
                                    disabledContentColor = Color.Black
                                ),
                                enabled = !reservado

                            ) {
                                Text(text = if (reservado) "Reservado" else "Reservar")
                            }
                        }
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(
                            text = "Comentarios",
                            color = colorResource(id = R.color.primary_text_color),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W400
                        )
                    }
                    CreateComment()
                    CommentDisplay(navController)
                }
                if (mostrarAlert) {
                    ReservaAlerta(
                        whatsappLink = "wha (link)",
                        onDismiss = { mostrarAlert = false }
                    )
                }
            }
        }
    }
}

