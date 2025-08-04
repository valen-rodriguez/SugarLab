package com.zn.challengebook.consola

fun main() {

    for (i in 1..100) {
        if (i % 3 != 0 && i % 5 != 0) {
            println(i)
        } else if (i % 3 == 0 && i % 5 == 0) {
            println("FizzBuzz")
        } else if (i % 3 == 0) {
            println("Fizz")
        } else {
            println("Buzz")
        }
    }
}