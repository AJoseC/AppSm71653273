package pe.edu.idat.appsm71653273.home.data.network.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.edu.idat.appsm71653273.core.retrofit.FotoClient
import pe.edu.idat.appsm71653273.home.data.network.response.FotoResponse
import javax.inject.Inject

class FotoService @Inject constructor(private val fotoClient: FotoClient) {
    suspend fun listarFotos(): List<FotoResponse>{
        return withContext(Dispatchers.IO){
            val response = fotoClient.listarFotos()
            response.body()!!
        }
    }
}