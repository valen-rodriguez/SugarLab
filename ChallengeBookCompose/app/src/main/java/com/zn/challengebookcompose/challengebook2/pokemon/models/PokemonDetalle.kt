package com.zn.challengebookcompose.challengebook2.pokemon.models

data class PokemonDetalle(
    val name: String,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>
)

data class Sprites(
    val front_default: String?
)

data class Stat(
    val base_stat: Int,
    val stat: StatName
)

data class StatName(
    val name: String
)

data class Type(
    val type: TypeName
)

data class TypeName(
    val name: String
)