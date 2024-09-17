package com.example.resolucao_atividade_aula06

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.resolucao_atividade_aula06.ui.theme.Resolucao_Atividade_aula06Theme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Resolucao_Atividade_aula06Theme {

                val listaCadastros = listOf(
                    Cadastro(idCadastro = 1, nomeCadastro = "João Silva", foneCadastro = "1234-5678"),
                    Cadastro(idCadastro = 2, nomeCadastro = "Maria Oliveira", foneCadastro = "2345-6789"),
                    Cadastro(idCadastro = 3, nomeCadastro = "Pedro Santos", foneCadastro = "3456-7890"),
                    Cadastro(idCadastro = 4, nomeCadastro = "Ana Costa", foneCadastro = "4567-8901")
                )

                CardColumn(listaCadastros)
            }
        }
    }
}
//------------------
data class Cadastro (
    val idCadastro    : Int,
    val nomeCadastro  : String,
    val foneCadastro  : String
)
//-----------------
@Composable
// dividindo a tela em 4 box
fun CardColumn(cadastros : List<Cadastro>) {
    Column(
        modifier = Modifier.fillMaxSize() // Faz a Column ocupar toda a tela
    ) {
        Row(
            modifier = Modifier.fillMaxSize() // Faz o Row ocupar toda a tela
                .weight(1f) // Ocupa metade da altura da tela
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Yellow)
                    .fillMaxHeight()
            ) {
                Column(modifier = Modifier.fillMaxSize()
                    ,verticalArrangement = Arrangement.Center
                    , horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = cadastros[0].idCadastro.toString()
                        , color = Color.Black
                        , style = TextStyle(fontStyle = FontStyle.Normal
                            ,fontSize = 24.sp
                            ,fontWeight = FontWeight.Bold)
                    )
                    Text(text = cadastros[0].nomeCadastro
                        , color = Color.Black
                        , style = TextStyle(fontStyle = FontStyle.Normal
                            ,fontSize = 24.sp
                            ,fontWeight = FontWeight.Bold))
                    Text(text = cadastros[0].foneCadastro
                        , color = Color.Black
                        , style = TextStyle(fontStyle = FontStyle.Normal
                            ,fontSize = 24.sp
                            ,fontWeight = FontWeight.Bold))
                }
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Blue)
                    .fillMaxHeight()
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = cadastros[1].idCadastro.toString()
                        , color = Color.White
                        , style = TextStyle(fontStyle = FontStyle.Normal
                            ,fontSize = 24.sp
                            ,fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = cadastros[1].nomeCadastro
                        , color = Color.White
                        , style = TextStyle(fontStyle = FontStyle.Normal
                            ,fontSize = 24.sp
                            ,fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = cadastros[1].foneCadastro
                        , color = Color.White
                        , style = TextStyle(fontStyle = FontStyle.Normal
                            ,fontSize = 24.sp
                            ,fontWeight = FontWeight.Bold)
                    )
                }
            }
        }
        //--
        Row(
            modifier = Modifier.fillMaxSize()
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Blue)
                    .fillMaxHeight()
            ){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = cadastros[3].idCadastro.toString()
                        , color = Color.White
                        , style = TextStyle(fontStyle = FontStyle.Normal
                            ,fontSize = 24.sp
                            ,fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = cadastros[3].nomeCadastro
                        , color = Color.White
                        , style = TextStyle(fontStyle = FontStyle.Normal
                            ,fontSize = 24.sp
                            ,fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = cadastros[3].foneCadastro
                        , color = Color.White
                        , style = TextStyle(fontStyle = FontStyle.Normal
                            ,fontSize = 24.sp
                            ,fontWeight = FontWeight.Bold)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Yellow)
                    .fillMaxHeight()
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = cadastros[3].idCadastro.toString()
                        , color = Color.Black
                        , style = TextStyle(fontStyle = FontStyle.Normal
                            ,fontSize = 24.sp
                            ,fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = cadastros[3].nomeCadastro
                        , color = Color.Black
                        , style = TextStyle(fontStyle = FontStyle.Normal
                            ,fontSize = 24.sp
                            ,fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = cadastros[3].foneCadastro
                        , color = Color.Black
                        , style = TextStyle(fontStyle = FontStyle.Normal
                            ,fontSize = 24.sp
                            ,fontWeight = FontWeight.Bold)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Resolucao_Atividade_aula06Theme {
        val listaCadastros = listOf(
            Cadastro(idCadastro = 1, nomeCadastro = "João Silva", foneCadastro = "1234-5678"),
            Cadastro(idCadastro = 2, nomeCadastro = "Maria Oliveira", foneCadastro = "2345-6789"),
            Cadastro(idCadastro = 3, nomeCadastro = "Pedro Santos", foneCadastro = "3456-7890"),
            Cadastro(idCadastro = 4, nomeCadastro = "Ana Costa", foneCadastro = "4567-8901")
        )

        CardColumn(listaCadastros)
    }
}

}


}

@Composable
fun MyCardExample() {
    Card(
        modifier = Modifier.padding(16.dp),
        elevation = elevation(4.dp),
        shape = RoundedCornerShape(6.dp)
    )
