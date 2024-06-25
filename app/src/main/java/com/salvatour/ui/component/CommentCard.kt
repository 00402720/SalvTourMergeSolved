package com.salvatour.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.salvatour.R

@Composable
fun CommentDisplay(navController: NavController){
    Row(modifier = Modifier.fillMaxWidth()) {

        IconButton(onClick = { navController.navigate("UserPostScreen") }) {
            Icon(painter = painterResource(id = R.drawable.baseline_person_24),
                contentDescription = "Icono de usuario",
                tint = colorResource(id = R.color.primary_text_color),
                modifier = Modifier.size(width = 50.dp, height = 50.dp)
            )
        }
        Column {
            Text(text = /*username*/"NombreUsuario", color = colorResource(id = R.color.primary_text_color))
            Text(text = /*commentDate*/"Date", color = colorResource(id = R.color.light_gray))
            Spacer(modifier = Modifier.size(15.dp))
            Text(text = /*commentMessage*/"El tour fue como un viaje en el tiempo, descubriendo la vida cotidiana" +
                    "de una  antigua comunidad mesoamericana. La guía experta y la conservación del sitio hicieron " +
                    "que la  experiencia fuera fascinante y enriquecedora", color = Color.White)
        }
    }

}

@Preview
@Composable
fun CommentCard(){
    CommentDisplay(navController = rememberNavController())
}