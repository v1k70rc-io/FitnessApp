package com.v1k70r.fitnessapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.v1k70r.fitnessapp.ui.theme.FitnessAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnessAppTheme {
                // Preparamos el controlador de navegación
                val navController = rememberNavController()

                // Scaffold es la estructura principal que contiene la barra inferior
                Scaffold(
                    bottomBar = { MenuInferior(navController = navController) },
                    containerColor = MaterialTheme.colorScheme.background
                ) { paddingValues ->
                    // El NavHost es el "espacio" donde se cargan las pantallas
                    NavHost(
                        navController = navController,
                        startDestination = Ruta.Pasos.ruta, // La app inicia en "Pasos"
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        // Aquí definimos qué pantalla se muestra según la ruta
                        composable(Ruta.Pasos.ruta) { PantallaPlaceholder("Contador de Pasos") }
                        composable(Ruta.Entrenamiento.ruta) { PantallaPlaceholder("Diario de Entrenamiento") }
                        composable(Ruta.Comida.ruta) { PantallaPlaceholder("Diario de Comidas") }
                    }
                }
            }
        }
    }
}

// Componente visual del menú inferior
@Composable
fun MenuInferior(navController: NavHostController) {
    val items = listOf(Ruta.Pasos, Ruta.Entrenamiento, Ruta.Comida)

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface, // Fondo gris oscuro
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val rutaActual = navBackStackEntry?.destination?.route

        items.forEach { ruta ->
            NavigationBarItem(
                icon = { ruta.icono?.let { Icon(imageVector = it, contentDescription = ruta.titulo) } },
                label = { Text(text = ruta.titulo) },
                selected = rutaActual == ruta.ruta,
                onClick = {
                    navController.navigate(ruta.ruta) {
                        // Evita que se abran múltiples copias de la misma pantalla
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                // Aplicamos nuestros colores: Rojo si está seleccionado, gris si no
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary, // Blanco
                    indicatorColor = MaterialTheme.colorScheme.primary, // Círculo rojo
                    selectedTextColor = MaterialTheme.colorScheme.primary, // Texto rojo
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant, // Gris
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant // Gris
                )
            )
        }
    }
}

// Una pantalla falsa temporal para rellenar los espacios
@Composable
fun PantallaPlaceholder(titulo: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = titulo,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}