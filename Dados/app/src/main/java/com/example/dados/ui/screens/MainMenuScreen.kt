package com.example.dados.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MainMenuScreen(navController: NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Button(
            onClick = { navController.navigate("data") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Dados")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { navController.navigate("share") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Compartilhamento")
        }
    }
}
