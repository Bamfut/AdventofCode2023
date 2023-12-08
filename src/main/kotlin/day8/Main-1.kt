package day8

import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("src/main/kotlin/day8/1.txt").inputStream()
    val lineList = mutableListOf<String>()

    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    val instructions:MutableList<String> = lineList[0].split("").filter { it != "" }.toMutableList()
    val navigationMap:MutableMap<String, MutableList<String>> = mutableMapOf()
    var steps = 0
    var currentInstruction = 0
    var currentKey = "AAA"

    lineList.removeAt(0)
    lineList.removeAt(0)

    lineList.forEach { it ->
        navigationMap[it.split(" ")[0]] = it.split("(")[1].split(")")[0].replace(" ", "").split(",").toMutableList()
        println(navigationMap[it.split(" ")[0]])
    }

    while (true) {
        if (currentKey == "ZZZ")
            break

        steps++

        currentKey = when (instructions[currentInstruction]) {
            "L" -> navigationMap[currentKey]?.get(0).toString()
            else -> navigationMap[currentKey]?.get(1).toString()
        }

        currentInstruction++

        if (currentInstruction == instructions.size) {
            currentInstruction = 0
        }
    }
    println(steps)
}
