package com.example.projemi

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projemi.ui.theme.ProjeMiTheme
import kotlin.math.roundToInt
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjeMiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )


                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var buttonOffset by remember { mutableStateOf(Offset(0f, 0f)) }

    val screenWidthPx = with(LocalDensity.current) { 1080.dp.toPx() }
    val screenHeightPx = with(LocalDensity.current) { 1920.dp.toPx() }

    // Função para mover o botão aleatoriamente dentro da tela
    fun moveButtonRandomly() {
        val newX = Random.nextFloat() * (screenWidthPx - 1079) // Ajuste para a largura do botão
        val newY = Random.nextFloat() * (screenHeightPx - 1919) // Ajuste para a altura do botão
        buttonOffset = Offset(newX, newY)
    }

    // Função para abrir um link do YouTube
    val context = LocalContext.current
    fun openYouTubeLink() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/04854XqcfCY?si=fe22IHIqtxwM47hL"))
        context.startActivity(intent)
    }


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Red)
    ) { MyApp()
        var buttonPositionX by remember { mutableStateOf(0)
        }
        var buttonPositionY by remember { mutableStateOf(0)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Texto acima dos botões
            Text(
                text = "Cuzin hj?",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 50.sp
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterHorizontally)
            ) {

                Button(
                    onClick = { openYouTubeLink() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RectangleShape,
                    modifier = Modifier.wrapContentSize()
                ) {
                    Text(text = "Sim", color = Color.Black)
                }

                // Botão "Não" que se move ao ser clicado

                Box(
                    modifier = Modifier
                        .padding(start = 50.dp)
                        .offset(x = buttonOffset.x.dp, y = buttonOffset.y.dp)
                ) {
                    Box(
                        modifier = Modifier.wrapContentSize()
                            .offset(x = buttonPositionX.dp, y = buttonPositionY.dp)
                    ) {
                        Text(text = "Não", color = Color.Transparent)
                    }
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    var buttonPositionX by remember { mutableStateOf(183)
    }
    var buttonPositionY by remember { mutableStateOf(399)
    }
    var count by remember { mutableStateOf(0)
    }
    var pedding by remember { mutableStateOf(50)
    }

    val screenWidth = 1080f
    val screenHeight = 1920f


    fun randomPosition(): Offset {
        val x = Random.nextFloat() * (screenWidth - 100)
        val y = Random.nextFloat() * (screenHeight - 100)
        return Offset(x, y)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = { buttonPositionX = randomBetweenOneAnd1080()
                buttonPositionY = randomBetweenOneAnd1920()
                count ++
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RectangleShape,
            modifier = Modifier.wrapContentSize()
                .offset(x = buttonPositionX.dp, y = buttonPositionY.dp)
        ) {
            Text(text = "Não", color = Color.Black)
        }
    }
    }

fun randomBetweenOneAnd1920(): Int {
    return Random.nextInt(1, 720)
}
fun randomBetweenOneAnd1080(): Int {
    return Random.nextInt(1, 210) // O limite superior é exclusivo, então usamos 1921
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProjeMiTheme {
        Greeting("Android")
    }
}
