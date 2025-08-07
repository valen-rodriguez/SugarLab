package com.zn.challengebookcompose.fizzbuzz.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zn.challengebookcompose.ui.theme.card_background_color
import com.zn.challengebookcompose.ui.theme.color_hint
import com.zn.challengebookcompose.ui.theme.track_active_color
import com.zn.challengebookcompose.ui.theme.track_inactive_color
import com.zn.challengebookcompose.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun RangeSlider1To100() {
    var sliderPosition by remember { mutableFloatStateOf(0f) }

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp)) //redondea columnas
            .background(card_background_color)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val valueSlider = sliderPosition.toInt()
        Text(
            text = "Número seleccionado:",
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Monospace,
            color = white,
            fontSize = 26.sp,
            modifier = Modifier.padding(top = 20.dp)
        )

        //muestra el numero en base a la posición del slider
        Text(
            text = valueSlider.toString(),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Monospace,
            color = white,
            fontSize = 40.sp,
            modifier = Modifier.padding(top = 10.dp)
        )

        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = 1f..100f,
            steps = 100,
            colors = SliderDefaults.colors(
                activeTrackColor = track_active_color,
                inactiveTrackColor = track_inactive_color,
                inactiveTickColor = track_inactive_color,
                activeTickColor = track_active_color
            ),
            thumb = {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .clip(RoundedCornerShape(32.dp))
                        .clip(CircleShape) // recorta la caja para que sea círculo
                        .background(color_hint)
                )
            },
            modifier = Modifier.padding(16.dp)
        )

        //fizz o buzz en base a si es o no multiplo de x numero
        val fizzOrBuzz = when {
            valueSlider % 15 == 0 -> "FizzBuzz"
            valueSlider % 3 == 0 -> "Fizz"
            valueSlider % 5 == 0 -> "Buzz"
            else -> "" //si no es multiplo no imprime nada
        }

        //muestra si es fizz o buzz
        Text(
            text = fizzOrBuzz,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Monospace,
            color = white,
            fontSize = 40.sp,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}