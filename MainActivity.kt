package com.example.jogodavelha

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF101827)
                ) {
                    JogoDaVelhaScreen()
                }
            }
        }
    }
}

@Composable
fun JogoDaVelhaScreen() {
    var board by remember { mutableStateOf(List(9) { "" }) }
    var jogadorAtual by remember { mutableStateOf("X") }
    var vencedor by remember { mutableStateOf<String?>(null) }
    var empate by remember { mutableStateOf(false) }
    var pontosX by remember { mutableIntStateOf(0) }
    var pontosO by remember { mutableIntStateOf(0) }

    fun reiniciarRodada() {
        board = List(9) { "" }
        jogadorAtual = "X"
        vencedor = null
        empate = false
    }

    fun reiniciarPlacar() {
        pontosX = 0
        pontosO = 0
        reiniciarRodada()
    }

    fun jogar(posicao: Int) {
        if (board[posicao].isNotEmpty() || vencedor != null || empate) return

        board = board.toMutableList().also { it[posicao] = jogadorAtual }

        val ganhador = verificarVencedor(board)
        when {
            ganhador != null -> {
                vencedor = ganhador
                if (ganhador == "X") pontosX++ else pontosO++
            }
            board.all { it.isNotEmpty() } -> {
                empate = true
            }
            else -> {
                jogadorAtual = if (jogadorAtual == "X") "O" else "X"
            }
        }
    }

    val mensagem = when {
        vencedor != null -> "Jogador $vencedor venceu!"
        empate -> "Deu velha!"
        else -> "Vez do jogador $jogadorAtual"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Jogo da Velha",
            color = Color.White,
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "X: $pontosX   |   O: $pontosO",
            color = Color(0xFFCBD5E1),
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = mensagem,
            color = Color(0xFF38BDF8),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(28.dp))

        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            for (linha in 0..2) {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    for (coluna in 0..2) {
                        val index = linha * 3 + coluna
                        CasaTabuleiro(
                            valor = board[index],
                            onClick = { jogar(index) }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = { reiniciarRodada() }
            ) {
                Text("Nova rodada")
            }

            Button(
                modifier = Modifier.weight(1f),
                onClick = { reiniciarPlacar() }
            ) {
                Text("Zerar placar")
            }
        }
    }
}

@Composable
fun CasaTabuleiro(
    valor: String,
    onClick: () -> Unit
) {
    val corTexto = when (valor) {
        "X" -> Color(0xFFF87171)
        "O" -> Color(0xFF60A5FA)
        else -> Color.Transparent
    }

    Box(
        modifier = Modifier
            .size(92.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(Color(0xFF1E293B))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = valor,
            color = corTexto,
            fontSize = 44.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

fun verificarVencedor(board: List<String>): String? {
    val combinacoesVencedoras = listOf(
        listOf(0, 1, 2),
        listOf(3, 4, 5),
        listOf(6, 7, 8),
        listOf(0, 3, 6),
        listOf(1, 4, 7),
        listOf(2, 5, 8),
        listOf(0, 4, 8),
        listOf(2, 4, 6)
    )

    for (combinacao in combinacoesVencedoras) {
        val a = combinacao[0]
        val b = combinacao[1]
        val c = combinacao[2]

        if (board[a].isNotEmpty() && board[a] == board[b] && board[b] == board[c]) {
            return board[a]
        }
    }

    return null
}
