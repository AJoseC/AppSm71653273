package pe.edu.idat.appsm71653273.auth.bd

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface PacienteDao {
    @Query("SELECT * FROM paciente LIMIT 1")
    fun obtenerPaciente(): LiveData<PacienteEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarPaciente(vararg paciente: PacienteEntity)
}