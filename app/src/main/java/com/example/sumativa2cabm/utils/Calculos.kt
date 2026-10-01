package com.example.sumativa2cabm.utils

//aqui cree las funciones para hacer los calculos de la cuenta

fun calcularSubtotal(cantidad: Int, precio: Double): Double {
    return cantidad * precio
}

fun calcularPropina(subtotal: Double, porcentaje: Double): Double {
    return subtotal * (porcentaje / 100)
}

fun calcularTotal(subtotal: Double, propina: Double): Double {
    return subtotal + propina
}
