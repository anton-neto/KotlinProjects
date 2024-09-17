package com.example.apppedido

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PedidoNamoroScreen()
        }
    }
}

@Composable
fun PedidoNamoroScreen() {
    var showMessage by remember { mutableStateOf(false) }
    var drawHeart by remember { mutableStateOf(false) }
    var isSuccess by remember { mutableStateOf(false) }
    var showRemoveButton by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFB6C1), Color(0xFFFF69B4))
                )
            )
    ) {
        Text(
            text = "Você aceita namorar comigo?",
            color = Color.White,
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 200.dp) // Ajuste o padding para que o texto fique no centro
        )

        if (showMessage) {
            Text(
                text = "Pedido aceito! Eu te amo!",
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .align(Alignment.Center)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.Center)
            ) {

                if (showRemoveButton) {
                    RemoveButton(onClick = {
                        showRemoveButton = false

                    })
                }
                SuccessButton(onClick = {

                    showMessage = true
                    drawHeart = true
                })
            }
        }


        if (drawHeart) {
            DrawHeartWithText(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun RemoveButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFc0392b),
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(50),
        modifier = Modifier
            .size(width = 150.dp, height = 50.dp)
            .clip(RoundedCornerShape(50))
    ) {
        Text(
            text = "Remove",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
fun SuccessButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF27ae60),
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(50),
        modifier = Modifier
            .size(width = 150.dp, height = 50.dp)
            .clip(RoundedCornerShape(50))
    ) {
        Text(
            text = "Success",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
fun DrawHeartWithText(modifier: Modifier = Modifier) {
    val scale by animateFloatAsState(targetValue = 1.0f)

    Canvas(
        modifier = modifier
            .size(300.dp)
            .padding(16.dp)
    ) {
        val path = Path().apply {
            moveTo(size.width / 2, size.height / 3)
            cubicTo(
                size.width * 0.9f, size.height * 0.1f,
                size.width, size.height * 0.5f,
                size.width / 2, size.height
            )
            cubicTo(
                0f, size.height * 0.5f,
                size.width * 0.1f, size.height * 0.1f,
                size.width / 2, size.height / 3
            )
        }


        drawPath(
            path = path,
            color = Color.Red,
            alpha = scale
        )


        drawContext.canvas.nativeCanvas.drawText(
            "Eu te amo, meu amor",
            size.width / 2,
            size.height / 1.7f,
            android.graphics.Paint().apply {
                textAlign = android.graphics.Paint.Align.CENTER
                textSize = 40f
                color = android.graphics.Color.WHITE
                isFakeBoldText = true
            }
        )
    }
}
