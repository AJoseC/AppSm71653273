package pe.edu.idat.appsm71653273.core.retrofit

import pe.edu.idat.appsm71653273.home.data.network.response.DatoResponse
import retrofit2.Response
import retrofit2.http.GET

interface Client {

    @GET("todos")
    suspend fun listarDatos(): Response<List<DatoResponse>>
}