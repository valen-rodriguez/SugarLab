package com.zn.challengebookcompose.poligono

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zn.challengebookcompose.MainActivity
import com.zn.challengebookcompose.R
import com.zn.challengebookcompose.carrera.CarreraActivity
import com.zn.challengebookcompose.poligono.componentes.BotonFigura
import com.zn.challengebookcompose.poligono.componentes.CustomTextField
import com.zn.challengebookcompose.ui.theme.background_color
import com.zn.challengebookcompose.ui.theme.card_background_color
import com.zn.challengebookcompose.ui.theme.componentesgenerales.BarraSuperior
import com.zn.challengebookcompose.ui.theme.componentesgenerales.BotonMenu
import com.zn.challengebookcompose.ui.theme.componentesgenerales.TextoNormal
import com.zn.challengebookcompose.ui.theme.secondary_color
import com.zn.challengebookcompose.ui.theme.white

class PoligonoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ViewContainer()
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Preview
    @Composable
    private fun ViewContainer() {
        val context = LocalContext.current
        Scaffold(
            topBar = { BarraSuperior("Poligono", card_background_color,onHomeClick = {
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            }) },
            content = {
                ContentPoligono(onCarrera = {
                    context.startActivity(Intent(context, CarreraActivity::class.java)
                    )
                }
                )
            }
        )
    }

    @Composable
    private fun ContentPoligono(
        onCarrera: () -> Unit
    ) {

        var botonSeleccionado by remember { mutableStateOf("square") }
        var baseLado by remember { mutableDoubleStateOf(0.00) }
        var altura by remember { mutableDoubleStateOf(0.00) }
        var areaPoligonoString by remember { mutableStateOf("") }


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(background_color)
                .padding(10.dp, top = 100.dp, end = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {

                TextoNormal("Calculemos el área de tu poligono", modifier = Modifier.padding(10.dp))

                Row(
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(background_color)
                        .padding(10.dp)
                ) {

                    //rectangulo
                    BotonFigura(
                        icon = painterResource(id = R.drawable.ic_rectangle),
                        activeBackgroundColor = secondary_color,
                        onClick = { botonSeleccionado = "rectangle" },
                        isToggled = botonSeleccionado == "rectangle",
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .clip(RoundedCornerShape(10.dp))

                    )
                    //triangulo
                    BotonFigura(
                        icon = painterResource(id = R.drawable.ic_triangle),
                        activeBackgroundColor = secondary_color,
                        isToggled = botonSeleccionado == "triangle",
                        onClick = { botonSeleccionado = "triangle" },
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )
                    //cuadrado11
                    BotonFigura(
                        icon = painterResource(id = R.drawable.ic_square),
                        activeBackgroundColor = secondary_color,
                        isToggled = botonSeleccionado == "square",
                        onClick = { botonSeleccionado = "square" },
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )
                }

                Text(
                    text = "Ingrese la base o el lado (cm)",
                    color = white,
                    fontFamily = FontFamily.Monospace,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 40.dp)
                )

                //
                CustomTextField(
                    value = baseLado.toString(),
                    onValueChange = { newValue ->
                        baseLado = (newValue.toDoubleOrNull() ?: 0) as Double
                    },
                    isEnabled = true,
                    label = "Base / Lado (cm)",
                    modifier = Modifier.padding(top = 20.dp)
                )


                Text(
                    text = "Ingrese la altura (cm)",
                    color = white,
                    fontFamily = FontFamily.Monospace,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 40.dp)
                )
                CustomTextField(
                    value = altura.toString(),
                    onValueChange = { newValue ->
                        altura = (newValue.toDoubleOrNull() ?: 0) as Double
                    },
                    isEnabled = botonSeleccionado != "square",
                    label = "Altura (cm)",
                    modifier = Modifier.padding(top = 20.dp)
                )

                if (botonSeleccionado == "square") altura = baseLado



                ElevatedButton(
                    onClick = {
                        areaPoligonoString = areaPoligono(baseLado, altura, botonSeleccionado)
                    },
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = secondary_color,
                        contentColor = card_background_color
                    ),
                    modifier = Modifier
                        .padding(top = 20.dp, end = 15.dp, start = 15.dp)
                        .fillMaxWidth()
                ) {
                    Text("Comprobar")
                }

                Text(
                    text = areaPoligonoString,
                    color = white,
                    fontFamily = FontFamily.Monospace,
                    textAlign = TextAlign.Center,
                    fontSize = 26.sp,
                    modifier = Modifier.padding(top = 20.dp)
                )

                BotonMenu(
                    texto = "Siguiente Ejercicio",
                    color = card_background_color,
                    onClick = onCarrera,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 40.dp),
                )
            }
        }
    }

    fun areaPoligono(baseLado: Double, altura: Double, botonSeleccionado: String): String {

        var areaPoligono = 0.00

        when (botonSeleccionado) {
            "square" -> areaPoligono = baseLado * baseLado
            "rectangle" -> areaPoligono = baseLado * altura
            "triangle" -> areaPoligono = baseLado * (altura / 2)
        }

        return if (areaPoligono == 0.00) "Ningun valor puede ser 0" else "El area de tu poligono es $areaPoligono"
    }
}