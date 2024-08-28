package pe.edu.idat.appsm71653273.home.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import pe.edu.idat.appsm71653273.home.data.network.response.DatoResponse
import pe.edu.idat.appsm71653273.home.viewmodel.HomeViewModel

@Composable
fun datosScreen(homeViewModel: HomeViewModel){
    val datos by homeViewModel.datoResponse.observeAsState(emptyList())
    LazyColumn {
        items(datos){
            dato-> datosItem(dato = dato)
        }
    }
}

@Composable
fun datosItem(dato: DatoResponse){
    Card(elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ))
    {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment =  Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = dato.userid.toString(), color = Color.Gray)
                Text(text = dato.id.toString(), fontWeight = FontWeight.Bold)
                Text(text = dato.title, color = Color.Gray)
                Text(text = dato.completed.toString() , color = Color.Gray)

            }
        }
    }
}