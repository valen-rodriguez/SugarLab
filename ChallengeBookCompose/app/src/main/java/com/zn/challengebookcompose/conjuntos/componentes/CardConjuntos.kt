//package com.zn.challengebookcompose.conjuntos.componentes
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.ElevatedButton
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableIntStateOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.zn.challengebookcompose.R
//import com.zn.challengebookcompose.ui.theme.background_color
//import com.zn.challengebookcompose.ui.theme.card_background_color
//import com.zn.challengebookcompose.ui.theme.gray
//import com.zn.challengebookcompose.ui.theme.secondary_color
//import com.zn.challengebookcompose.ui.theme.white
//
//
//@Composable
//fun CardConjuntos() {
//    Column(
//        modifier = Modifier
//            .clip(RoundedCornerShape(16.dp)) //redondea columnas
//            .background(card_background_color)
//            .padding(0.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Card(
//            colors = CardDefaults.cardColors(containerColor = card_background_color),
//            modifier = Modifier.size(width = 500.dp, height = 500.dp)
//        ) {
//            Column(
//                modifier = Modifier
//                    .clip(RoundedCornerShape(16.dp)) //redondea columnas
//                    .background(card_background_color)
//                    .padding(16.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//
//            ) {
//
//                Text(
//                    text = "Encontremos los comunes (o diferentes) en tus arreglos!",
//                    color = white,
//                    fontFamily = FontFamily.Monospace,
//                    textAlign = TextAlign.Center,
//                    fontSize = 16.sp,
//                )
//
//
//
//                val primerArray = Arreglos(
//                    "Ingrese el Primer Arreglo separando todo con una coma"
//                )
//
//                val segundoArray = Arreglos(
//                    "Ingrese el Segundo Arreglo separando todo con una coma"
//                )
//
//                var isComun by remember { mutableIntStateOf(10) }
//                var resultado by remember { mutableStateOf("") }
//
//
//
//                Row(
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .background(card_background_color)
//                        .padding(20.dp)
//                ) {
//
//                    //Comunes
//                    BotonComunDiferente(
//                        icon = painterResource(R.drawable.ic_equal),
//                        modifier = Modifier
//                            .width(170.dp)
//                            .height(170.dp)
//                            .padding(10.dp)
//                            .clip(RoundedCornerShape(20.dp)),
//                        onClick = {
//                            resultado = encontrarComunes(
//                                primerArray,
//                                segundoArray
//                            ).toString() //guardo el array en una variable
//                            isComun = 1
//                        }
//                    )
//
//                    //Diferentes
//                    BotonComunDiferente(
//                        icon = painterResource(R.drawable.ic_not_equal),
//                        modifier = Modifier
//                            .width(170.dp)
//                            .height(170.dp)
//                            .padding(10.dp)
//                            .clip(RoundedCornerShape(20.dp)),
//                        onClick = {
//                            resultado = encontrarDiferentes(primerArray, segundoArray).toString() //guardo el array en una variable
//                            isComun = 0
//                        }
//                    )
//                }
//
//
//
//                // Lógica para mostrar el texto final
//                val textoFinal = when (isComun) {
//                    1 -> "Los comunes son: $resultado"
//                    0 -> "Los diferentes son: $resultado"
//                    else -> "Presione un botón para mostrar su resultado"
//                }
//
//                Text(
//                    text = textoFinal,
//                    color = white,
//                    fontFamily = FontFamily.Monospace,
//                    textAlign = TextAlign.Center,
//                    fontSize = 16.sp,
//                    modifier = Modifier.padding(top = 20.dp)
//                )
//
//            }
//        }
//    }
//}
