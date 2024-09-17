package com.example.aula1.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun JogoDaVelha(modificador: Modifier = Modifier) {
    var tabuleiro by remember { mutableStateOf(List(9) { "" }) }
    var jogadorAtual by remember { mutableStateOf("X") }
    var vencedor by remember { mutableStateOf<String?>(null) }

    // Função para verificar o vencedor
    fun verificarVencedor(): String? {
        val combinacoes = listOf(
            listOf(0, 1, 2),
            listOf(3, 4, 5),
            listOf(6, 7, 8),
            listOf(0, 3, 6),
            listOf(1, 4, 7),
            listOf(2, 5, 8),
            listOf(0, 4, 8),
            listOf(2, 4, 6)
        )

        for (comb in combinacoes) {
            val (a, b, c) = comb
            if (tabuleiro[a] == tabuleiro[b] && tabuleiro[b] == tabuleiro[c] && tabuleiro[a].isNotEmpty()) {
                return tabuleiro[a]
            }
        }

        return if (tabuleiro.all { it.isNotEmpty() }) "Empate" else null
    }

    // Função para realizar uma jogada
    fun realizarJogada(indice: Int) {
        if (tabuleiro[indice].isEmpty() && vencedor == null) {
            val novoTabuleiro = tabuleiro.toMutableList().apply {
                this[indice] = jogadorAtual
            }
            tabuleiro = novoTabuleiro
            vencedor = verificarVencedor()
            if (vencedor == null) {
                jogadorAtual = if (jogadorAtual == "X") "O" else "X"
            }
        }
    }

    // Layout do jogo da velha
    Column(modifier = modificador.padding(16.dp)) {
        Text(
            text = when {
                vencedor != null -> "Vencedor: $vencedor"
                else -> "Jogador atual: $jogadorAtual"
            },
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Column {
            for (linha in 0..2) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    for (coluna in 0..2) {
                        val indice = linha * 3 + coluna
                        BotaoTabuleiro(
                            texto = tabuleiro[indice],
                            onClick = { realizarJogada(indice) },
                            jogadorAtual = jogadorAtual,
                            modificador = Modifier
                                .size(100.dp)
                                .background(MaterialTheme.colorScheme.surface)
                                .padding(8.dp)
                                .weight(1f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BotaoTabuleiro(texto: String, onClick: () -> Unit, jogadorAtual: String, modificador: Modifier = Modifier) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modificador
    ) {
        Text(
            texto,
            style = MaterialTheme.typography.titleLarge,
            color = when (texto) {
                "X" -> Color.Blue
                "O" -> Color.Red
                else -> MaterialTheme.colorScheme.onSurface
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun JogoDaVelhaPreview() {
    Aula1Theme {
        JogoDaVelha()
    }
}
