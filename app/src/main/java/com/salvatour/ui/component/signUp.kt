package com.salvatour.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.material3.NavigationBarDefaults.containerColor
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.salvatour.MainViewModel
import com.salvatour.UiState
import com.salvatour.data.model.RegisterData
import com.salvatour.util.navigation.Screen
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.salvatour.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(
    viewModel: MainViewModel,
    navController: NavController) {

    var regis by remember { mutableStateOf(RegisterData("", "","","","","","")) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var hidden by remember { mutableStateOf(true) }

    val context = LocalContext.current

    val uiState = viewModel.RegisteryState.collectAsState()

    /*
    if (uiState.value is UiState<Any?>.Error) {
        when ((uiState.value as UiState<Any?>.Error).code) {
            400 -> {
                showMessage(context, "Error: usuario o contraseña incorrecta")
            }
        }
    }

    if (uiState.value is UiState<Any?>.Success_r) {
        showMessage(context, "Token: ${(uiState.value as UiState<Any?>.Success_r).token}")
        navController.navigate(route = Screen.LogIn.route)
        viewModel.setStateToReady()
    }

    */
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
                /*
                val containerColor = Color(0x948FC7BD)
                 */
                OutlinedTextField(
                    value = regis.email,
                    onValueChange = { regis = regis.copy(email = it) },
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
                    value = regis.username,
                    onValueChange = { regis = regis.copy(username = it) },
                    label = { Text("UserName", fontSize = 12.sp)},
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
                    value = regis.hashedPassword,
                    onValueChange = { regis = regis.copy(hashedPassword = it) },
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
                OutlinedTextField(
                    value = regis.name,
                    onValueChange = { regis = regis.copy(name = it) },
                    label = { Text("name", fontSize = 12.sp)},
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
                    value = regis.lastname,
                    onValueChange = { regis = regis.copy(lastname = it) },
                    label = { Text("last name", fontSize = 12.sp)},
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
                    value = regis.telephoneNumber,
                    onValueChange = { regis = regis.copy(telephoneNumber = it) },
                    label = { Text("Numero de Telefono", fontSize = 12.sp) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
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

                Column{
                    // Register Button
                    Button(
                        onClick = { viewModel.register(regis) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.secondary_action_button)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text("Registrarse", color = colorResource(id = R.color.primary_text_color))
                    }
                    Spacer(modifier = Modifier.size(6.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Ya tienes una cuenta? ",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = colorResource(id = R.color.secondary_text_color)
                        )
                        ClickableText(
                            text = AnnotatedString("Iniciar Sesión"),
                            onClick = { navController.navigate("LoginView") },
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


