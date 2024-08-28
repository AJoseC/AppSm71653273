package pe.edu.idat.appsm71653273.auth.bd

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class PacienteDbModule {

    @Provides
    fun providePacienteDao(pacienteDatabase: PacienteDatabase): PacienteDao {
        return pacienteDatabase.pacienteDao()
    }

    @Provides
    @Singleton
    fun providePacienteDatabase(@ApplicationContext context: Context): PacienteDatabase {
        return Room.databaseBuilder(context,
            PacienteDatabase::class.java,
            "pacientedb").build()

    }    }