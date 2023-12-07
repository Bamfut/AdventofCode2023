package day7

import java.io.File
import java.io.InputStream

fun main(args: Array<String>) {

    val inputStream: InputStream = File("src/main/kotlin/day7/1.txt").inputStream()
    val lineList = mutableListOf<String>()

    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    val handMap = mutableMapOf<String, Int>()

    lineList.forEach { it ->
        handMap[it.split(" ")[0]] = it.split(" ")[1].toInt()
    }

    /*lineList.forEach { it ->
        println(it.split(" ")[0].groupBy { it })
    }*/
}

enum class Hand(val value: Int) {
    FIVE_OF_A_KIND(7),
    FOUR_OF_A_KIND(6),
    FULL_HOUSE(5),
    THREE_OF_A_KIND(4),
    TWO_PAIR(3),
    ONE_PAIR(2),
    HIGH_CARD(1)
}