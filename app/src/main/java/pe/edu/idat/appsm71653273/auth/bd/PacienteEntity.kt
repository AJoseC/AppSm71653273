package pe.edu.idat.appsm71653273.auth.bd

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "paciente")
data class PacienteEntity (
    @PrimaryKey
    val codpaciente: String,
    val password: String,
    val nombreapellido: String,
    val celular: String
)