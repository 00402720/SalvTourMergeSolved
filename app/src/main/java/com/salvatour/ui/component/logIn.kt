package com.salvatour.ui.component

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.salvatour.MainViewModel
import com.salvatour.UiState
import androidx.navigation.compose.rememberNavController
import com.salvatour.R
import com.salvatour.util.navigation.Screen

@Composable
fun LogIn(
    viewModel: MainViewModel,
    navController: NavController) {

    var login by remember { mutableStateOf(com.salvatour.data.model.LoginModel("","")) }

    val context = LocalContext.current

    val uiState = viewModel.loginState.collectAsState()

    var passwordVisible by remember { mutableStateOf(false) }

    //when
    /*
    when (uiState) {
        is UiState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is UiState.Success -> {
            Box(
                modifier = Modifier
                    .background(Color(0xFF138535))
            ){
                Text(
                    text = "Login Successful!",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                )
            }
            navController.navigate(AppScreens.AppController.route)
        }
        is UiState.Error -> {

            Box(
                modifier = Modifier.background(Color(0xFFD53036))
            ){
                Text(
                    text = "Login Failed",
                    color = Color.White,
                    textAlign = TextAlig
                            n.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                )
            }
        }
        else -> {}
    }*/



    /*if (uiState.value is UiState.Error) {
        when ((uiState.value as UiState.Error).code) {
            400 -> {
                showMessage(context, "Error: usuario o contraseña incorrecta")
            }
        }
    }

    if (uiState.value is UiState.) {
        showMessage(context, "Token: ${(uiState.value as UiState.Success).token}")
        navController.navigate(route = Screen.HomeMenu.route)
        viewModel.setStateToReady()
    }*/

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var hidden by remember { mutableStateOf(true) }
    Column{
        Box(
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
           
                val containerColor = Color(0x948FC7BD)
       


                OutlinedTextField(
                    value = login.identifier,
                    onValueChange = { login = login.copy(identifier = it) },
                    label = { Text("Email", fontSize = 12.sp) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            unfocusedLabelColor = colorResource(id = R.color.secondary_text_color),
                            unfocusedIndicatorColor = colorResource(id = R.color.secondary_text_color),
                            unfocusedTextColor = colorResource(id = R.color.secondary_text_color),

                            focusedContainerColor = Color.Transparent,
                            focusedLabelColor = colorResource(id = R.color.secondary_text_color),
                            focusedIndicatorColor = colorResource(id = R.color.secondary_text_color),
                            focusedTextColor = colorResource(id = R.color.secondary_text_color),
                        )

                )

                OutlinedTextField(
                    value = login.password,
                    onValueChange = { login = login.copy(password = it) },
                    label = { Text("Contraseña", fontSize = 12.sp) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),//2
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        unfocusedLabelColor = colorResource(id = R.color.secondary_text_color),
                        unfocusedIndicatorColor = colorResource(id = R.color.secondary_text_color),
                        unfocusedTextColor = colorResource(id = R.color.secondary_text_color),

                        focusedContainerColor = Color.Transparent,
                        focusedLabelColor = colorResource(id = R.color.secondary_text_color),
                        focusedIndicatorColor = colorResource(id = R.color.secondary_text_color),
                        focusedTextColor = colorResource(id = R.color.secondary_text_color),
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
                                tint = colorResource(id = R.color.secondary_text_color)
                            )
                        }
                    }
                )

                Column{
                    // Register Button
                    Button(
                        onClick = { viewModel.login(login.identifier,login.password) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.secondary_action_button)
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text("Iniciar Sesión", color = colorResource(id = R.color.primary_text_color))
                    }
                    Spacer(modifier = Modifier.size(6.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "No tienes una cuenta? ",
                            fontWeight = FontWeight.Normal,
                            color = colorResource(id = R.color.secondary_text_color),
                            fontSize = 12.sp
                        )
                        ClickableText(
                            text = AnnotatedString("Registrate",),
                            onClick = { navController.navigate("SignUpView") },
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 12.sp,
                                color = colorResource(id = R.color.tertiary_text_color))
                        )
                    }
                }

            }
        }
    }
}

fun showMessage(
    context: Context,
    msg: String
){
    Toast.makeText(context,
        msg,
        Toast.LENGTH_SHORT).show()
}

