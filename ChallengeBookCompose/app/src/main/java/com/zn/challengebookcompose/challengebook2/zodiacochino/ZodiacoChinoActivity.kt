package com.zn.challengebookcompose.challengebook2.zodiacochino

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zn.challengebookcompose.R
import com.zn.challengebookcompose.challengebook2.Menu2Activity
import com.zn.challengebookcompose.challengebook2.zodiacochino.componentes.signoChino
import com.zn.challengebookcompose.poligono.componentes.CustomTextField
import com.zn.challengebookcompose.ui.theme.agua
import com.zn.challengebookcompose.ui.theme.background_color
import com.zn.challengebookcompose.ui.theme.card_background_color
import com.zn.challengebookcompose.ui.theme.componentesgenerales.BarraSuperior
import com.zn.challengebookcompose.ui.theme.fuego
import com.zn.challengebookcompose.ui.theme.madera
import com.zn.challengebookcompose.ui.theme.metal
import com.zn.challengebookcompose.ui.theme.secondary_color
import com.zn.challengebookcompose.ui.theme.tierra
import com.zn.challengebookcompose.ui.theme.white

class ZodiacoChinoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            ViewContainer()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview
private fun ViewContainer() {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            BarraSuperior("Sexagenario Chino", card_background_color, onHomeClick = {
                val intent = Intent(context, Menu2Activity::class.java)
                context.startActivity(intent)
            })
        },
        content = {
            ContentSexagenario()
        }
    )
}

@Composable
fun ContentSexagenario() {

    var mostrarImagen by remember { mutableIntStateOf(0) }
    var year by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var idImagen by remember { mutableIntStateOf(-1) }
    var colorNombre: Color by remember { mutableStateOf(Color.Black) }
    var resultText by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(background_color)
            .padding(10.dp, top = 80.dp, end = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {

            Image(
                painter = painterResource(R.drawable.signos),
                contentDescription = null,
                modifier = Modifier.size(300.dp)
            )

            Text(
                text = "Veamos que elemento y animal le corresponde",
                color = white,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
            )

            //text field creado en la actividad poligonos
            CustomTextField(
                value = year,
                onValueChange = { newValue ->
                    year = newValue
                    mostrarImagen = 0
                    errorMessage = null
                },
                isEnabled = true,
                label = "Ingrese su año",
                modifier = Modifier.padding(top = 20.dp)
            )



            ElevatedButton(onClick = {
                val yearInt = year.toIntOrNull()
                if (yearInt != null && yearInt >= 1900) {
                    mostrarImagen = 1
                    errorMessage = null // Borrar cualquier mensaje de error anterior

                    //se calcula que signo corresponde
                    val signo = signoChino(yearInt).split(",")

                    resultText = "${signo[0]} ${signo[1]}"
                    val indiceElemento = signo[2].trim().toInt()
                    val indiceAnimal = signo[3].trim().toInt()

                //en base a lo que tocó se elige una imagen y color
                when (indiceAnimal){
                    0 -> idImagen = R.drawable.ic_rat
                    1 -> idImagen = R.drawable.ic_ox
                    2 -> idImagen = R.drawable.ic_tiger
                    3 -> idImagen = R.drawable.ic_rabbit
                    4 -> idImagen = R.drawable.ic_dragon
                    5 -> idImagen = R.drawable.ic_snake
                    6 -> idImagen = R.drawable.ic_horse
                    7 -> idImagen = R.drawable.ic_goat
                    8 -> idImagen = R.drawable.ic_monkey
                    9 -> idImagen = R.drawable.ic_rooster
                    10 -> idImagen = R.drawable.ic_dog
                    11 -> idImagen = R.drawable.ic_pig
                }

                when (indiceElemento){
                    0 -> colorNombre = madera
                    1 -> colorNombre = fuego
                    2 -> colorNombre = tierra
                    3 -> colorNombre = metal
                    4 -> colorNombre = agua
                }
            } else {
                    mostrarImagen = 0
                    resultText = ""
                    idImagen = -1
                    colorNombre = Color.Black
                    errorMessage = "Por favor, ingrese un año válido (a partir de 1900)."
                }
        },
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = secondary_color,
                    contentColor = card_background_color
                ),
                modifier = Modifier.padding(top = 20.dp, end = 15.dp, start = 15.dp)
                    .fillMaxWidth()){
                Text("Comprobar")

            }

            errorMessage?.let {
                Text(
                    text = it,
                    color = Color.White,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }


            if (mostrarImagen != 0 && idImagen != -1) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = colorNombre),
                    modifier = Modifier.size(width = 200.dp, height = 200.dp)
                ){
                    Column(
                        modifier = Modifier.fillMaxSize().padding(5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Text(
                            text = resultText,
                            color = white,
                            fontFamily = FontFamily.Monospace,
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(10.dp)
                        )

                        Icon(
                            painter = painterResource(id = idImagen),
                            tint = white,
                            contentDescription = null,
                            modifier = Modifier.size(60.dp)
                        )
                    }
                }
            }
        }
    }
}
