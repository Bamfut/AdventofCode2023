package day5

import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("src/main/kotlin/day5/1.txt").inputStream()
    val lineList = mutableListOf<String>()

    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    var seeds:MutableList<Long> = mutableListOf()
    val allMaps:MutableList<MutableList<MutableList<Long>>> = mutableListOf(mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf())
    var currentlyReading = -1

    for (i in 0..<lineList.size) {
        if (i == 0){
            seeds = lineList[i].split(":")[1].split(" ").filter { it != "" }.map { it.toLong() }.toMutableList()
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
                //println("added something")
            }
        }

    }

    val locations:MutableList<Long> = mutableListOf()
    seeds.forEach {  it ->
        var current = it
        for (i in 0..<allMaps.size) {
            for (j in 0..<allMaps[i].size) {
                if (current in allMaps[i][j][1]..allMaps[i][j][1] + allMaps[i][j][2]) {
                    current += (allMaps[i][j][0] - allMaps[i][j][1])
                    break
                }
            }
        }
        locations.add(current)
    }

    println(locations.min())
}
