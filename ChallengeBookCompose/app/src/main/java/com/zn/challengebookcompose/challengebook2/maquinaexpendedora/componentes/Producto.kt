package com.zn.challengebookcompose.challengebook2.maquinaexpendedora.componentes

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.zn.challengebookcompose.R
import kotlin.random.Random

data class Producto (
    val name: String,
    val price: Double,
    val stock: MutableState<Int> = mutableIntStateOf(0),
    val idImagen: Int
)


fun generarProductos(): List<Producto> {
    val botellaAgua = Producto("Botella de agua", 1200.00, mutableIntStateOf(Random.nextInt(1, 10)), R.drawable.botella_agua)
    val papasFritas = Producto("Papas Lays", 2300.00, mutableIntStateOf(Random.nextInt(1, 10)), R.drawable.papas)
    val monster = Producto("Monster", 4100.00, mutableIntStateOf(Random.nextInt(1, 10)), R.drawable.monster)
    val pepas = Producto("Pepas", 800.00, mutableIntStateOf(Random.nextInt(1, 10)), R.drawable.pepas)
    val cepita = Producto("Cepita", 600.00, mutableIntStateOf(Random.nextInt(1, 10)), R.drawable.cepita)
    val speed = Producto("Speed", 2600.00, mutableIntStateOf(Random.nextInt(1, 10)), R.drawable.speed)
    val oreo = Producto("Oreo", 1500.00, mutableIntStateOf(Random.nextInt(1, 10)), R.drawable.oreo)
    val gomitas = Producto("Gomitas", 500.00, mutableIntStateOf(Random.nextInt(1, 10)), R.drawable.gomitas)

    return listOf(botellaAgua, papasFritas, monster, pepas, cepita, speed, oreo, gomitas)
}

