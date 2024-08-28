package pe.edu.idat.appsm71653273.core.ruteo

sealed class Ruta(val path: String) {
    object loginscreen : Ruta("loginScreen")
    object datosscreen : Ruta("datosScreen")
    object fotosscreen : Ruta("fotosScreen")
    object homescreen : Ruta("homescreen")
}