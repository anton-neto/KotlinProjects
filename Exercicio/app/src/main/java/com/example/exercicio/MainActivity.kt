package com.example.exercicio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.exercicio.ui.theme.ExercicioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExercicioTheme {
                // Exemplo de uso do composable com parâmetros
                WelcomeScreen(
                    title = "Bem-vindo ao Aplicativo",
                    description = "Aqui você encontrará todas as informações sobre o nosso aplicativo e como usá-lo. Explore e aproveite a experiência!"
                )
            }
        }
    }
}

@Composable
fun WelcomeScreen(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    // Container principal para centralização
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp), // Espaçamento ao redor da tela
        contentAlignment = Alignment.Center // Centraliza o conteúdo vertical e horizontalmente
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), // Ajusta a altura da coluna conforme o conteúdo
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // Centraliza o conteúdo verticalmente
        ) {
            Text(
                text = title,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 8.dp) // Espaço entre título e descrição
            )
            Text(
                text = description,
                fontSize = 18.sp,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp) // Espaço ao redor da descrição
            )
        }
    }
}