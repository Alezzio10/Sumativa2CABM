package com.example.sumativa2cabm.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import java.util.Locale

//aqui muestro un titulo pequeño y abajo la cantidad en dolares
@Composable
fun Resultado(titulo: String, monto: Double) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = titulo, fontSize = 14.sp)
        Text(
            text = "$" + String.format(Locale.US, "%.2f", monto),
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
