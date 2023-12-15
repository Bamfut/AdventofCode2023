package day11

import java.io.File
import java.io.InputStream
import java.util.*
import kotlin.math.abs

fun main() {
    val inputStream: InputStream = File("src/main/kotlin/day11/1.txt").inputStream()
    var lineList = mutableListOf<String>()
    val galaxies:MutableMap<Int, MutableList<Int>> = mutableMapOf()
    var sumOfDistances = 0

    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    lineList = expandUniverse(lineList)
    lineList = rotateList(lineList)
    lineList = expandUniverse(lineList)
    lineList = rotateList(lineList)

    var galaxyNumber = 0
    for (i in lineList.indices) {
        for (j in lineList[i].indices) {
            if (lineList[i][j] == '#') {
                galaxyNumber++
                galaxies[galaxyNumber] = mutableListOf()
                galaxies[galaxyNumber]?.add(i)
                galaxies[galaxyNumber]?.add(j)
            }
        }
    }

    for (i in 1..galaxies.size) {
        for (j in i+1..galaxies.size) {
            val distance = abs(galaxies[i]!![0] - galaxies[j]!![0]) + abs(galaxies[i]!![1] - galaxies[j]!![1])
            sumOfDistances += distance
        }
    }

    println("Sum of distances: $sumOfDistances")
}

fun rotateList(lineList: MutableList<String>): MutableList<String> {
    val rotatedLineList = mutableListOf<String>()
    for (i in lineList[0].indices) {
        var rotatedLine = ""
        for (j in lineList.indices) {
            rotatedLine += lineList[j][i]
        }
        rotatedLineList.add(rotatedLine)
    }
    return rotatedLineList
}

fun expandUniverse(universe: MutableList<String>): MutableList<String> {
    var i = 0
    while (i < universe.size) {
        if (!universe[i].contains("#")){
            universe.add(i, ".".repeat(universe[i].length))
            i++
        }
        i++
    }
    return universe
}