package com.salvatour.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.salvatour.MainViewModel
import com.salvatour.R
import com.salvatour.data.model.PostModel
import com.salvatour.util.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePost(
    viewModel: MainViewModel,
    onPublishClick: () -> Unit,
    navController: NavController,
    token: String
) {
    var tourData by remember { mutableStateOf(PostModel("", "", "", "", "", "", "", "", "")) }
    var imageUris by remember { mutableStateOf(listOf<Uri>()) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = { uris -> imageUris = uris }
    )

    val allFieldsFilled = tourData.title.isNotEmpty() && tourData.price.isNotEmpty() && tourData.duration.isNotEmpty() &&
            tourData.mapLink.isNotEmpty() && tourData.description.isNotEmpty() && tourData.whatsappLink.isNotEmpty() && imageUris.isNotEmpty()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = colorResource(R.color.secondary_background)
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Screen.HomeMenu.route) }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier.size(24.dp),
                            tint = Color.White
                        )
                    }
                },
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .background(
                                    colorResource(id = R.color.primary_background),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(horizontal = 48.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "Crear Publicación",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(colorResource(id = R.color.primary_background)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(
                        color = colorResource(id = R.color.special_background),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable { imagePickerLauncher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                if (imageUris.isNotEmpty()) {
                    LazyRow(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(imageUris) { uri ->
                            Box(modifier = Modifier.size(150.dp)) {
                                Image(
                                    painter = rememberAsyncImagePainter(uri),
                                    contentDescription = "Selected Image",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(RoundedCornerShape(8.dp))
                                )
                                IconButton(
                                    onClick = { imageUris = imageUris - uri },
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Remove Image",
                                        tint = Color.White
                                    )
                                }
                            }
                        }
                    }
                } else {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add_photo),
                            contentDescription = "Agregar fotos",
                            modifier = Modifier.size(40.dp),
                            tint = colorResource(id = R.color.primary_text_color)
                        )
                        Text(text = "Agregar fotos", color = colorResource(id = R.color.primary_text_color))
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.End
            ) {
                item {
                    OutlinedTextField(
                        value = tourData.title,
                        onValueChange = { tourData = tourData.copy(title = it) },
                        label = { Text("Título") },
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
                    OutlinedTextField(
                        value = tourData.price,
                        onValueChange = { tourData = tourData.copy(price = it) },
                        label = { Text("Precio") },
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
                item {
                    OutlinedTextField(
                        value = tourData.duration,
                        onValueChange = { tourData = tourData.copy(duration = it) },
                        label = { Text("Duración") },
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
                    OutlinedTextField(
                        value = tourData.mapLink,
                        onValueChange = { tourData = tourData.copy(mapLink = it) },
                        label = { Text("Ubicación (link)") },
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
                    OutlinedTextField(
                        value = tourData.description,
                        onValueChange = { tourData = tourData.copy(description = it) },
                        label = { Text("Descripción") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        maxLines = 4,
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
                    OutlinedTextField(
                        value = tourData.whatsappLink,
                        onValueChange = { tourData = tourData.copy(whatsappLink = it) },
                        label = { Text("WhatsApp link") },
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
                    Button(
                        modifier = Modifier.padding(vertical = 16.dp),
                        onClick = {
                            viewModel.postTour(token, tourData)
                            onPublishClick()
                        },
                        enabled = allFieldsFilled,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.primary_action_button),
                        ),
                    ) {
                        Text(text = "Publicar", color = colorResource(id = R.color.primary_text_color))
                    }
                }
            }
        }
    }
}
