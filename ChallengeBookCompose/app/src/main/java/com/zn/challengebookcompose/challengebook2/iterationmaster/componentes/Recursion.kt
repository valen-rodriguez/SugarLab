package com.zn.challengebookcompose.challengebook2.iterationmaster.componentes

fun contarRecursivo(i: Int): Int {

    if (i <= 100) {
        contarRecursivo(i + 1)
    }
}