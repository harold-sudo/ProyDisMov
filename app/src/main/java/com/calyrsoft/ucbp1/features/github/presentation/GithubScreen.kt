package com.calyrsoft.ucbp1.features.github.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel
import com.calyrsoft.ucbp1.features.github.gitalias.GitaliasUI
@Composable
fun GithubScreen(
    modifier: Modifier = Modifier,
    vm: GithubViewModel = koinViewModel()
) {
    var nickname by remember { mutableStateOf("") }
    // Aquí se corrige el nombre de la variable
    val state by vm.state.collectAsState()

    OutlinedButton(onClick = { vm.fetchAlias(nickname) }) {
        Text(text = "Buscar Alias")
    }

    when (val st = state) {
        is GithubViewModel.GithubStateUI.Error -> {
            Text(st.message)
        }
        is GithubViewModel.GithubStateUI.Init -> {
            Text(text = "Init")
        }
        is GithubViewModel.GithubStateUI.Loading -> {
            Text(text = "Loading")
        }
        is GithubViewModel.GithubStateUI.Success -> {
            Column {
                Text(st.github.name) // Accedemos a la propiedad "name" del modelo de usuario
                AsyncImage(
                    model = st.github.avatarUrl,
                    contentDescription = null,
                    modifier = Modifier.size(size = 100.dp),
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}