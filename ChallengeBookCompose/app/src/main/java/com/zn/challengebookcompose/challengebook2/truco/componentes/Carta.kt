package com.zn.challengebookcompose.challengebook2.truco.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zn.challengebookcompose.ui.theme.background_color
import com.zn.challengebookcompose.ui.theme.card_background_color
import com.zn.challengebookcompose.ui.theme.color_hint
import com.zn.challengebookcompose.ui.theme.secondary_color

data class Carta(
    val id: Int,
    val contenido: String,
    var revelada: Boolean = false,
    var encontrada: Boolean = false
)

fun generarCartas(): List<Carta> {
    val simbolos = listOf("🎃", "🕸️", "🕷️", "🍬", "🍭", "🍫", "💀", "☠️", "👻", "👽", "👹", "🍪", "🍩", "🍒")
    val pares = simbolos + simbolos // duplicamos para hacer pares
    return pares
        .shuffled()
        .mapIndexed { index, simbolo -> Carta(id = index, contenido = simbolo) }
}

@Composable
fun CartaMemoria(carta: Carta, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(8.dp))
            .background(
                when {
                    carta.encontrada -> color_hint
                    carta.revelada -> secondary_color
                    else -> card_background_color
                }
            )
            .clickable(enabled = !carta.encontrada, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        if (carta.revelada || carta.encontrada) {
            Text(carta.contenido, fontSize = 28.sp)
        }
    }
}