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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import com.salvatour.R
import com.salvatour.data.model.PostModel
import androidx.compose.ui.unit.sp

@Composable
fun TourCard(
    onClick: () -> Unit,
    Tourmodel: PostModel,
    navController: NavController
){



    Box(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .padding(top = 8.dp)
            .background(Color.Transparent, RoundedCornerShape(8.dp)) // Fondo rgb(51, 193, 154)
            .clickable { onClick() }
            .clickable { navController.navigate("TourDisplayView") }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    colorResource(id = R.color.special_background),
                    RoundedCornerShape(8.dp)
                )
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
                    .fillMaxWidth()

                    .padding(start = 8.dp)) {
                Text(text = "TOUR: ${Tourmodel.title}", color =Color.White, fontSize = 10.sp)
                Text(text = "LUGAR: ${Tourmodel.mapLink}", color =Color.White, fontSize = 10.sp)
                Text(text = "PRECIO: ${Tourmodel.price}", color =Color.White, fontSize = 10.sp)
            }
        }
    }
}

