package com.zn.challengebookcompose.challengebook2.zodiacochino.componentes

fun signoChino (year: Int): String {

    val animales = listOf(
        "Rata", "Buey", "Tigre", "Conejo", "Dragón", "Serpiente",
        "Caballo", "Cabra", "Mono", "Gallo", "Perro", "Cerdo"
    )

    val elementos = listOf("Madera", "Fuego", "Tierra", "Metal", "Agua")

    val indiceAnimal = (year - 1900) % 12
    val indiceElemento = ((year - 1900) % 10) / 2

    return "${elementos[indiceElemento]}, ${animales[indiceAnimal]}, $indiceElemento, $indiceAnimal"

}