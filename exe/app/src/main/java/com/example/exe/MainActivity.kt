package com.example.exe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.exe.ui.theme.ExeTheme

class MainActivity : ComponentActivity() {
    private var isDadosPreenchidos by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExeTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHostContainer(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding),
                        onDadosPreenchidosChanged = { preenchidos -> isDadosPreenchidos = preenchidos }
                    )
                }
            }
        }
    }

    @Composable
    fun NavHostContainer(
        navController: NavHostController,
        modifier: Modifier = Modifier,
        onDadosPreenchidosChanged: (Boolean) -> Unit
    ) {
        NavHost(navController = navController, startDestination = "menu") {
            composable("menu") {
                MainMenu(navController, isDadosPreenchidos)
            }
            composable("dados") {
                DadosScreen(navController, onDadosPreenchidosChanged)
            }
            composable("compartilhamento/{nome}/{telefone}/{biotipo}") { backStackEntry ->
                val nome = backStackEntry.arguments?.getString("nome") ?: ""
                val telefone = backStackEntry.arguments?.getString("telefone") ?: ""
                val biotipo = backStackEntry.arguments?.getString("biotipo") ?: ""
                CompartilhamentoScreen(nome = nome, telefone = telefone, biotipo = biotipo, navController = navController)
            }
        }
    }

    @Composable
    fun MainMenu(navController: NavHostController, isDadosPreenchidos: Boolean) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { navController.navigate("dados") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Dados")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate("compartilhamento") },
                enabled = isDadosPreenchidos,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Compartilhamento")
            }
        }
    }
}
