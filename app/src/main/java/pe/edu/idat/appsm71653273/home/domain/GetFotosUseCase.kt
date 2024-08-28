package pe.edu.idat.appsm71653273.home.domain

import pe.edu.idat.appsm71653273.home.data.network.response.FotoResponse
import pe.edu.idat.appsm71653273.home.data.repository.FotoRepository
import javax.inject.Inject

class GetFotosUseCase @Inject constructor(private  val fotoRepository: FotoRepository) {
    suspend operator fun invoke(): List<FotoResponse>{
        return fotoRepository.listarFotos()
    }
}