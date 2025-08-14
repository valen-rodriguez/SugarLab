package com.zn.challengebookcompose.challengebook2.calculadora

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zn.challengebookcompose.challengebook2.Menu2Activity
import com.zn.challengebookcompose.challengebook2.calculadora.consola.calcular
import com.zn.challengebookcompose.ui.theme.background_color
import com.zn.challengebookcompose.ui.theme.card_background_color
import com.zn.challengebookcompose.ui.theme.componentesgenerales.BarraSuperior
import com.zn.challengebookcompose.ui.theme.secondary_color
import com.zn.challengebookcompose.ui.theme.white

class CalculadoraActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
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
            BarraSuperior("Calculadora", card_background_color, onHomeClick = {
                val intent = Intent(context, Menu2Activity::class.java)
                context.startActivity(intent)
            })
        },
        content = {
            ContentCalculadora(
//            onAnagrama = { context.startActivity(Intent(context, AnagramaActivity::class.java)) }
            )
        }
    )
}

@Composable
private fun ContentCalculadora() {

    var uriArchivoSeleccionado by remember { mutableStateOf<Uri?>(null) }
    var contenidoArchivo by remember { mutableStateOf<List<String>?>(null) }
    var resultado by remember { mutableStateOf("+1")}

    val context = LocalContext.current
    val contentResolver = context.contentResolver

    val pickFileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri: Uri? ->
            uriArchivoSeleccionado = uri
        }
    )

    LaunchedEffect(uriArchivoSeleccionado) {
        uriArchivoSeleccionado?.let { uri ->
            try {
                contentResolver.openInputStream(uri)?.use { inputStream ->
                    contenidoArchivo = inputStream.bufferedReader().use { it.readLines() }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                contenidoArchivo = null
            }
        }
    }



    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(background_color)
            .padding(10.dp, top = 100.dp, end = 10.dp)
    ) {
        item {

            Text(
                text = "Ingrese un archivo .txt para hacer calculos matemáticos",
                color = white,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 40.dp)
            )


            ElevatedButton(
                onClick = {
                    pickFileLauncher.launch(arrayOf("text/plain"))
                },
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = secondary_color,
                    contentColor = card_background_color
                ),
                modifier = Modifier
                    .padding(top = 20.dp, end = 15.dp, start = 15.dp, bottom = 20.dp)
                    .fillMaxWidth()
            ) {
                Text("Seleccionar archivo TXT")
            }


            val resultadoContenido = calcular(contenidoArchivo)



            resultado = when (resultadoContenido){
                null -> "Error en la lectura del archivo,\n revise si tiene incoherencias matemáticas como la división por 0"
                else -> "El resultado de las cuentas del archivo es $resultadoContenido"
            }


            Card(
                colors = CardDefaults.cardColors(containerColor = card_background_color),
                modifier = Modifier.padding(bottom = 20.dp)
            ) {
                Text(
                    text = resultado,
                    color = white,
                    fontFamily = FontFamily.Monospace,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 40.dp).height(80.dp)
                )
            }
        }
    }
}
