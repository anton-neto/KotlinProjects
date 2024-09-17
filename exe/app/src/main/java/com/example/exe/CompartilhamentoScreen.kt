package com.example.exe

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CompartilhamentoScreen(
    navController: NavController,
    nome: String,
    telefone: String,
    biotipo: String
) {
    var intencao by rememberSaveable { mutableStateOf("") }
    var isNetworkAvailable by remember { mutableStateOf(false) }
    val context = LocalContext.current

    fun isConnectedToInternet(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
    }


    LaunchedEffect(Unit) {
        isNetworkAvailable = isConnectedToInternet(context)
        if (!isNetworkAvailable) {
            Toast.makeText(context, "Conexão com a internet não disponível", Toast.LENGTH_SHORT).show()
        }
    }


    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Nome: $nome")
        Text(text = "Telefone: $telefone")
        Text(text = "Biotipo: $biotipo")

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = intencao,
            onValueChange = { intencao = it },
            label = { Text("Intenção") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (isNetworkAvailable) {
                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, "Nome: $nome\nTelefone: $telefone\nBiotipo: $biotipo\nIntenção: $intencao")
                        type = "text/plain"
                    }
                    context.startActivity(Intent.createChooser(intent, "Compartilhar"))
                }
            },
            enabled = isNetworkAvailable,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Compartilhar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("MainActivity")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar para Menu")
        }
    }
}
