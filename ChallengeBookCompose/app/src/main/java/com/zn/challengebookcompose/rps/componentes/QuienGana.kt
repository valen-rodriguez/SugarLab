package com.zn.challengebookcompose.rps.componentes

fun quienGana(player1: String, player2: String): String {

    val jugador1 = "El ganador es el Jugador 1"
    val jugador2 = "El ganador es el Jugador 2"
    val empate = "Empate"
    val errorRPS = "Error"

    when (player1) {
        "R" ->
            return when (player2) {
                "P" -> {
                    jugador2
                }

                "S" -> {
                    jugador1
                }

                "R" -> {
                    empate
                }

                else -> {
                    errorRPS
                }
            }

        "P" -> return when (player2) {
            "P" -> {
                empate
            }

            "S" -> {
                jugador2
            }

            "R" -> {
                jugador1
            }

            else -> {
                errorRPS
            }
        }

        "S" -> return when (player2) {
            "P" -> {
                jugador1
            }

            "S" -> {
                empate
            }

            "R" -> {
                jugador2
            }

            else -> {
                errorRPS
            }
        }

    }

    return "Error"

}