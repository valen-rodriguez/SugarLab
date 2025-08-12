package com.zn.challengebookcompose.challengebook2.calculadora.componentes

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun SelectorArchivo(onArchivoSeleccionado: (List<String>) -> Unit) {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        uri?.let {
            val contenido = leerArchivo(it, context)
            onArchivoSeleccionado(contenido)
        }
    }

    Button(
        onClick = { launcher.launch(arrayOf("text/plain")) },
        modifier = Modifier
            .height(60.dp)
            .width(280.dp)
    ) {
        Text("Seleccionar archivo .txt")
    }
}

fun leerArchivo(uri: Uri, context: Context): List<String> {
    val lineas = mutableListOf<String>()
    try {
        context.contentResolver.openInputStream(uri)?.bufferedReader().use { reader ->
            reader?.forEachLine { linea ->
                val lineaTrim = linea.trim()
                if (lineaTrim.isNotEmpty()) {
                    lineas.add(lineaTrim)
                }
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return lineas
}