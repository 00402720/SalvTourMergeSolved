package com.salvatour.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.salvatour.R

@Composable
fun OutlinedInput(text : String, mainColor: Int, specialColor : Int = mainColor){

    var textValue by remember { mutableStateOf("") }
    
    OutlinedTextField(
        value = textValue,
        onValueChange = { textValue = it },
        label = { Text(text) },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            unfocusedLabelColor = colorResource(id = mainColor),
            unfocusedIndicatorColor = colorResource(id = specialColor),
            unfocusedTextColor = colorResource(id = mainColor),

            focusedContainerColor = Color.Transparent,
            focusedLabelColor = colorResource(id = mainColor),
            focusedIndicatorColor = colorResource(id = specialColor),
            focusedTextColor = colorResource(id = mainColor),

        )
    )
}

@Preview
@Composable
fun OutlinedInputPreview(){
    OutlinedInput(text = "Email", R.color.primary_text_color)
}