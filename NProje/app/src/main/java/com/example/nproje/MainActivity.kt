package com.example.nproje

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nproje.ui.theme.NProjeTheme
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NProjeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AnimatedFlower(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun AnimatedFlower(modifier: Modifier = Modifier) {
    var isAnimating by remember { mutableStateOf(true) }
    val animationProgress by animateDpAsState(
        targetValue = if (isAnimating) 45.dp else 100.dp, // Ajustei o valor máximo para ficar mais dentro do tamanho da tela
        animationSpec = tween(durationMillis = 2000)
    )

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            isAnimating = !isAnimating
        }
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        drawFlower(center = Offset(size.width / 2, size.height / 2), petalRadius = animationProgress.toPx())
    }
}

fun DrawScope.drawFlower(center: Offset, petalRadius: Float) {
    val petalCount = 8
    val petalWidth = 50f
    val petalHeight = 150f
    val centerSize = 70f
    val petalColor = Color(0xFFFFC107)
    val centerColor = Color(0xFFFF5722)
    val stemColor = Color(0xFF4CAF50)

    for (i in 0 until petalCount) {
        val angle = 2 * PI / petalCount * i
        val petalOffset = Offset(
            center.x + petalRadius * cos(angle).toFloat(),
            center.y + petalRadius * sin(angle).toFloat()
        )
        drawOval(
            color = petalColor,
            topLeft = Offset(petalOffset.x - petalWidth / 2, petalOffset.y - petalHeight / 2),
            size = androidx.compose.ui.geometry.Size(petalWidth, petalHeight),
            alpha = 0.8f
        )
    }

    drawCircle(
        color = centerColor,
        radius = centerSize,
        center = center
    )

    drawLine(
        color = stemColor,
        start = Offset(center.x, center.y + petalRadius + petalHeight / 2),
        end = Offset(center.x, size.height - 50f),
        strokeWidth = 40f
    )
}

@Preview(showBackground = true)
@Composable
fun AnimatedFlowerPreview() {
    NProjeTheme {
        AnimatedFlower()
    }
}
