package com.example.memoria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.memoria.ui.theme.MemoriaTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MemoriaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MemoryGameWithLevelsAndTimer(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MemoryGameWithLevelsAndTimer(modifier: Modifier = Modifier) {
    var level by remember { mutableStateOf(1) }
    var cards by remember { mutableStateOf(shuffleCards(level)) }
    var selectedIndices by remember { mutableStateOf(listOf<Int>()) }
    var matchedIndices by remember { mutableStateOf(setOf<Int>()) }
    var moves by remember { mutableStateOf(0) }
    var showAllCards by remember { mutableStateOf(true) }
    var timeLeft by remember { mutableStateOf(30) }
    var movesLimit by remember { mutableStateOf(calculateMovesLimit(level)) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(level) {
        showAllCards = true
        delay(3000)
        showAllCards = false
        timeLeft = 30
        moves = 0
    }

    LaunchedEffect(showAllCards) {
        if (!showAllCards) {
            while (timeLeft > 0 && matchedIndices.size < cards.size) {
                delay(1000)
                timeLeft -= 1
            }

            if (timeLeft == 0 && matchedIndices.size < cards.size) {
                resetLevel(level, scope) { newState ->
                    level = newState.level
                    cards = newState.cards
                    selectedIndices = newState.selectedIndices
                    matchedIndices = newState.matchedIndices
                    moves = newState.moves
                    showAllCards = newState.showAllCards
                    timeLeft = newState.timeLeft
                    movesLimit = newState.movesLimit
                }
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Linha no topo com nível e movimentos
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Nivel: $level",
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )

            Text(
                text = "Movimentos: $moves / $movesLimit",
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }

        // Tempo restante
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text(
                text = "Tempo restante:",
                fontSize = 24.sp,
                color = if (timeLeft <= 10) Color.Red else Color.White,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "$timeLeft segundos",
                fontSize = 36.sp,
                color = if (timeLeft <= 10) Color.Red else Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }

        val gridSize = calculateGridSize(level)
        val gridCards = cards.chunked(gridSize)

        Column(
            modifier = Modifier.wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            gridCards.forEachIndexed { rowIndex, row ->
                Row(
                    modifier = Modifier.wrapContentSize(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    row.forEachIndexed { colIndex, card ->
                        val cardIndex = rowIndex * gridSize + colIndex
                        val isMatched = matchedIndices.contains(cardIndex)
                        val isSelected = selectedIndices.contains(cardIndex)
                        val flipAnimation = remember { Animatable(0f) }

                        LaunchedEffect(isSelected || isMatched || showAllCards) {
                            if (isSelected || isMatched || showAllCards) {
                                flipAnimation.animateTo(
                                    targetValue = 1f,
                                    animationSpec = tween(durationMillis = 500)
                                )
                            } else {
                                flipAnimation.animateTo(
                                    targetValue = 0f,
                                    animationSpec = tween(durationMillis = 500)
                                )
                            }
                        }

                        MemoryCardWithAnimation(
                            value = if (isMatched || isSelected || showAllCards) card else "",
                            flipProgress = flipAnimation.value,
                            onClick = {
                                if (selectedIndices.size < 2 && !isMatched && !isSelected && !showAllCards && moves < movesLimit) {
                                    selectedIndices = selectedIndices + cardIndex

                                    if (selectedIndices.size == 2) {
                                        moves += 1
                                        val firstIndex = selectedIndices[0]
                                        val secondIndex = selectedIndices[1]

                                        if (cards[firstIndex] == cards[secondIndex]) {
                                            matchedIndices = matchedIndices + firstIndex + secondIndex
                                        }

                                        scope.launch {
                                            delay(1000)
                                            selectedIndices = emptyList()
                                        }
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }

        if (matchedIndices.size == cards.size) {
            Text(
                text = "Nivel completado!",
                fontSize = 24.sp,
                color = Color.Green,
                modifier = Modifier.padding(16.dp)
            )

            LaunchedEffect(Unit) {
                delay(2000)
                level += 1
                resetLevel(level, scope) { newState ->
                    level = newState.level
                    cards = newState.cards
                    selectedIndices = newState.selectedIndices
                    matchedIndices = newState.matchedIndices
                    moves = newState.moves
                    showAllCards = newState.showAllCards
                    timeLeft = newState.timeLeft
                    movesLimit = newState.movesLimit
                }
            }
        }
    }
}

@Composable
fun MemoryCardWithAnimation(value: String, flipProgress: Float, onClick: () -> Unit) {
    val backgroundColor = if (flipProgress > 0.5f) Color.Blue else Color.Gray

    Box(
        modifier = Modifier
            .size(80.dp)
            .background(backgroundColor, shape = RoundedCornerShape(12.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        if (flipProgress > 0.5f) {
            Text(
                text = value,
                fontSize = 24.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

data class GameState(
    val level: Int,
    val cards: List<String>,
    val selectedIndices: List<Int>,
    val matchedIndices: Set<Int>,
    val moves: Int,
    val showAllCards: Boolean,
    val timeLeft: Int,
    val movesLimit: Int
)

fun resetLevel(level: Int, scope: CoroutineScope, updateState: (GameState) -> Unit) {
    scope.launch {
        delay(2000)
        val newState = GameState(
            level = level,
            cards = shuffleCards(level),
            selectedIndices = listOf(),
            matchedIndices = setOf(),
            moves = 0,
            showAllCards = true,
            timeLeft = 30,
            movesLimit = calculateMovesLimit(level)
        )
        updateState(newState)

        delay(3000)
        updateState(newState.copy(showAllCards = false))
    }
}

fun calculateGridSize(level: Int): Int {
    return (2 + level)
}

fun calculateMovesLimit(level: Int): Int {
    return 10 + level * 2
}

fun shuffleCards(level: Int): List<String> {
    val numberOfPairs = level + 4
    val cardValues = (1..numberOfPairs).flatMap { listOf(it.toString(), it.toString()) }
    return cardValues.shuffled(Random(System.currentTimeMillis()))
}
