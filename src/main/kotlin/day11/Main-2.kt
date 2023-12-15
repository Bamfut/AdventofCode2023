package day11

import java.io.File
import java.io.InputStream
import kotlin.math.abs

fun main() {
    val inputStream: InputStream = File("src/main/kotlin/day11/1.txt").inputStream()
    var lineList = mutableListOf<String>()
    val galaxies:MutableMap<Int, MutableList<Int>> = mutableMapOf()
    var sumOfDistances:Long = 0
    val multiply = 1000000

    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    lineList = expandUniverse2(lineList)
    lineList = rotateList(lineList)
    lineList = expandUniverse2(lineList)
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

    // calculate the distance between al galaxies, if it crosses an 'X', add 10 to the distance
    for (i in 1..galaxies.size) {
        for (j in i+1..galaxies.size) {
            var currentDistance:Long = 0
            currentDistance += abs(galaxies[i]!![0] - galaxies[j]!![0]) + abs(galaxies[i]!![1] - galaxies[j]!![1])
            // check if there is an 'X' between the two galaxies, for every 'X' add 10 to the distance
            if (galaxies[i]!![0] < galaxies[j]!![0]){
                for (k in galaxies[i]!![0]..galaxies[j]!![0]){
                    if (lineList[k][0] == 'X') {
                        currentDistance += multiply-1
                    }
                }
            }
            else if (galaxies[i]!![0] > galaxies[j]!![0]){
                for (k in galaxies[j]!![0]..galaxies[i]!![0]){
                    if (lineList[k][0] == 'X') {
                        currentDistance += multiply-1
                    }
                }
            }

            if (galaxies[i]!![1] < galaxies[j]!![1]){
                for (k in galaxies[i]!![1]..galaxies[j]!![1]){
                    if (lineList[0][k] == 'X') {
                        currentDistance += multiply-1
                    }
                }
            }
            else if (galaxies[i]!![1] > galaxies[j]!![1]){
                for (k in galaxies[j]!![1]..galaxies[i]!![1]){
                    if (lineList[0][k] == 'X') {
                        currentDistance += multiply-1
                    }
                }
            }
            // println("Distance between galaxy $i and $j: $currentDisance")
            sumOfDistances += currentDistance
        }
    }

    // lineList.forEach { println(it) }

    println("Sum of distances: $sumOfDistances")
}

fun expandUniverse2(universe: MutableList<String>): MutableList<String> {
    var i = 0
    while (i < universe.size) {
        if (!universe[i].contains("#")){
            universe[i] = "X".repeat(universe[i].length)
        }
        i++
    }
    return universe
}