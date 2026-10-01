package com.example.sumativa2cabm.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

//broton principal
@Composable
fun BotonPrincipal(texto: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = texto, color = Color.White)
    }
}

//boton para limpiar
@Composable
fun BotonTexto(texto: String, onClick: () -> Unit) {
    TextButton(onClick = onClick) {
        Text(text = texto, color = Color.Black)
    }
}
