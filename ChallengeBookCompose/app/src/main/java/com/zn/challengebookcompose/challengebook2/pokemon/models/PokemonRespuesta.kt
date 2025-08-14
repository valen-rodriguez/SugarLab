package com.zn.challengebookcompose.challengebook2.pokemon.models

data class PokemonRespuesta(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonApi>
)

