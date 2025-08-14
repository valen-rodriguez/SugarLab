package com.zn.challengebookcompose.challengebook2.pokemon.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonBatalla(
    val nombre: String,
    val tipo: Tipo,
    val ataque: Int,
    val defensa: Int,
    val imagenUrl: String
) : Parcelable

data class PokemonApi(
    val name: String,
    val url: String
)

