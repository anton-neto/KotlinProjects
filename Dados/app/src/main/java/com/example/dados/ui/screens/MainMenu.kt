package com.example.dados.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainMenu(onNavigateToData: () -> Unit, onNavigateToShare: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onNavigateToData) {
            Text("Dados")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onNavigateToShare) {
            Text("Compartilhamento")
        }
    }
}
