package pe.edu.idat.appsm71653273.core.retrofit

import pe.edu.idat.appsm71653273.home.data.network.response.FotoResponse
import retrofit2.Response
import retrofit2.http.GET

interface FotoClient {
    @GET("photos")
    suspend fun listarFotos(): Response<List<FotoResponse>>
}