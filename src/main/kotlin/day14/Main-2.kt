package day14

import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("src/main/kotlin/day14/input.txt").inputStream()
    var totalLoad = 0
    val grid:Grid = Grid(mutableListOf())
    val hashList = mutableListOf<String>()

    inputStream.bufferedReader().forEachLine {  it ->
        if (it.isEmpty()){
            val times = grid.copy().getCycleTime()

            for (i in 0..<times.first + (1000000000-times.first).mod(times.second)) {
                grid.rotateCycle()
            }
            grid.rotateClockwise()
        }
        grid.add(it.toCharArray())
    }
    val times = grid.copy().getCycleTime()

    for (i in 0..<times.first + (1000000000-times.first).mod(times.second)) {
        grid.rotateCycle()
    }
    grid.rotateClockwise()

    totalLoad += grid.calculateRightLoad()
    /*totalLoad += grid.rotateClockwise().rollRight().calculateRightLoad()

    println(totalLoad)*/

    /*grid.rotateCycle().rotateCycle().rotateCycle().rotateCycle().rotateCycle().rotateCycle()
    grid.rotateClockwise()

    println(grid.calculateRightLoad())*/

    //println((1000000000-3).mod(7))


    //grid.rotateCycle().rotateCycle().rotateCycle().printGrid()

    println(totalLoad)
}