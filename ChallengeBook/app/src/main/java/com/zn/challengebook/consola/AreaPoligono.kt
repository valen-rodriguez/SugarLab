package com.zn.challengebook.consola

fun main() {

    do {

        println("Ingrese 1 para Triángulo, 2 para Cuadrado, 3 para Rectángulo o 4 para salir del programa")

        var poligono: Int = readlnOrNull()?.toInt() ?: -1

        when (poligono) {

            //cuadrado
            2 -> {
                var base: Double
                do {
                    println("Ingrese un lado del cuadrado en cm")
                    base = readlnOrNull()?.toDouble() ?: -1.00
                } while (base.toInt() == 0 && base > 0.00)

                println(calcularAreaPoligono(poligono, base, 1.00))
            }

            //triangulo y rectangulo
            1 and 3 -> {

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

            //salir
            4 -> {}

            //repite hasta que seleccione una opcion correcta
            -1 -> {
                while (poligono == -1) {
                    println("Ingrese 1 para Triángulo, 2 para Cuadrado, 3 para Rectángulo")

                    poligono = readlnOrNull()?.toInt() ?: -1
                }
            }

            //repite hasta que seleccione una opcion correcta
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

        2 -> {
            area = base * base
            return "El area del cuadrado es $area"
        }

        3 -> {
            area = base * altura
            return "El area del rectangulo es $area"
        }

        else -> {
            "Error"
        }
    }

}
