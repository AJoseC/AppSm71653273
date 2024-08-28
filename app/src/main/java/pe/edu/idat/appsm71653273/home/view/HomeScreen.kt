package pe.edu.idat.appsm71653273.home.view


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import pe.edu.idat.appsm71653273.core.ruteo.Ruta
import pe.edu.idat.appsm71653273.core.utils.MenuItem
import pe.edu.idat.appsm71653273.home.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun homeScreen(homeViewModel: HomeViewModel, navPrincipal: NavController){

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            drawerContent(items = opcionesMenu(), onItemClick = { item ->
                coroutineScope.launch {
                    drawerState.close()
                }
                when (item.titulo) {
                 "Datos" -> navController.navigate(Ruta.datosscreen.path)
                   "Fotos" -> navController.navigate(Ruta.fotosscreen.path)


                }
            })
        },
        content = {
            Column {
                TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.LightGray,
                    titleContentColor = Color.Black
                ),
                    title = { Text(text = "App") },
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }, actions = {
                        IconButton(onClick = {
                            navPrincipal.navigate(Ruta.loginscreen.path){
                                popUpTo(navController.graph.startDestinationId)
                                { inclusive = true}
                            }
                        }) {
                            Icon(imageVector = Icons.AutoMirrored.Filled.ExitToApp, contentDescription = null,
                                tint = Color.Black)
                        }
                    })
                NavHost(navController = navController,
                    startDestination = Ruta.datosscreen.path) {
                    composable(Ruta.datosscreen.path){ datosScreen(homeViewModel)}
                    composable(Ruta.fotosscreen.path){ fotosScreen(homeViewModel) }


                }
            }
        }
    )
}


@Composable
fun drawerContent(
    items: List<MenuItem>,
    onItemClick: (MenuItem) -> Unit
) {
    Column(
        Modifier
            .fillMaxHeight().fillMaxWidth(0.87f)
            .background(Color.White)
            .systemBarsPadding()
    ) {
        cabeceraMenu()
        Spacer(modifier = Modifier.height(8.dp))
        items.forEach { item ->
            drawerMenuItem(item, onItemClick)
        }
    }
}

@Composable
fun drawerMenuItem(item: MenuItem, onItemClick: (MenuItem) -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onItemClick(item) }
        .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = item.icon, contentDescription = "")
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = item.titulo)
    }
}

@Composable
fun cabeceraMenu() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(16.dp))
    }
}

fun opcionesMenu(): List<MenuItem> {
    return listOf(
       MenuItem(Icons.Default.Person, "Datos"),
        MenuItem(Icons.Default.Photo, "Fotos"),

    )
}