package day15

import java.io.File
import java.io.InputStream

fun main(){
    val inputStream: InputStream = File("src/main/kotlin/day15/input.txt").inputStream()
    val lineList = mutableListOf<String>()
    var totalValue = 0

    inputStream.bufferedReader().forEachLine { lineList.add(it) }
    val input = lineList[0].split(",")

    input.forEach { it ->
        var value = 0
        it.forEach { it2 ->
            value += it2.code
            value *= 17
            value = value.mod(256)
        }
        totalValue += value
    }

    println(totalValue)
}