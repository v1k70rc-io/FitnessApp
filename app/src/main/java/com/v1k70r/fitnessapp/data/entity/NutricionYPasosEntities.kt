package com.v1k70r.fitnessapp.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

// --- MÓDULO DE NUTRICIÓN ---

@Entity(tableName = "tabla_alimentos")
data class Alimento(
    @PrimaryKey(autoGenerate = true) val idAlimento: Int = 0,
    val nombre: String,
    val porcionBase: Float, // Ej: 100.0 (gramos)
    val calorias: Float,
    val proteinas: Float,
    val carbohidratos: Float,
    val grasas: Float,
    val esPersonalizado: Boolean // Para saber si lo creó el usuario
)

@Entity(
    tableName = "tabla_registros_comida",
    foreignKeys = [
        ForeignKey(entity = Alimento::class, parentColumns = ["idAlimento"], childColumns = ["fkAlimentoId"], onDelete = ForeignKey.CASCADE)
    ]
)
data class RegistroComida(
    @PrimaryKey(autoGenerate = true) val idRegistro: Int = 0,
    val fecha: String,
    val tipoComida: String, // "Desayuno", "Almuerzo", "Cena", "Snack"
    val fkAlimentoId: Int,
    val cantidadConsumida: Float // Multiplicador. Si comió el doble de la porción, esto es 2.0
)

// --- MÓDULO DE PASOS ---

@Entity(tableName = "tabla_pasos")
data class RegistroPasos(
    @PrimaryKey val fecha: String, // La fecha es la clave, solo hay un registro por día
    val cantidadPasos: Int,
    val metaPasosDiaria: Int
)