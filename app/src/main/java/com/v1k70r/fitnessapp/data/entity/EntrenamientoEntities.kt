package com.v1k70r.fitnessapp.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

// 1. Tabla de Categorías (Ej: Pecho, Espalda, Pierna)
@Entity(tableName = "tabla_categorias")
data class Categoria(
    @PrimaryKey(autoGenerate = true) val idCategoria: Int = 0,
    val nombreCategoria: String
)

// 2. Tabla de Ejercicios (Ej: Press de Banca)
@Entity(
    tableName = "tabla_ejercicios",
    foreignKeys = [
        ForeignKey(
            entity = Categoria::class,
            parentColumns = ["idCategoria"],
            childColumns = ["fkCategoriaId"],
            onDelete = ForeignKey.CASCADE // Si borras la categoría "Pecho", se borran sus ejercicios
        )
    ]
)
data class Ejercicio(
    @PrimaryKey(autoGenerate = true) val idEjercicio: Int = 0,
    val fkCategoriaId: Int,
    val nombreEjercicio: String
)

// 3. Tabla para el Día de Rutina (Ej: El entrenamiento de hoy)
@Entity(tableName = "tabla_rutinas_dia")
data class RutinaDia(
    @PrimaryKey(autoGenerate = true) val idRutina: Int = 0,
    val fecha: String // Guardaremos la fecha como "YYYY-MM-DD"
)

// 4. Tabla de los Sets (Ej: 4 repeticiones con 190 lbs)
@Entity(
    tableName = "tabla_sets",
    foreignKeys = [
        ForeignKey(entity = RutinaDia::class, parentColumns = ["idRutina"], childColumns = ["fkRutinaId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Ejercicio::class, parentColumns = ["idEjercicio"], childColumns = ["fkEjercicioId"], onDelete = ForeignKey.CASCADE)
    ]
)
data class SetEntrenamiento(
    @PrimaryKey(autoGenerate = true) val idSet: Int = 0,
    val fkRutinaId: Int,
    val fkEjercicioId: Int,
    val peso: Float,
    val repeticiones: Int
)