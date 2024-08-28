package pe.edu.idat.appsm71653273.home.data.repository

import pe.edu.idat.appsm71653273.home.data.network.response.DatoResponse
import pe.edu.idat.appsm71653273.home.data.network.service.ClientService
import javax.inject.Inject

class ClientRepository @Inject constructor(private val clientService: ClientService ) {
    suspend fun listarDatos(): List<DatoResponse>{
        return clientService.listarDatos()
    }
}