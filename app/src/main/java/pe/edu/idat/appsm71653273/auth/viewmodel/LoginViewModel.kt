package pe.edu.idat.appsm71653273.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.edu.idat.appsm71653273.auth.model.Paciente
import pe.edu.idat.appsm71653273.auth.bd.PacienteDao
import pe.edu.idat.appsm71653273.auth.bd.PacienteDatabase
import pe.edu.idat.appsm71653273.auth.bd.PacienteEntity
import pe.edu.idat.appsm71653273.auth.domain.RegistrarDatosUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor( private val registrarDatosUseCase: RegistrarDatosUseCase): ViewModel() {

    private val _paciente = listOf(
        Paciente("pac1", "12345","jose casanova","123456789"),
        Paciente("pac2", "12345","salvador cabrera","123456789"),
        Paciente("pac3", "12345","sergio castilla","123456789")
    )

    private val _pacienteDatos = MutableLiveData<Paciente?>()
    val pacientedatos : LiveData<Paciente?> = _pacienteDatos

    var _botonLoginHabilitado = MutableLiveData<Boolean>()
    var botonLoginHabilitado : LiveData<Boolean> = _botonLoginHabilitado

    private val _codpaciente = MutableLiveData<String>()
    val codpaciente : LiveData<String> = _codpaciente
    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    fun onLoginValueChanged(codpaciente:String, password: String){
        _codpaciente.value = codpaciente
        _password.value = password
        _botonLoginHabilitado.value = habilitarBoton(codpaciente, password)
    }

    fun habilitarBoton (usuario:String, password: String)
            = usuario.length > 2 && password.length > 2

    fun autenticacion(codpaciente: String, password: String){
        val paciente = _paciente.find { it.codpaciente == codpaciente && it.password == password}
        _pacienteDatos.value = paciente

        if (paciente != null){
            viewModelScope.launch {
                val pacienteEntity = PacienteEntity(
                    codpaciente = paciente.codpaciente,
                    password = paciente.password,
                    nombreapellido = paciente.nombreapellido,
                    celular = paciente.celular
                )
                registrarDatosUseCase(pacienteEntity)
            }
        }
    }
}