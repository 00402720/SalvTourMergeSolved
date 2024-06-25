package com.salvatour.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.salvatour.MainViewModel
import com.salvatour.ui.component.SignUp
import com.salvatour.R

@Composable
fun SignUpView(
    viewModel: MainViewModel,
    navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.primary_background)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .background(colorResource(id = R.color.secondary_background)),
            contentAlignment = Alignment.TopCenter
        ) {

            Column{
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TextButton(onClick = { navController.navigate("SignUpView") }) {
                        Text(
                            text = "Registrarse",
                            fontWeight = FontWeight.Medium,
                            color = colorResource(id = R.color.secondary_text_color),
                            textAlign = TextAlign.Center
                        )
                    }

                    TextButton(onClick = { navController.navigate("LoginView") }) {
                        Text(
                            text = "Iniciar Sesión",
                            fontWeight = FontWeight.Medium,
                            color = colorResource(id = R.color.secondary_text_color),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                SignUp(viewModel,navController)
            }
        }
    }
}
