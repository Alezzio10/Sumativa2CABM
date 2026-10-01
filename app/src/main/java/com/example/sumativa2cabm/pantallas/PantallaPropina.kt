package com.example.sumativa2cabm.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sumativa2cabm.componentes.BotonPrincipal
import com.example.sumativa2cabm.componentes.BotonTexto
import com.example.sumativa2cabm.componentes.CampoTexto
import com.example.sumativa2cabm.componentes.Resultado
import com.example.sumativa2cabm.utils.calcularPropina
import com.example.sumativa2cabm.utils.calcularSubtotal
import com.example.sumativa2cabm.utils.calcularTotal

@Composable
fun PantallaPropina(modifier: Modifier = Modifier) {
    var platillo by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var porcentaje by remember { mutableStateOf("") }

    var errorPlatillo by remember { mutableStateOf("") }
    var errorCantidad by remember { mutableStateOf("") }
    var errorPrecio by remember { mutableStateOf("") }
    var errorPorcentaje by remember { mutableStateOf("") }

    // resultados
    var propina by remember { mutableDoubleStateOf(0.0) }
    var total by remember { mutableDoubleStateOf(0.0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Resultado(titulo = "Propina", monto = propina)
            Resultado(titulo = "Total a pagar", monto = total)
        }

        Spacer(modifier = Modifier.height(24.dp))

        CampoTexto(
            valor = platillo,
            onValorChange = {
                platillo = it
                errorPlatillo = ""
            },
            etiqueta = "Nombre del platillo",
            error = errorPlatillo
        )

        CampoTexto(
            valor = cantidad,
            onValorChange = {
                cantidad = it
                errorCantidad = ""
            },
            etiqueta = "Cantidad",
            tipoTeclado = KeyboardType.Number,
            error = errorCantidad
        )

        CampoTexto(
            valor = precio,
            onValorChange = {
                precio = it
                errorPrecio = ""
            },
            etiqueta = "Precio unitario",
            tipoTeclado = KeyboardType.Decimal,
            error = errorPrecio
        )

        CampoTexto(
            valor = porcentaje,
            onValorChange = {
                porcentaje = it
                errorPorcentaje = ""
            },
            etiqueta = "Porcentaje de propina (máx. 10%)",
            tipoTeclado = KeyboardType.Decimal,
            error = errorPorcentaje
        )

        Spacer(modifier = Modifier.height(8.dp))

        BotonPrincipal(texto = "Calcular Total") {
            var hayError = false

            if (platillo.isBlank()) {
                errorPlatillo = "Ingrese el nombre del platillo"
                hayError = true
            }

            val cant = cantidad.toIntOrNull()
            if (cantidad.isBlank()) {
                errorCantidad = "Ingrese la cantidad"
                hayError = true
            } else if (cant == null || cant <= 0) {
                errorCantidad = "Cantidad no valida"
                hayError = true
            }

            val prec = precio.toDoubleOrNull()
            if (precio.isBlank()) {
                errorPrecio = "Ingrese el precio"
                hayError = true
            } else if (prec == null || prec <= 0) {
                errorPrecio = "Precio no valido"
                hayError = true
            }

            val porc = porcentaje.toDoubleOrNull()
            if (porcentaje.isBlank()) {
                errorPorcentaje = "Ingrese el porcentaje"
                hayError = true
            } else if (porc == null || porc < 0) {
                errorPorcentaje = "Porcentaje no valido"
                hayError = true
            } else if (porc > 10) {
                errorPorcentaje = "La propina no puede ser mayor al 10%"
                hayError = true
            }


            if (!hayError && cant != null && prec != null && porc != null) {
                val subtotal = calcularSubtotal(cant, prec)
                propina = calcularPropina(subtotal, porc)
                total = calcularTotal(subtotal, propina)
            }
        }

        BotonTexto(texto = "Limpiar") {
            platillo = ""
            cantidad = ""
            precio = ""
            porcentaje = ""
            errorPlatillo = ""
            errorCantidad = ""
            errorPrecio = ""
            errorPorcentaje = ""
            propina = 0.0
            total = 0.0
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaPropinaPreview() {
    PantallaPropina()
}
