package com.zn.challengebook.consola

fun main() {

    var player1: String?
    var player2: String?

    do {
        println("Jugador 1: Ingrese R (piedra), P (Papel) o T (Tijera)")
        player1 = readlnOrNull()?.uppercase()

        //Si no es nignuna opcion valida pasa a valer null
        if (player1 != "R" && player1 != "P" && player1 != "S") {
            player1 = null
        }

        //si es null repite el pedido hasta que deje de serlo
    } while (player1 == null)

    do {
        println("Jugador 2: Ingrese R (piedra), P (Papel) o T (Tijera)")
        player2 = readlnOrNull()?.uppercase()

        //Si no es nignuna opcion valida pasa a valer null
        if (player2 != "R" && player2 != "P" && player2 != "S") {
            player2 = null
        }

        //si es null repite el pedido hasta que deje de serlo
    } while (player2 == null)

    print(quienGana(player1, player2))

}

private fun quienGana(player1: String, player2: String): String {

    val jugador1 = "El ganador es el Jugador 1"
    val jugador2 = "El ganador es el Jugador 2"
    val empate = "Empate"
    val errorRPS = "Error"

    when (player1) {
        "R" ->
            return when (player2) {
                "P" -> { jugador2 }
                "S" -> { jugador1 }
                "R" -> { empate }
                else -> { errorRPS }
            }

        "P" -> return when (player2){
            "P" -> {empate}
            "S" -> {jugador2}
            "R" -> {jugador1}
            else -> {errorRPS}
        }

        "S" -> return when (player2){
            "P" -> {jugador1}
            "S" -> {empate}
            "R" -> {jugador2}
            else -> {errorRPS}
        }

    }


    return "Error"

}