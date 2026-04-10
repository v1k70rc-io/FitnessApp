package com.v1k70r.fitnessapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.v1k70r.fitnessapp.data.dao.EntrenamientoDao
import com.v1k70r.fitnessapp.data.dao.NutricionYPasosDao
import com.v1k70r.fitnessapp.data.entity.Alimento
import com.v1k70r.fitnessapp.data.entity.Categoria
import com.v1k70r.fitnessapp.data.entity.Ejercicio
import com.v1k70r.fitnessapp.data.entity.RegistroComida
import com.v1k70r.fitnessapp.data.entity.RegistroPasos
import com.v1k70r.fitnessapp.data.entity.RutinaDia
import com.v1k70r.fitnessapp.data.entity.SetEntrenamiento

// 1. Le decimos a Room cuáles son todas las tablas que conforman esta base de datos
@Database(
    entities = [
        Categoria::class,
        Ejercicio::class,
        RutinaDia::class,
        SetEntrenamiento::class,
        Alimento::class,
        RegistroComida::class,
        RegistroPasos::class
    ],
    version = 1, // Si en el futuro agregas más tablas, cambiarás esto a 2
    exportSchema = false
)
abstract class FitnessDatabase : RoomDatabase() {

    // 2. Conectamos los DAOs
    abstract fun entrenamientoDao(): EntrenamientoDao
    abstract fun nutricionYPasosDao(): NutricionYPasosDao

    // 3. Patrón Singleton: Nos aseguramos de que solo exista UNA copia de la base de datos abierta a la vez
    companion object {
        @Volatile
        private var INSTANCE: FitnessDatabase? = null

        fun getDatabase(context: Context): FitnessDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FitnessDatabase::class.java,
                    "fitness_local_database" // El nombre real del archivo oculto en el celular
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}