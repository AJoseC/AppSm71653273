package pe.edu.idat.appsm71653273.auth.view

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import pe.edu.idat.appsm71653273.auth.viewmodel.LoginViewModel
import pe.edu.idat.appsm71653273.core.ruteo.Ruta

@Composable
fun loginScreen(loginViewModel: LoginViewModel, navController: NavController){
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) {
            paddingInit ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingInit)){

            formularioLogin(
                Modifier.align(Alignment.Center),
                loginViewModel, snackbarHostState, navController)
        }
    }
}

@Composable
fun formularioLogin(modifier: Modifier, loginViewModel: LoginViewModel,
                    state: SnackbarHostState, navController: NavController){
    val usuario: String by loginViewModel.codpaciente.observeAsState(initial = "")
    val password: String by loginViewModel.password.observeAsState(initial = "")
    val botonHabilitado: Boolean by loginViewModel.botonLoginHabilitado.observeAsState(initial = false)

    Column(modifier = modifier.padding(start = 5.dp, end = 5.dp)) {
        txtusuario(usuario = usuario) { loginViewModel.onLoginValueChanged(it, password) }
        Spacer(modifier = Modifier.size(4.dp))
        txtpassword(password = password) { loginViewModel.onLoginValueChanged(usuario, it) }
        Spacer(modifier = Modifier.size(4.dp))
        loginButton(botonHabilitado, loginViewModel, state, navController)
    }

}

@Composable
fun txtusuario(usuario: String, onTextChanged: (String) -> Unit) {
    OutlinedTextField(value = usuario,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Usuario") },
        maxLines = 1,
        leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "persona")},
        singleLine = true)
}
@Composable
fun txtpassword(password: String, onTextChanged: (String) -> Unit) {
    var visible by rememberSaveable {
        mutableStateOf(false)
    }
    OutlinedTextField(value = password,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Password") },
        maxLines = 1,
        singleLine = true,
        leadingIcon = { Icon(imageVector = Icons.Default.Key, contentDescription = "persona")},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val icon = if(visible){
                Icons.Filled.VisibilityOff
            }else Icons.Filled.Visibility
            IconButton(onClick = { visible = !visible }) {
                Icon(imageVector = icon, contentDescription = "ver password")
            }
        },
        visualTransformation = if(visible){
            VisualTransformation.None
        } else PasswordVisualTransformation()
    )
}

@Composable
fun loginButton(botonHabilitado: Boolean, loginViewModel: LoginViewModel,
                state: SnackbarHostState, navController: NavController){

    val paciente by loginViewModel.pacientedatos.observeAsState()
    val scope = rememberCoroutineScope()
    Button(enabled = botonHabilitado,
        onClick = { loginViewModel.autenticacion(loginViewModel.codpaciente.value ?: "", loginViewModel.password.value ?: "") },
        modifier = Modifier.fillMaxWidth()) {
        Text(text = "INGRESAR")
    }
    paciente?.let {
        if (it != null){
            navController.navigate(Ruta.homescreen.path)
        }else{
            scope.launch {
                state.showSnackbar("Login fallido",
                    actionLabel = "OK", duration = SnackbarDuration.Short)
            }
        }
    }
}
