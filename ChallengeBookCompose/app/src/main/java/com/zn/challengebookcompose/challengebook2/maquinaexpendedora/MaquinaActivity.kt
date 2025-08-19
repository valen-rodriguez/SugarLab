package com.zn.challengebookcompose.challengebook2.maquinaexpendedora

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zn.challengebookcompose.challengebook2.Menu2Activity
import com.zn.challengebookcompose.challengebook2.maquinaexpendedora.componentes.Producto
import com.zn.challengebookcompose.challengebook2.maquinaexpendedora.componentes.ProductoMaquina
import com.zn.challengebookcompose.challengebook2.maquinaexpendedora.componentes.generarProductos
import com.zn.challengebookcompose.poligono.componentes.CustomTextField
import com.zn.challengebookcompose.ui.theme.background_color
import com.zn.challengebookcompose.ui.theme.card_background_color
import com.zn.challengebookcompose.ui.theme.componentesgenerales.BarraSuperior
import com.zn.challengebookcompose.ui.theme.secondary_color
import com.zn.challengebookcompose.ui.theme.white

class MaquinaActivity : ComponentActivity() {
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
            BarraSuperior("Maquina Expendedora", card_background_color, onHomeClick = {
                val intent = Intent(context, Menu2Activity::class.java)
                context.startActivity(intent)
            })
        },
        content = {
            ContentMaquinaExpendedora()
        }
    )
}

@Composable
fun ContentMaquinaExpendedora() {

    var productos by remember { mutableStateOf(generarProductos()) }
    var dinero by remember { mutableDoubleStateOf(0.00) }
    var dineroString by remember { mutableStateOf("") }
    var alertaDinero by remember { mutableStateOf(false) }
    var alertaStock by remember { mutableStateOf(false) }
    var alertaCompra by remember { mutableStateOf(false) }
    var dineroReal: Double?

    var productoSeleccionado by remember { mutableStateOf<Producto?>(null) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background_color)
            .padding(5.dp, top = 100.dp),
    ) {

        Text(
            text = "Dinero disponible: $$dinero",
            color = white,
            fontFamily = FontFamily.Monospace,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        Column(
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxSize()
                .background(background_color),
                    horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Ingrese su dinero disponible",
                color = white,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
            )

            CustomTextField(
                value = dineroString,
                onValueChange = { newValue ->
                    dineroString = newValue
                },
                isEnabled = true,
                label = "Ingrese dinero",
                modifier = Modifier.padding(horizontal = 35.dp, vertical = 5.dp)
            )

            ElevatedButton(
                onClick = {

                    dineroReal = dineroString.toDoubleOrNull()

                    if (dineroReal != null && dineroReal!! > 0.00) {
                        dinero = dineroReal!!
                    }
                },
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = secondary_color,
                    contentColor = card_background_color
                ),
                enabled = true,
                modifier = Modifier
                    .padding(15.dp)
                    .width(150.dp)
            ) {
                Text("Ingresar dinero")
            }



            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp)
            ) {

                items(productos.size) { index ->

                    val producto = productos[index]

                    ProductoMaquina(
                        producto = producto,
                        stock = producto.stock.value,
                        onClick = {
                            if (producto.stock.value > 0 && dinero >= producto.price) {

                                producto.stock.value -= 1
                                dinero -= producto.price
                                productoSeleccionado = producto
                                alertaCompra = true

                            } else {
                                if (producto.stock.value == 0) {
                                    alertaStock = true
                                } else if (dinero < producto.price){
                                    alertaDinero = true
                                }
                            }
                        }
                    )



                }
            }

            if (alertaStock) {
                AlertDialog(
                    onDismissRequest = { },
                    title = { Text(text = "Error") },
                    text = { Text("El producto no tiene stock") },
                    confirmButton = {
                        Button(onClick = { alertaStock = false }) {
                            Text("Aceptar")
                        }
                    }
                )
            }

            if (alertaDinero) {
                AlertDialog(
                    onDismissRequest = { },
                    title = { Text(text = "Error") },
                    text = { Text("Dinero insuficiente") },
                    confirmButton = {
                        Button(onClick = { alertaDinero = false }) {
                            Text("Aceptar")
                        }
                    }
                )
            }

            if (alertaCompra) {
                AlertDialog(
                    onDismissRequest = { alertaCompra = false },
                    title = { Text(text = "Compra exitosa!") },
                    text = { Text("Ha comprado ${productoSeleccionado?.name}, su nuevo saldo es:  $$dinero") },
                    confirmButton = {
                        Button(onClick = { alertaDinero = false }) {
                            Text("Aceptar")
                        }
                    }
                )
            }
        }
    }
}


