package com.v1k70r.fitnessapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.v1k70r.fitnessapp.data.entity.Alimento
import com.v1k70r.fitnessapp.data.entity.RegistroComida
import com.v1k70r.fitnessapp.data.entity.RegistroPasos
import kotlinx.coroutines.flow.Flow

@Dao
interface NutricionYPasosDao {

    // --- ALIMENTOS ---
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarAlimento(alimento: Alimento)

    @Query("SELECT * FROM tabla_alimentos")
    fun obtenerCatalogoAlimentos(): Flow<List<Alimento>>

    @Query("SELECT * FROM tabla_alimentos WHERE nombre LIKE '%' || :busqueda || '%'")
    suspend fun buscarAlimentoPorNombre(busqueda: String): List<Alimento>

    // --- REGISTRO DIARIO DE COMIDA ---
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun registrarComida(registro: RegistroComida)

    @Query("SELECT * FROM tabla_registros_comida WHERE fecha = :fechaBuscada AND tipoComida = :tipo")
    fun obtenerComidasPorTipo(fechaBuscada: String, tipo: String): Flow<List<RegistroComida>>

    // --- PASOS ---
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardarPasosDelDia(registroPasos: RegistroPasos)

    @Query("SELECT * FROM tabla_pasos WHERE fecha = :fechaBuscada")
    fun obtenerPasosDelDia(fechaBuscada: String): Flow<RegistroPasos?>
}