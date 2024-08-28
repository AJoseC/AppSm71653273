package pe.edu.idat.appsm71653273.auth.repository

import pe.edu.idat.appsm71653273.auth.bd.PacienteDao
import pe.edu.idat.appsm71653273.auth.bd.PacienteEntity
import javax.inject.Inject

class PacienteAuthRepository @Inject constructor(
    private val pacienteDao: PacienteDao
) {
    suspend fun insertarPaciente(pacienteEntity: PacienteEntity){
        pacienteDao.insertarPaciente(pacienteEntity)
    }
}