package com.salvatour.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.salvatour.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ReservaAlerta(
    whatsappLink: String,
    onDismiss: () -> Unit
) {
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        scope.launch {
            delay(6000)
            onDismiss()
        }
    }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "La reserva se ha logrado exitosamente") },
        text = {
            Column {
                Text("Se puede comunicar a este Whatsapp para confirmar")
                val clipboardManager = LocalClipboardManager.current
                OutlinedTextField(
                    value = whatsappLink,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Whatsapp (link)") },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.copy_icon),
                            contentDescription = "Copiar",
                            modifier = Modifier.clickable {
                                clipboardManager.setText(AnnotatedString(whatsappLink))
                            }
                                .size(20.dp)
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {}
    )
}

@Preview(showBackground = true)
@Composable
fun ReservaAlertaPreview() {
    var mostrarAlerta by remember { mutableStateOf(false) }
    ReservaAlerta(
        whatsappLink = "link",
        onDismiss = { mostrarAlerta = false }
    )
}


