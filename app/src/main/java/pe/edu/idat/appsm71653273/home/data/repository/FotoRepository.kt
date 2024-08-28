package pe.edu.idat.appsm71653273.home.data.repository


import pe.edu.idat.appsm71653273.home.data.network.response.FotoResponse
import pe.edu.idat.appsm71653273.home.data.network.service.FotoService
import javax.inject.Inject

class FotoRepository @Inject constructor(private val fotoService: FotoService ) {
    suspend fun listarFotos(): List<FotoResponse>{

        return fotoService.listarFotos().filter { it.albumId % 2 == 0 }
    }
}