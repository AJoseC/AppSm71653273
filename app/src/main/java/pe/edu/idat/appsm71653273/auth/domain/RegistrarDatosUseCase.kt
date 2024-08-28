package pe.edu.idat.appsm71653273.auth.domain

import pe.edu.idat.appsm71653273.auth.bd.PacienteEntity
import pe.edu.idat.appsm71653273.auth.repository.PacienteAuthRepository
import javax.inject.Inject

class RegistrarDatosUseCase @Inject constructor(
    private val repository: PacienteAuthRepository
) {
    suspend operator fun invoke(pacienteEntity: PacienteEntity){
        return repository.insertarPaciente(pacienteEntity)
    }
}