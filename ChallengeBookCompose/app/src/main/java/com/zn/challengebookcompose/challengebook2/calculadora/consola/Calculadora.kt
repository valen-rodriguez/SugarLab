package com.zn.challengebookcompose.challengebook2.calculadora.consola

fun calcular(contenido: List<String>?): Double? {


    var resultado: Double?

    val calculadoraTxt = contenido



    resultado = calculadoraTxt?.get(0)?.toDoubleOrNull()
    if (resultado == null){
        println("Formato de archivo incorrecto, no se pudo resolver las operaciones")
        return null
    }

    for (i in 1 until (calculadoraTxt?.size?.minus(1) ?: 0) step 2){
        val nextNum = calculadoraTxt?.getOrNull(i + 1)?.toDoubleOrNull()
        val operador = calculadoraTxt?.get(i)

        if (nextNum == null){
            println("Formato de archivo incorrecto, no se pudo resolver las operaciones")
            return null
        }

        when (operador){
            "+" -> resultado += nextNum
            "-" -> resultado -= nextNum
            "*" -> resultado *= nextNum
            "/" -> {
                if (nextNum == 0.00){
                    "Error: Division por 0"
                    return null
                }else{
                    resultado /= nextNum
                }
            }
        }
    }

    return resultado
}