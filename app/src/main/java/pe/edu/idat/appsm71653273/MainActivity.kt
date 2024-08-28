package pe.edu.idat.appsm71653273

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import pe.edu.idat.appsm71653273.auth.view.loginScreen
import pe.edu.idat.appsm71653273.auth.viewmodel.LoginViewModel
import pe.edu.idat.appsm71653273.core.ruteo.Ruta
import pe.edu.idat.appsm71653273.home.view.homeScreen
import pe.edu.idat.appsm71653273.home.viewmodel.HomeViewModel
import pe.edu.idat.appsm71653273.ui.theme.AppSm71653273Theme
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppSm71653273Theme {
                val navigation = rememberNavController()
                NavHost(navController = navigation, startDestination = Ruta.loginscreen.path,
                    builder = {
                        composable(Ruta.loginscreen.path){
                            loginScreen(loginViewModel, navigation)
                        }
                        composable(Ruta.homescreen.path){
                            homeScreen(homeViewModel, navigation)
                        }
                    })

            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppSm71653273Theme {

    }
}