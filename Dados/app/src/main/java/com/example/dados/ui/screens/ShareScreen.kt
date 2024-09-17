package com.example.dados.ui.screens

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun ShareScreen(navController: NavController) {
    var intention by rememberSaveable { mutableStateOf("") }
    var userData by remember { mutableStateOf(UserData("", "", "")) } // Placeholder data

    val context = LocalContext.current

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Nome: ${userData.name}")
        Spacer(modifier = Modifier.height(8.dp))
        Text("Número de telefone: ${userData.phone}")
        Spacer(modifier = Modifier.height(8.dp))
        Text("Descrição do biotipo: ${userData.description}")
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = intention,
            onValueChange = { intention = it },
            label = { Text("Intenção") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_SUBJECT, "Dados Pessoais")
                    putExtra(Intent.EXTRA_TEXT, "Nome: ${userData.name}\nNúmero de telefone: ${userData.phone}\nDescrição do biotipo: ${userData.description}\nIntenção: $intention")
                }
                context.startActivity(Intent.createChooser(intent, "Compartilhe"))
            },
            enabled = intention.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Compartilhar")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("main") }) {
            Text("Voltar para Menu")
        }
    }
}
