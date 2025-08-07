package com.zn.challengebookcompose.anagrama.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zn.challengebookcompose.ui.theme.card_background_color
import com.zn.challengebookcompose.ui.theme.gray
import com.zn.challengebookcompose.ui.theme.secondary_color
import com.zn.challengebookcompose.ui.theme.white


@Composable
fun CardEsAnagrama() {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp)) //redondea columnas
            .background(card_background_color)
            .padding(0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = card_background_color),
            modifier = Modifier.size(width = 500.dp, height = 500.dp)
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp)) //redondea columnas
                    .background(card_background_color)
                    .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

            ) {

                Text(
                    text = "Un Anagrama consiste en formar una palabra reordenando" +
                            " TODAS las letras de otra palabra inicial.",
                    color = white,
                    fontFamily = FontFamily.Monospace,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                )

                Text(
                    text = "Ingrese la Primera Palabra",
                    color = white,
                    fontFamily = FontFamily.Monospace,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 20.dp)
                )

                var primeraPalabra by remember { mutableStateOf("") }
                var segundaPalabra by remember { mutableStateOf("") }

                //primera palabra ingresada por el usuario
                TextField(
                    value = primeraPalabra,
                    onValueChange = { primeraPalabra = it },
                    placeholder = { Text(text = "Primera Palabra") },
                    colors = TextFieldDefaults.colors(
                        // Colores en estado "no enfocado"
                        unfocusedContainerColor = card_background_color,
                        unfocusedIndicatorColor = white,
                        unfocusedLabelColor = white,
                        unfocusedTextColor = white,

                        // Colores en estado "enfocado"
                        focusedContainerColor = card_background_color,
                        focusedIndicatorColor = white,
                        focusedLabelColor = white,
                        focusedTextColor = white,

                        // Color del placeholder
                        unfocusedPlaceholderColor = gray,
                        focusedPlaceholderColor = gray,
                    ),
                    modifier = Modifier.padding(top = 20.dp)
                )

                Text(
                    text = "Ingrese la Segunda Palabra",
                    color = white,
                    fontFamily = FontFamily.Monospace,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 40.dp)
                )


                //segunda palabra ingresada por el usuario
                TextField(
                    value = segundaPalabra,
                    onValueChange = { segundaPalabra = it },
                    placeholder = { Text(text = "Segunda Palabra") },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = card_background_color,
                        unfocusedIndicatorColor = white,
                        unfocusedLabelColor = white,
                        unfocusedTextColor = white,
                        focusedContainerColor = card_background_color,
                        focusedIndicatorColor = white,
                        focusedLabelColor = white,
                        focusedTextColor = white,
                        unfocusedPlaceholderColor = gray,
                        focusedPlaceholderColor = gray,
                    ),
                    modifier = Modifier.padding(top = 10.dp)
                )

                var esAnagrama by remember { mutableStateOf("¿Es un anagrama?") }

                ElevatedButton(onClick = {
                    esAnagrama = esAnagrama(primeraPalabra, segundaPalabra)
                },
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = secondary_color,
                        contentColor = card_background_color
                    ),
                    modifier = Modifier.padding(top = 20.dp, end = 15.dp, start = 15.dp)
                        .fillMaxWidth()){
                    Text("Comprobar")

                }

                Text(
                    text = esAnagrama,
                    color = white,
                    fontFamily = FontFamily.Monospace,
                    textAlign = TextAlign.Center,
                    fontSize = 26.sp,
                    modifier = Modifier.padding(top = 20.dp)
                )

            }
        }
    }
}

fun esAnagrama(palabra1: String, palabra2: String): String{

    //si no ingreso nada no es anagrama
    if  (palabra1.isEmpty() || palabra2.isEmpty()){
        return "Alguna de las palabras no ha sido ingresada"
    }

    //si no tienen las mismas letras no es anagrama
    if (palabra1.length != palabra2.length){
        return "${palabra1.uppercase()} y ${palabra2.uppercase()} no son anagramas :("
    }

    //paso el string a array de chars y los ordeno
    val arrayPalabra1Lower = palabra1.lowercase().toCharArray().sortedArray()
    val arrayPalabra2Lower = palabra2.lowercase().toCharArray().sortedArray()


    //si el array es IGUAL es anagrama, sino no
    return if (arrayPalabra1Lower.contentEquals(arrayPalabra2Lower)){
        "¡¡¡${palabra1.uppercase()} y ${palabra2.uppercase()} son anagramas!!!"
    }else{
        "${palabra1.uppercase()} y ${palabra2.uppercase()} no son anagramas :("
    }
}
