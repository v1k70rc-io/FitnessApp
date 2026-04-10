package com.v1k70r.fitnessapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.v1k70r.fitnessapp.data.entity.Categoria
import com.v1k70r.fitnessapp.data.entity.Ejercicio
import com.v1k70r.fitnessapp.data.entity.RutinaDia
import com.v1k70r.fitnessapp.data.entity.SetEntrenamiento
import kotlinx.coroutines.flow.Flow

@Dao
interface EntrenamientoDao {

    // --- CATEGORÍAS ---
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarCategoria(categoria: Categoria)

    @Query("SELECT * FROM tabla_categorias")
    fun obtenerTodasLasCategorias(): Flow<List<Categoria>> // Flow hace que la UI se actualice sola si hay cambios

    // --- EJERCICIOS ---
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarEjercicio(ejercicio: Ejercicio)

    @Query("SELECT * FROM tabla_ejercicios WHERE fkCategoriaId = :categoriaId")
    fun obtenerEjerciciosPorCategoria(categoriaId: Int): Flow<List<Ejercicio>>

    // --- RUTINAS Y SETS ---
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun iniciarRutinaDia(rutina: RutinaDia): Long // Retorna el ID de la nueva rutina

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardarSet(setEntrenamiento: SetEntrenamiento)

    @Query("SELECT * FROM tabla_rutinas_dia WHERE fecha = :fechaBuscada")
    suspend fun obtenerRutinaPorFecha(fechaBuscada: String): RutinaDia?

    @Query("SELECT * FROM tabla_sets WHERE fkRutinaId = :rutinaId")
    fun obtenerSetsDeRutina(rutinaId: Int): Flow<List<SetEntrenamiento>>

    @Delete
    suspend fun borrarSet(setEntrenamiento: SetEntrenamiento)
}