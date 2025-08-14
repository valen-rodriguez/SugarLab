package com.zn.challengebookcompose.challengebook2.pokemon.pokeapi

import com.zn.challengebookcompose.challengebook2.pokemon.models.PokemonDetalle
import com.zn.challengebookcompose.challengebook2.pokemon.models.PokemonRespuesta
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface PokeapiService {

    @GET ("pokemon")
    fun obtenerListaPokemon(): Call<PokemonRespuesta?>?

    @GET
    fun obtenerPokemonDetalle(@Url url: String): Call<PokemonDetalle?>?
}
