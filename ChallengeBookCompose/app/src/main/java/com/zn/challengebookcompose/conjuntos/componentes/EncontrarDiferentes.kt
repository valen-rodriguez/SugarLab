package com.zn.challengebookcompose.conjuntos.componentes

fun encontrarDiferentes(primerArray: List<String>, segundoArray: List<String>): ArrayList<String>{

    val arrayDiferentes = ArrayList<String>()


    for(elemento in primerArray){
        if (elemento !in segundoArray){
            arrayDiferentes.add(elemento)
        }
    }

    for(elemento in segundoArray){
        if (elemento !in primerArray){
            arrayDiferentes.add(elemento)
        }
    }

    return arrayDiferentes
}