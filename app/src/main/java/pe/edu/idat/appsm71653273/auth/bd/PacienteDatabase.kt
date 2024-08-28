package pe.edu.idat.appsm71653273.auth.bd

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PacienteEntity::class], version = 1)
abstract class PacienteDatabase : RoomDatabase() {

    abstract fun pacienteDao(): PacienteDao
}