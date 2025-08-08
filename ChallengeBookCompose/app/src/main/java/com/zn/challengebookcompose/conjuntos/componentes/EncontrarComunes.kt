package com.zn.challengebookcompose.conjuntos.componentes

fun encontrarComunes(primerArray: List<String>, segundoArray: List<String>): ArrayList<String>{

    val arrayComunes = ArrayList<String>()

    for(i in 0..primerArray.size-1){
        for (j in 0..segundoArray.size-1){
            if (primerArray[i] == (segundoArray[j])){
                if (!arrayComunes.contains(primerArray[i])){
                    arrayComunes.add(primerArray[i])
                }
            }
        }
    }
    return arrayComunes
}