package com.zn.challengebookcompose.challengebook2.maquinaexpendedora.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zn.challengebookcompose.ui.theme.card_background_color
import com.zn.challengebookcompose.ui.theme.secondary_color
import com.zn.challengebookcompose.ui.theme.white


@Composable
fun ProductoMaquina(producto: Producto, stock: Int, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(card_background_color)
            .width(200.dp)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = producto.idImagen),
            contentDescription = null,
            modifier = Modifier
                .height(100.dp)
        )

        Text(
            text = "${producto.name}\n$${producto.price}\nStock: $stock",
            color = white,
            fontFamily = FontFamily.Monospace,
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )

        ElevatedButton(
            onClick = { onClick() },
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = secondary_color,
                contentColor = card_background_color
            ),
            enabled = true,
            modifier = Modifier
                .padding(15.dp)
                .width(150.dp)
        ) {
            Text("Comprar")
        }
    }
}
