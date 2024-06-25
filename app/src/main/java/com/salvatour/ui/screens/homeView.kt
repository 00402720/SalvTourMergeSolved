package com.salvatour.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.salvatour.MainViewModel
import com.salvatour.R
import com.salvatour.util.navigation.Screen

@Composable
fun HomeView(
    navController: NavController)
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.primary_background)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Spacer(modifier = Modifier.height(40.dp))

        Image(painter = painterResource(id = R.drawable.img),
            contentDescription = "SalvaTourLogo",
            modifier = Modifier
                .padding(vertical = 40.dp)
                .size(200.dp)
        )

        Text(
            text = "VEN Y DISFRUTA DE NUESTROS ATRACTIVOS !!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = colorResource(id = R.color.primary_text_color),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "\"SalvaTour\" te da la bienvenida a un viaje lleno de descubrimientos, con una interfaz intuitiva que te guía a través de los tesoros escondidos y las maravillas culturales de El Salvador.",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = colorResource(id = R.color.primary_text_color),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 32.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(onClick = { navController.navigate("SignUpView") },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.primary_action_button)
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(4.dp)
        ){
            Text("RESERVA YA!", color = colorResource(id = R.color.primary_text_color))
            Icon(Icons.Filled.ArrowForward, contentDescription = "Icono flecha", tint = colorResource(id = R.color.primary_text_color))
        }

    }
}


@Preview(showBackground = true)
@Composable
private fun HomeViewPreview() {
    HomeView(navController = rememberNavController())
}
