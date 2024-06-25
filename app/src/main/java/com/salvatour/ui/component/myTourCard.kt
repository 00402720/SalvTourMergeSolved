package com.salvatour.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import com.salvatour.R

@Composable
fun MyTourCard(
    onClick: () -> Unit,
    navController: NavController
){
    Box(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .padding(top = 8.dp)
            .background(Color.Transparent, RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .clickable { navController.navigate("TourDisplayView") }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF4C6D6F), RoundedCornerShape(8.dp))
                .padding(2.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_1),
                contentDescription = "Tour Image",
                modifier = Modifier
                    .size(width = 135.dp, height = 100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier
                    .weight(1f)
                    .padding(vertical = 8.dp)
                    .padding(start = 8.dp)) {
                Row {
                    Text(text = "TOUR: ", color = Color.Black)
                    Text(text = "Aventura Maya", color = Color.White)
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Text(text = "LUGAR: ", color = Color.Black)
                    Text(text = "Parque Arqueológico Joya de Ceren", color = Color.White)
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Text(text = "PRECIO: ", color = Color.Black)
                    Text(text = "\$35.00", color = Color.White)
                }
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .background(
                    Color(0xFF00BFA6),
                    RoundedCornerShape(topEnd = 8.dp, bottomStart = 8.dp)
                )
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Icon(imageVector = Icons.Filled.Star, contentDescription = "", Modifier.size(18.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyTourCardPreview() {
    MyTourCard(onClick = {}, navController = rememberNavController())
}