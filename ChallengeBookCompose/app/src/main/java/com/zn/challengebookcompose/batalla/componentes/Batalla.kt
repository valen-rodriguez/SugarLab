package com.zn.challengebookcompose.batalla.componentes

import com.zn.challengebookcompose.batalla.carta.Carta

fun batalla(primerMazo: List<Carta>, segundoMazo: List<Carta>): String{

    var dmgPrimerMazo = 0
    var hpPrimerMazo = 0

    var dmgSegundoMazo = 0
    var hpSegundoMazo = 0


    for (carta in primerMazo){
        dmgPrimerMazo += carta.dmg
        hpPrimerMazo += carta.hp
    }

    for (carta in segundoMazo){
        dmgSegundoMazo += carta.dmg
        hpSegundoMazo += carta.hp
    }


    val hpPrimerMazoDespues = hpPrimerMazo - dmgSegundoMazo
    val hpSegundoMazoDespues = hpSegundoMazo - dmgPrimerMazo


    return when {

        hpPrimerMazoDespues > hpSegundoMazoDespues -> {"¡El ganador es el Mazo del Jugador 1!\n\n" +
                "Mazo 1:\n" +
                "Daño: $dmgPrimerMazo\n" +
                "Vida: $hpPrimerMazo\n" +
                "Vida Despues de la pelea: $hpPrimerMazoDespues\n\n" +
                "Mazo 2:\n" +
                "Daño: $dmgSegundoMazo\n" +
                "Vida: $hpSegundoMazo\n\n" +
                "Vida Despues de la pelea: $hpSegundoMazoDespues"}

        hpSegundoMazoDespues > hpPrimerMazoDespues -> {"¡El ganador es el Mazo del Jugador 2!\n\n" +
                "Mazo 2:\n" +
                "Daño: $dmgSegundoMazo\n" +
                "Vida: $hpSegundoMazo\n" +
                "Vida Despues de la pelea: $hpSegundoMazoDespues\n\n" +
                "Mazo 1:\n" +
                "Daño: $dmgPrimerMazo\n" +
                "Vida: $hpPrimerMazo\n" +
                "Vida Despues de la pelea: $hpPrimerMazoDespues"}

        else -> {"Empate\n"+
                "Mazo 1:\n" +
                "Daño: $dmgPrimerMazo\n" +
                "Vida: $hpPrimerMazo\n" +
                "Vida Despues de la pelea: $hpPrimerMazoDespues\n\n" +
                "Mazo 2:\n" +
                "Daño: $dmgSegundoMazo\n" +
                "Vida: $hpSegundoMazo\n" +
                "Vida Despues de la pelea: $hpSegundoMazoDespues"}
    }


}