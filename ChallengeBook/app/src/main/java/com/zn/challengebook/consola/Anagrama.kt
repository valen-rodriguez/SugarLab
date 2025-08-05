package com.zn.challengebook.consola

fun main() {

    println("Ingrese la primera palabra")
    val palabra1: String? = readLine()
    println("Ingrese la segunda palabra")
    val palabra2: String? = readLine()

    print(esAnagrama(palabra1, palabra2))


}

fun esAnagrama(palabra1: String?, palabra2: String?): Boolean{

    if (palabra1 == null || palabra2 == null) { return false }

    if (palabra1 == palabra2){ return false }


    if (palabra1.length != palabra2.length){ return false }

    val palabra1Lower = palabra1.lowercase()
    val palabra2Lower = palabra2.lowercase()

    val arrayPalabra1 = palabra1Lower.toCharArray().sortedArray()
    val arrayPalabra2 = palabra2Lower.toCharArray().sortedArray()

    return arrayPalabra1.contentEquals(arrayPalabra2)

}