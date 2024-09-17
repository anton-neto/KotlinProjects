package com.example.exe1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.exe1.ui.theme.Exe1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Exe1Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MyApp(
                        "Antonio", "1234-5678",
                        "Gabreu", "2345-6789",
                        "Gusta", "3456-7890",
                        "Davs", "4567-8901"
                    )
                }
            }
        }
    }
}

@Composable
fun MyApp(
    nome1: String, telefone1: String,
    nome2: String, telefone2: String,
    nome3: String, telefone3: String,
    nome4: String, telefone4: String
) {
    val colors = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow)
    val infos = listOf(
        "$nome1\n$telefone1",
        "$nome2\n$telefone2",
        "$nome3\n$telefone3",
        "$nome4\n$telefone4"
    )

    Column(Modifier.fillMaxSize()) {
        Row(Modifier.weight(1f)) {
            Quadrant(
                color = colors[0],
                info = infos[0],
                modifier = Modifier.weight(1f)
            )
            Quadrant(
                color = colors[1],
                info = infos[1],
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            Quadrant(
                color = colors[2],
                info = infos[2],
                modifier = Modifier.weight(1f)
            )
            Quadrant(
                color = colors[3],
                info = infos[3],
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun Quadrant(color: Color, info: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = info,
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyApp() {
    Exe1Theme {
        MyApp(
            "Antonio", "1234-5678",
            "Gabreu", "2345-6789",
            "Gusta", "3456-7890",
            "Davs", "4567-8901"
        )
    }
}
