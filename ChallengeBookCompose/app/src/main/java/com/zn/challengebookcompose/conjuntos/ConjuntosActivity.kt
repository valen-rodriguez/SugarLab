package com.zn.challengebookcompose.conjuntos

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zn.challengebookcompose.MainActivity
//import com.zn.challengebookcompose.conjuntos.componentes.CardConjuntos
import com.zn.challengebookcompose.conjuntos.componentes.CardConjuntos1
import com.zn.challengebookcompose.conjuntos.componentes.encontrarComunes
import com.zn.challengebookcompose.conjuntos.componentes.encontrarDiferentes
import com.zn.challengebookcompose.rps.RpsActivity
import com.zn.challengebookcompose.ui.theme.background_color
import com.zn.challengebookcompose.ui.theme.card_background_color
import com.zn.challengebookcompose.ui.theme.componentesgenerales.BarraSuperior
import com.zn.challengebookcompose.ui.theme.componentesgenerales.BotonMenu

class ConjuntosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ViewContainer()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
private fun ViewContainer() {

    val context = LocalContext.current
    Scaffold(
        topBar = { BarraSuperior("Conjuntos", card_background_color,onHomeClick = {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }) },
        content = { ContentConjunto(
            onRps = { context.startActivity(Intent(context, RpsActivity::class.java)) }
        ) }
    )
}

@Composable
private fun ContentConjunto(
    onRps: () -> Unit
) {
    var isComun by remember { mutableIntStateOf(10) }
    var resultado by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(background_color)
            .padding(10.dp, top = 100.dp, end = 10.dp)
    ) {
        item {
            CardConjuntos1(
                resultado = resultado,
                isComun = isComun,
                onComunClick = { primerArray, segundoArray ->
                    resultado = encontrarComunes(primerArray, segundoArray).toString()
                    isComun = 1
                },
                onDiferenteClick = { primerArray, segundoArray ->
                    resultado = encontrarDiferentes(primerArray, segundoArray).toString()
                    isComun = 0
                }
            )

            BotonMenu(
                texto = "Siguiente Ejercicio",
                color = card_background_color,
                onClick = onRps,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 40.dp),
            )
        }
    }
}