package com.v1k70r.fitnessapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.ui.graphics.vector.ImageVector

// Ahora el icono puede ser nulo, porque la pantalla de Login no tiene icono en la barra inferior
sealed class Ruta(val ruta: String, val titulo: String, val icono: ImageVector?) {
    object Login : Ruta("login", "Login", null)
    object Pasos : Ruta("pasos", "Pasos", Icons.Filled.DirectionsWalk)
    object Entrenamiento : Ruta("entrenamiento", "Rutinas", Icons.Filled.FitnessCenter)
    object Comida : Ruta("comida", "Diario", Icons.Filled.Restaurant)
}