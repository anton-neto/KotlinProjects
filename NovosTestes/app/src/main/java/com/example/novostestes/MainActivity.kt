package com.example.novostestes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.novostestes.ui.theme.NovosTestesTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

object Routes {
    const val Tela01 = "tela01"
    const val Tela02 = "tela02"
}

const val CORRECT_PASSWORD = "0406"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NovosTestesTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHostComponent(navController = navController, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun NavHostComponent(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = Routes.Tela01, modifier = modifier) {
        composable(Routes.Tela01) { Tela01(navController = navController) }
        composable(Routes.Tela02) { Tela02(navController = navController) }
    }
}

@Composable
fun Tela01(navController: NavController) {
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Bem-vinda à Tela de bloqueio")
            Spacer(modifier = Modifier.height(16.dp))

            BasicTextField(
                value = password,
                onValueChange = { password = it },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .border(1.dp, Color.Gray)
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = Color.Red)
                Spacer(modifier = Modifier.height(16.dp))
            }

            Button(
                onClick = {
                    if (password == CORRECT_PASSWORD) {
                        navController.navigate(Routes.Tela02)
                    } else {
                        errorMessage = "Senha incorreta. Tente novamente."
                    }
                },
                modifier = Modifier.size(200.dp, 100.dp)
            ) {
                Text(text = "Ir para Tela 02")
            }
        }
    }
}

@Composable
fun Tela02(navController: NavController) {
    // Lista de charadas e respostas com opções
    val riddles = listOf(
        "O que é pequeno, redondo e tem um buraco no meio?" to listOf("bola", "maçã", "bola de tênis"),
        "Qual é o animal que anda com os pés na cabeça?" to listOf("cabelo", "pássaro", "cachorro"),
        "O que tem cabeça, mas não tem corpo?" to listOf("alho", "peixe", "carneiro"),
        "O que é que cai em pé e corre deitado?" to listOf("chuva", "roda", "vaca"),
        "O que é que se quebra e não cai?" to listOf("promessa", "vidro", "boneca")
    )

    // Estado atual da charada
    var currentRiddle by remember { mutableStateOf(riddles.random()) }
    var timeLeft by remember { mutableStateOf(60) } // Tempo inicial em segundos
    var message by remember { mutableStateOf("") }
    var timerRunning by remember { mutableStateOf(true) }
    var attemptsLeft by remember { mutableStateOf(3) } // Número de tentativas restantes
    var showRetryButton by remember { mutableStateOf(false) } // Mostrar o botão "O Dobro ou Nada"
    var currentAttempt by remember { mutableStateOf(0) } // Contador de tentativas

    val scope = rememberCoroutineScope()

    // Cronômetro
    LaunchedEffect(timerRunning) {
        while (timerRunning && timeLeft > 0) {
            delay(1000)
            timeLeft -= 1
        }
        if (timeLeft == 0 && timerRunning) {
            message = "A bomba explodiu!"
            timerRunning = false
        }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (message.isEmpty()) {
                Text(text = "Charada da Tela 02")
                Spacer(modifier = Modifier.height(16.dp))

                // Exibição do tempo restante
                Text(text = "Tempo restante: $timeLeft segundos")
                Spacer(modifier = Modifier.height(16.dp))

                // Exibição da charada atual
                Text(text = "Charada: ${currentRiddle.first}")
                Spacer(modifier = Modifier.height(16.dp))

                // Opções de resposta
                val answers = currentRiddle.second.shuffled() // Embaralha as opções para que não fiquem na mesma ordem
                answers.forEach { answer ->
                    Button(
                        onClick = {
                            if (answer == currentRiddle.second[0]) { // Verifica se a resposta está correta
                                if (currentAttempt < 2) {
                                    message = "Parabéns, você resolveu a charada!"
                                    showRetryButton = true
                                    timerRunning = false
                                } else {
                                    message = "Você venceu! Parabéns!"
                                    timerRunning = false
                                }
                            } else {
                                message = "Resposta incorreta. Tente novamente."
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Text(text = answer)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Botão "O Dobro ou Nada"
                if (showRetryButton) {
                    Button(
                        onClick = {
                            if (currentAttempt < 2) {
                                timeLeft = (timeLeft / 2).coerceAtLeast(10) // Diminui o tempo para a próxima charada
                                message = ""
                                timerRunning = true
                                currentAttempt += 1
                                currentRiddle = riddles.random() // Seleciona uma nova charada
                                showRetryButton = false
                            } else {
                                message = "Você venceu! Parabéns!"
                                timerRunning = false
                            }
                        },
                        modifier = Modifier.size(200.dp, 100.dp)
                    ) {
                        Text(text = "O Dobro ou Nada")
                    }
                }
            }

            // Exibição da mensagem de resultado
            if (message.isNotEmpty()) {
                Text(text = message, color = if (message.contains("explodiu")) Color.Red else Color.Green)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Row para posicionar os botões lado a lado
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Botão "Resetar Bomba"
                Button(
                    onClick = {
                        timeLeft = 60
                        message = ""
                        timerRunning = true
                        currentAttempt = 0
                        attemptsLeft = 3
                        currentRiddle = riddles.random()
                        showRetryButton = false
                    },
                    modifier = Modifier.size(200.dp, 100.dp)
                ) {
                    Text(text = "Resetar Bomba")
                }

                Spacer(modifier = Modifier.width(16.dp)) // Espaço entre os botões

                // Botão "Voltar para Tela 01"
                Button(
                    onClick = { navController.navigate(Routes.Tela01) },
                    modifier = Modifier.size(200.dp, 100.dp)
                ) {
                    Text(text = "Voltar para Tela de bloqueio")
                }
            }
        }
    }
}




