package com.salvatour.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.salvatour.R

@Composable
fun CreateComment(){

    var comment by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 16.dp, end = 16.dp), horizontalAlignment = Alignment.End) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_person_24),
                contentDescription = "Icono de usuario",
                tint = Color.White,
                modifier = Modifier.size(width = 40.dp, height = 40.dp)
            )
            Column {
                Text(text = /*username*/"NombreUsuario", color = colorResource(id = R.color.primary_text_color))
                Spacer(modifier = Modifier.size(15.dp))
                OutlinedTextField(
                    value = comment,
                    onValueChange = { comment = it },
                    label = { Text("Commentario") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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
        }
        Spacer(modifier = Modifier.size(8.dp))
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults
                .buttonColors(containerColor = colorResource(id = R.color.special_background))
        ) {
            Text(text = "Publicar", color = colorResource(id = R.color.primary_text_color))
        }
    }
}

@Preview
@Composable
fun CreateCommentPreview(){
    CreateComment()
}