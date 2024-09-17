package com.example.quiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quiz.ui.theme.QuizTheme
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    QuizScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun QuizScreen(modifier: Modifier = Modifier) {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var errors by remember { mutableStateOf(0) }
    var quizFinished by remember { mutableStateOf(false) }

    val questions = listOf(
        Question("Qual é a capital do Brasil?", listOf("Brasília", "Rio de Janeiro", "São Paulo", "Salvador"), 0),
        Question("Qual é o maior planeta do sistema solar?", listOf("Júpiter", "Saturno", "Terra", "Marte"), 0),
        Question("Quem escreveu 'Dom Casmurro'?", listOf("Machado de Assis", "José de Alencar", "Clarice Lispector", "Guimarães Rosa"), 0)
    )

    if (currentQuestionIndex >= questions.size) {
        quizFinished = true
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.c),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        if (quizFinished) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(Color.Transparent),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally // Center content horizontally
            ) {
                Text(
                    text = "O Quiz Acabou!",
                    style = TextStyle(
                        fontSize = 36.sp, // Larger font size
                        fontWeight = FontWeight.Bold, // Make the font bold
                        color = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Sua Pontuação Final é:",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "$score",
                    style = TextStyle(
                        fontSize = 48.sp, // Larger font size for emphasis
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.padding(bottom = 24.dp)
                )
                Text(
                    text = "Quantidade de Erros:",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "$errors",
                    style = TextStyle(
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.error
                    )
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = {
                        // Reset the quiz
                        score = 0
                        errors = 0
                        currentQuestionIndex = 0
                        quizFinished = false
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Reiniciar Quiz")
                }
            }
        } else {
            val question = questions[currentQuestionIndex]
            val shuffledOptions = question.options.shuffled()
            val correctAnswerIndex = shuffledOptions.indexOf(question.options[question.correctAnswerIndex])

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(Color.Transparent),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally // Center content horizontally
            ) {
                Text(
                    text = question.text,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                shuffledOptions.forEachIndexed { index, option ->
                    Button(
                        onClick = {
                            if (index == correctAnswerIndex) {
                                score++
                            } else {
                                errors++
                            }
                            currentQuestionIndex++
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        Text(text = option)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Pontuação: $score",
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )

                Text(
                    text = "Erros: $errors",
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewQuizScreen() {
    QuizTheme {
        QuizScreen()
    }
}

data class Question(val text: String, val options: List<String>, val correctAnswerIndex: Int)
