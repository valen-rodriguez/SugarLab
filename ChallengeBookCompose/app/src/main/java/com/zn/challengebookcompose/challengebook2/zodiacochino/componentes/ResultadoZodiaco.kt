package com.zn.challengebookcompose.challengebook2.zodiacochino.componentes

fun signoChino (year: Int): String {

    val animales = listOf(
        "Rata", "Buey", "Tigre", "Conejo", "Dragón", "Serpiente",
        "Caballo", "Cabra", "Mono", "Gallo", "Perro", "Cerdo"
    )

    val elementos = listOf("Madera", "Fuego", "Tierra", "Metal", "Agua")

    val indiceAnimal = ((year - 1984) % 12 + 12) % 12
    val indiceElemento = (((year - 1984) % 10 + 10) % 10) / 2

    val animal = animales[(indiceAnimal + 12) % 12]
    val elemento = elementos[(indiceElemento + 5) % 5]

    return "${elemento}, $animal, $indiceElemento, $indiceAnimal"

}