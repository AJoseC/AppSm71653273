package pe.edu.idat.appsm71653273.core.retrofit

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.edu.idat.appsm71653273.home.data.network.service.FotoService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return  Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideClient(retrofit: Retrofit): Client {
        return retrofit.create(Client::class.java)
    }

    @Singleton
    @Provides
    fun provideFotoClient(retrofit: Retrofit): FotoClient{
        return retrofit.create(FotoClient::class.java)
    }
}