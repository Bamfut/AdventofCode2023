package day16

import java.io.File
import java.io.InputStream
import kotlin.jvm.Throws

fun main() {
    val inputStream: InputStream = File("src/main/kotlin/day16/input.txt").inputStream()
    val lineList = mutableListOf<String>()
    var count = 0

    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    lineList.forEachIndexed { index, s ->
        val energizedGrid:MutableList<MutableList<MutableList<Direction>>> =
            MutableList(lineList.size){MutableList(lineList[0].length){mutableListOf<Direction>()}}

        lightBeam(lineList, energizedGrid, Direction.RIGHT, Pair(index, 0))
        Thread.sleep(100)

        var newCount = 0
        for (k in 0..<energizedGrid.size) {
            for (l in 0..<energizedGrid[k].size) {
                if (energizedGrid[k][l].isNotEmpty()) {
                    newCount++
                }
            }
        }

        if (newCount > count) {
            count = newCount
        }
    }

    lineList.forEachIndexed { index, s ->
        val energizedGrid:MutableList<MutableList<MutableList<Direction>>> =
            MutableList(lineList.size){MutableList(lineList[0].length){mutableListOf<Direction>()}}

        lightBeam(lineList, energizedGrid, Direction.LEFT, Pair(index, lineList[0].length - 1))
        Thread.sleep(100)

        var newCount = 0
        for (k in 0..<energizedGrid.size) {
            for (l in 0..<energizedGrid[k].size) {
                if (energizedGrid[k][l].isNotEmpty()) {
                    newCount++
                }
            }
        }

        if (newCount > count) {
            count = newCount
        }
    }

    lineList[0].forEachIndexed{ index, s ->
        val energizedGrid:MutableList<MutableList<MutableList<Direction>>> =
            MutableList(lineList.size){MutableList(lineList[0].length){mutableListOf<Direction>()}}

        lightBeam(lineList, energizedGrid, Direction.DOWN, Pair(0, index))
        Thread.sleep(100)

        var newCount = 0
        for (k in 0..<energizedGrid.size) {
            for (l in 0..<energizedGrid[k].size) {
                if (energizedGrid[k][l].isNotEmpty()) {
                    newCount++
                }
            }
        }

        if (newCount > count) {
            count = newCount
        }
    }

    lineList[0].forEachIndexed{ index, s ->
        val energizedGrid:MutableList<MutableList<MutableList<Direction>>> =
            MutableList(lineList.size){MutableList(lineList[0].length){mutableListOf<Direction>()}}

        lightBeam(lineList, energizedGrid, Direction.DOWN, Pair(lineList.size-1, index))
        Thread.sleep(100)

        var newCount = 0
        for (k in 0..<energizedGrid.size) {
            for (l in 0..<energizedGrid[k].size) {
                if (energizedGrid[k][l].isNotEmpty()) {
                    newCount++
                }
            }
        }

        if (newCount > count) {
            count = newCount
        }
    }


    // print the grid, if the tile has no directions, print a 0, else 1

    println(count)
}