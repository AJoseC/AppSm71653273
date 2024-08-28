package pe.edu.idat.appsm71653273.home.data.network.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.edu.idat.appsm71653273.core.retrofit.Client
import pe.edu.idat.appsm71653273.home.data.network.response.DatoResponse
import javax.inject.Inject

class ClientService @Inject constructor(private val client: Client) {

    suspend fun listarDatos(): List<DatoResponse>{
        return withContext(Dispatchers.IO){
            val response = client.listarDatos()
            response.body()!!
        }
    }
}