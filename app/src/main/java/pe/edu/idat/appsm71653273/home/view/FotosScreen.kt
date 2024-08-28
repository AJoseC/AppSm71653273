package pe.edu.idat.appsm71653273.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import pe.edu.idat.appsm71653273.R
import pe.edu.idat.appsm71653273.home.data.network.response.FotoResponse
import pe.edu.idat.appsm71653273.home.viewmodel.HomeViewModel

@Composable
fun fotosScreen(homeViewModel: HomeViewModel){
    val fotos by homeViewModel.fotoResponse.observeAsState(emptyList())
    LazyRow {
        items(fotos){
            foto-> fotosItem(foto = foto)
        }
    }
}

@Composable
fun fotosItem(foto:FotoResponse){
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .width(120.dp)
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(ImageRequest.Builder(LocalContext.current)
                    .data(data = foto.thumbnailUrl)
                    .apply (block = fun ImageRequest.Builder.(){
                        crossfade(true)
                        placeholder(R.drawable.imgperfil)
                    }).build()
                ), contentDescription = foto.title,
                modifier = Modifier
                    .size(100.dp)
                    .padding(4.dp)
            )
            Text(text = foto.albumId.toString(), modifier = Modifier.padding(4.dp))
            Text(text = foto.id.toString(), modifier = Modifier.padding(4.dp))
            Text(text = foto.title, modifier = Modifier.padding(4.dp))
        }
    }
}