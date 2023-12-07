package day5

import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("src/main/kotlin/day5/1.txt").inputStream()
    val lineList = mutableListOf<String>()

    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    val seeds:MutableList<MutableList<Long>> = mutableListOf()
    val allMaps:MutableList<MutableList<MutableList<Long>>> = mutableListOf(mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf())
    var currentlyReading = -1

    for (i in 0..<lineList.size) {
        if (i == 0){
            // seeds = lineList[i].split(":")[1].split(" ").filter { it != "" }.map { it.toLong() }.toMutableList()
            val seedNumbers = lineList[i].split(":")[1].split(" ").filter { it != "" }
            for (j in 0..<seedNumbers.count { it != "" } /2) {
                seeds.add(mutableListOf(seedNumbers[j*2].toLong(), seedNumbers[j*2+1].toLong()))
            }
            continue
        }

        // convert the above to a switch statement
        when {
            lineList[i].contains("seed-to-soil map:") -> {currentlyReading = 0; continue}
            lineList[i].contains("soil-to-fertilizer map:") -> {currentlyReading = 1; continue}
            lineList[i].contains("fertilizer-to-water map:") -> {currentlyReading = 2; continue}
            lineList[i].contains("water-to-light map:") -> {currentlyReading = 3; continue}
            lineList[i].contains("light-to-temperature map:") -> {currentlyReading = 4; continue}
            lineList[i].contains("temperature-to-humidity map:") -> {currentlyReading = 5; continue}
            lineList[i].contains("humidity-to-location map:") -> {currentlyReading = 6; continue}
        }

        if (currentlyReading != -1) {
            if (lineList[i].isNotBlank()){
                allMaps[currentlyReading].add(lineList[i].split(" ").filter { it != "" }.map { it.toLong() }.toMutableList())
            }
        }
    }

    var currentLocation:Long = 0

    here@ while (true) {
        //println(currentLocation)
        var current = currentLocation
        for (i in allMaps.size-1 downTo 0) {
            for (j in 0..<allMaps[i].size) {
                if (current in allMaps[i][j][0]..allMaps[i][j][0] + allMaps[i][j][2]) {
                    current += (allMaps[i][j][1] - allMaps[i][j][0])
                    break
                }
            }
        }
        for (i in 0..<seeds.size) {
            if (seeds[i][0] <= current && current <= seeds[i][0] + seeds[i][1]) {
                println("location is $currentLocation")
                break@here
            }
        }
        currentLocation++
    }

}
