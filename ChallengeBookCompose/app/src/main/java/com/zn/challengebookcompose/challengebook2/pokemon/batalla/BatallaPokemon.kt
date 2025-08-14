package com.zn.challengebookcompose.challengebook2.pokemon.batalla

import com.zn.challengebookcompose.challengebook2.pokemon.models.Efectividad
import com.zn.challengebookcompose.challengebook2.pokemon.models.PokemonBatalla
import com.zn.challengebookcompose.challengebook2.pokemon.models.Tipo

fun quienGana(pokemon1: PokemonBatalla?, pokemon2: PokemonBatalla?): String{

    var efectividadPokemon1Multiplicador: Double
    var efectividadPokemon1Txt: String

    when (pokemon1?.tipo?.getEfectividadContra(pokemon2?.tipo ?: Tipo.NORMAL)){
        Efectividad.MUY_EFICAZ -> {
            efectividadPokemon1Multiplicador = 2.0
            efectividadPokemon1Txt = "Muy eficaz"
        }

        Efectividad.NORMAL -> {
            efectividadPokemon1Multiplicador = 1.0
            efectividadPokemon1Txt = "Normal"
        }
        Efectividad.POCO_EFICAZ -> {
            efectividadPokemon1Multiplicador = 0.5
            efectividadPokemon1Txt = "Poco eficaz"
        }

        Efectividad.NULO -> {
            efectividadPokemon1Multiplicador = 0.0
            efectividadPokemon1Txt = "Nula"
        }

        null -> TODO()
    }

    var efectividadPokemon2Multiplicador: Double
    var efectividadPokemon2Txt: String

    when (pokemon2?.tipo?.getEfectividadContra(pokemon1.tipo)){
        Efectividad.MUY_EFICAZ -> {
            efectividadPokemon2Multiplicador = 2.0
            efectividadPokemon2Txt = "Muy eficaz"
        }

        Efectividad.NORMAL -> {
            efectividadPokemon2Multiplicador = 1.0
            efectividadPokemon2Txt = "Normal"
        }
        Efectividad.POCO_EFICAZ -> {
            efectividadPokemon2Multiplicador = 0.5
            efectividadPokemon2Txt = "Poco eficaz"
        }

        Efectividad.NULO -> {
            efectividadPokemon2Multiplicador = 0.0
            efectividadPokemon2Txt = "Nula"
        }

        null -> TODO()
    }

    val dmgPokemon1 = 50 * (pokemon1.ataque / pokemon2.defensa) * efectividadPokemon1Multiplicador
    val dmgPokemon2 = 50 * (pokemon2.ataque / pokemon1.defensa) * efectividadPokemon2Multiplicador


    return if (dmgPokemon1 > dmgPokemon2){
        "1, ${pokemon1.nombre}, $dmgPokemon1, $efectividadPokemon1Txt"
    }else if(dmgPokemon2 > dmgPokemon1){
        "2, ${pokemon2.nombre}, $dmgPokemon2, $efectividadPokemon2Txt"
    }else{
        "Empate técnico"
    }
}