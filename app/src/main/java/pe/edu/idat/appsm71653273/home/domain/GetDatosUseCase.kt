package pe.edu.idat.appsm71653273.home.domain

import pe.edu.idat.appsm71653273.home.data.network.response.DatoResponse
import pe.edu.idat.appsm71653273.home.data.repository.ClientRepository
import javax.inject.Inject

class GetDatosUseCase @Inject constructor(private val clientRepository: ClientRepository) {
    suspend operator fun invoke():List<DatoResponse>{
        return clientRepository.listarDatos()
    }
}