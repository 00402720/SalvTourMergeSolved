package com.salvatour.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.salvatour.MainViewModel
import com.salvatour.R
import com.salvatour.UiState
import com.salvatour.data.model.UserModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileView(
    viewModel: MainViewModel,
    navController: NavController,
    token: String
){

    var configUser by remember { mutableStateOf(UserModel("","","","","","","","","","","")) }

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var newpassword by remember { mutableStateOf("") }


    var hidden by remember { mutableStateOf(true) }
    var profileImageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri -> profileImageUri = uri }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = colorResource(id = R.color.secondary_background)
                ),
                navigationIcon = {
                   IconButton(onClick = { navController.navigate("ProfileView") }) {
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
                                .background(colorResource(id = R.color.primary_background), shape = RoundedCornerShape(8.dp))
                                .padding(horizontal = 48.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "Editar Perfil",
                                color = colorResource(id = R.color.primary_text_color),
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
                //action aqui
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(colorResource(id = R.color.primary_background)),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .size(130.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.White, CircleShape)
                    .background(colorResource(id = R.color.tertiary_background), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                if (profileImageUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(profileImageUri),
                        contentDescription = "Profile Picture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Agregar Foto",
                        modifier = Modifier.size(40.dp),
                        tint = colorResource(id = R.color.primary_text_color)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { imagePickerLauncher.launch("image/*") },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.primary_action_button))
            ) {
                Text(text = "Agregar Foto", color = colorResource(id = R.color.primary_text_color))
            }

            Spacer(modifier = Modifier.height(16.dp))

            //Column para ingresar infromacion
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    OutlinedTextField(
                        value = configUser.name,
                        onValueChange = { configUser = configUser.copy(name = it) },
                        label = { Text("name", fontSize = 9.sp) },
                        textStyle = LocalTextStyle.current.copy(fontSize = 9.sp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),//2
                        singleLine = true
                    )
                }
                item {
                    OutlinedTextField(
                        value = configUser.lastname,
                        onValueChange = { configUser = configUser.copy(lastname = it) },
                        label = { Text("lastname", fontSize = 9.sp) },
                        textStyle = LocalTextStyle.current.copy(fontSize = 9.sp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),//2
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            unfocusedLabelColor = colorResource(id = R.color.primary_text_color),
                            unfocusedIndicatorColor = colorResource(id = R.color.special_background),
                            unfocusedTextColor = colorResource(id = R.color.primary_text_color),
                            focusedContainerColor = Color.Transparent,
                            focusedLabelColor = colorResource(id = R.color.primary_text_color),
                            focusedIndicatorColor = colorResource(id = R.color.special_background),
                            focusedTextColor = colorResource(id = R.color.primary_text_color),
                        )
                    )
                }
                item {
                    Text(
                        text = "Email: ${configUser.email}",
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 8.dp),
                        fontWeight = FontWeight.Bold,
                    )
                }

                item {
                    OutlinedTextField(
                        value = configUser.hashedPassword,
                        onValueChange = { configUser = configUser.copy(hashedPassword = it) },
                        label = { Text("Contraseña", fontSize = 9.sp) },
                        textStyle = LocalTextStyle.current.copy(fontSize = 9.sp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),//2
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            unfocusedLabelColor = colorResource(id = R.color.primary_text_color),
                            unfocusedIndicatorColor = colorResource(id = R.color.special_background),
                            unfocusedTextColor = colorResource(id = R.color.primary_text_color),
                            focusedContainerColor = Color.Transparent,
                            focusedLabelColor = colorResource(id = R.color.primary_text_color),
                            focusedIndicatorColor = colorResource(id = R.color.special_background),
                            focusedTextColor = colorResource(id = R.color.primary_text_color),
                        ),
                        visualTransformation =
                        if (hidden) PasswordVisualTransformation() else VisualTransformation.None,//3
                        trailingIcon = {
                            IconButton(onClick = { hidden = !hidden }) {
                                val vector = painterResource(//5
                                    if (hidden) R.drawable.ic_visibility
                                    else R.drawable.ic_visibility_off
                                )
                                val description = if (hidden) "Ocultar contraseña" else "Revelar contraseña" //6
                                Icon(
                                    painter = vector,
                                    contentDescription = description,
                                    modifier = Modifier.size(20.dp),
                                    tint = colorResource(id = R.color.primary_text_color)
                                )
                            }
                        }
                    )
                }
                item {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {  },
                        label = { Text("Nueva Contraseña", fontSize = 9.sp) },
                        textStyle = LocalTextStyle.current.copy(fontSize = 9.sp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),//2
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            unfocusedLabelColor = colorResource(id = R.color.primary_text_color),
                            unfocusedIndicatorColor = colorResource(id = R.color.special_background),
                            unfocusedTextColor = colorResource(id = R.color.primary_text_color),
                            focusedContainerColor = Color.Transparent,
                            focusedLabelColor = colorResource(id = R.color.primary_text_color),
                            focusedIndicatorColor = colorResource(id = R.color.special_background),
                            focusedTextColor = colorResource(id = R.color.primary_text_color),
                        ),
                        visualTransformation =
                        if (hidden) PasswordVisualTransformation() else VisualTransformation.None,//3
                        trailingIcon = {// 4
                            IconButton(onClick = { hidden = !hidden }) {
                                val vector = painterResource(//5
                                    if (hidden) R.drawable.ic_visibility
                                    else R.drawable.ic_visibility_off
                                )
                                val description = if (hidden) "Ocultar contraseña" else "Revelar contraseña" //6
                                Icon(
                                    painter = vector,
                                    contentDescription = description,
                                    modifier = Modifier.size(20.dp),
                                    tint = colorResource(id = R.color.primary_text_color)
                                )
                            }
                        }
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
                item {
                    Button(
                        onClick = { viewModel.updateUser(token, configUser) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(91, 143, 146))
                    ) {
                        Text(text = "Guardar", color = Color.White)
                    }
                }
            }
        }
    }
}
