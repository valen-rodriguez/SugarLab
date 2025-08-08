package com.zn.challengebookcompose.consola

fun main(){

    var array1: Array<String> = arrayOf("1", "2", "3", "4", "5", "1", "9", "2", "3")
    var array2: Array<String> = arrayOf("2", "3", "8", "9", "5", "1", "2")
    var newArray = ArrayList<String>()


    for(i in 0..array1.size-1){
        for (j in 0..array2.size-1){
            if (array1[i] == (array2[j])){
                if (!newArray.contains(array1[i])){
                    newArray.add(array1[i])
                    print(array1[i])
                }
            }
        }
    }
}