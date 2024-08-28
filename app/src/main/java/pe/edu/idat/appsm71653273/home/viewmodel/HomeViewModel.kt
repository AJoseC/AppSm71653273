package pe.edu.idat.appsm71653273.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.edu.idat.appsm71653273.home.data.network.response.DatoResponse
import pe.edu.idat.appsm71653273.home.data.network.response.FotoResponse
import pe.edu.idat.appsm71653273.home.domain.GetDatosUseCase
import pe.edu.idat.appsm71653273.home.domain.GetFotosUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDatosUseCase: GetDatosUseCase,
    private val getFotosUseCase: GetFotosUseCase
) : ViewModel() {

    private val _datoResponse = MutableLiveData<List<DatoResponse>>()
    val datoResponse: LiveData<List<DatoResponse>> = _datoResponse

    private val _fotoResponse = MutableLiveData<List<FotoResponse>>()
    val fotoResponse: LiveData<List<FotoResponse>> = _fotoResponse

    init {
        listarDatos()
        listarFotos()
    }

    fun listarDatos(){
        viewModelScope.launch {
            val response = getDatosUseCase()
            _datoResponse.value = response
        }
    }

    fun listarFotos(){
        viewModelScope.launch {
            val response = getFotosUseCase()
            _fotoResponse.value = response
        }
    }

}