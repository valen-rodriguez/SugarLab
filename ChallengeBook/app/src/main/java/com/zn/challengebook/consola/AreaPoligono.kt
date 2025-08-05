package com.zn.challengebook.consola

fun main() {

    do {

        println("Ingrese 1 para Triángulo, 2 para Cuadrado, 3 para Rectángulo o 4 para salir del programa")

        var poligono: Int = readlnOrNull()?.toInt() ?: -1

        when (poligono) {
            in 1..3 -> {

                var base: Double
                var altura: Double

                do {
                    println("Ingrese la base del poligono en cm")
                    base = readlnOrNull()?.toDouble() ?: -1.00
                } while (base.toInt() == 0 && base > 0.00)
                do {
                    println("Ingrese la altura del poligono en cm")
                    altura = readlnOrNull()?.toDouble() ?: -1.00
                } while (altura.toInt() == 0 && altura > 0.00)
                println(calcularAreaPoligono(poligono, base, altura))
            }

            4 -> {}

            -1 -> {
                while (poligono == -1) {
                    println("Ingrese 1 para Triángulo, 2 para Cuadrado, 3 para Rectángulo")

                    poligono = readlnOrNull()?.toInt() ?: -1
                }
            }

            else -> while (poligono != 1 && poligono != 2 && poligono != 3) {
                println("Ingrese 1 para Triángulo, 2 para Cuadrado, 3 para Rectángulo")

                poligono = readlnOrNull()?.toInt() ?: -1
            }
        }
    } while (poligono != 4)
}

fun calcularAreaPoligono(poligono: Int, base: Double, altura: Double): String {

    var area: Double

    return when (poligono) {
        1 -> {
            area = base * (altura / 2)
            return "El area del triangulo es $area"
        }

        in 2..3 -> {
            area = base * altura
            if (poligono == 2) {
                return "El area del cuadrado es $area"
            } else {
                return "El area del rectangulo es $area"
            }
        }

        else -> {
            "Error"
        }
    }

}
