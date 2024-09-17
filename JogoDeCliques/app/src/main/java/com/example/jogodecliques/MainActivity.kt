package com.example.jogodecliques

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JogoScreen()
        }
    }
}

@Composable
fun JogoScreen() {
    var clickCount by remember { mutableStateOf(0) }
    var totalClicks by remember { mutableStateOf(Random.nextInt(1, 51)) }
    var gameState by remember { mutableStateOf(GameState.START) }

    val currentImage = getImageForState(gameState, clickCount, totalClicks)

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = currentImage, contentDescription = null, modifier = Modifier.size(200.dp))
        Spacer(modifier = Modifier.height(16.dp))

        when (gameState) {
            GameState.START, GameState.PLAYING -> {
                Button(onClick = {
                    clickCount++
                    if (clickCount >= totalClicks) {
                        gameState = GameState.WON
                    } else {
                        // Atualize a imagem com base no progresso
                    }
                }) {
                    Text("Clique aqui")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    gameState = GameState.ABANDONADO
                }) {
                    Text("Desistir")
                }
            }
            GameState.ABANDONADO -> {
                Text("Você desistiu. Novo jogo?")
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Button(onClick = {
                        clickCount = 0
                        totalClicks = Random.nextInt(1, 51)
                        gameState = GameState.START
                    }) {
                        Text("Sim")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        // Implementar lógica para sair ou retornar à tela inicial
                    }) {
                        Text("Não")
                    }
                }
            }
            GameState.WON -> {
                Text("Parabéns! Você venceu!")
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Button(onClick = {
                        clickCount = 0
                        totalClicks = Random.nextInt(1, 51)
                        gameState = GameState.START
                    }) {
                        Text("Jogar novamente")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        // Implementar lógica para sair ou retornar à tela inicial
                    }) {
                        Text("Sair")
                    }
                }
            }
        }
    }
}

@Composable
fun getImageForState(state: GameState, clickCount: Int, totalClicks: Int): Painter {
    return when (state) {
        GameState.START -> painterResource(id = R.drawable.imagem_inicial)
        GameState.PLAYING -> {
            val progress = clickCount / totalClicks.toFloat()
            when {
                progress < 0.33 -> painterResource(id = R.drawable.imagem_inicial)
                progress < 0.66 -> painterResource(id = R.drawable.imagem_mediana)
                progress < 1.0 -> painterResource(id = R.drawable.imagem_final)
                else -> painterResource(id = R.drawable.imagem_conquista)
            }
        }
        GameState.ABANDONADO -> painterResource(id = R.drawable.imagem_desistencia)
        GameState.WON -> painterResource(id = R.drawable.imagem_conquista)
    }
}

enum class GameState {
    START, PLAYING, ABANDONADO, WON
}
