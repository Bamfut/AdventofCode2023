package day14

import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("src/main/kotlin/day14/input.txt").inputStream()
    var totalLoad = 0
    val grid:Grid = Grid(mutableListOf())

    inputStream.bufferedReader().forEachLine {  it ->
        if (it.isEmpty()){
            totalLoad += grid.rotateClockwise().rollRight().calculateRightLoad()

            grid.clear()
        }
        grid.add(it.toCharArray())
    }
    totalLoad += grid.rotateClockwise().rollRight().calculateRightLoad()

    println(totalLoad)
}

class Grid(val grid: MutableList<CharArray>) {
    fun rotateClockwise() : Grid {
        val newGrid = mutableListOf<String>()
        for (i in 0..<grid.size) {
            var newLine = ""
            for (j in grid.size - 1 downTo 0) {
                newLine += grid[j][i]
            }
            newGrid.add(newLine)
        }
        grid.clear()
        for (line in newGrid) {
            grid.add(line.toCharArray())
        }

        return this
    }

    fun printGrid() {
        for (line in grid) {
            println(line)
        }
    }

    fun rollRight() : Grid {
        for (line in grid) {
            val indexOfLastRock: MutableList<Int> = mutableListOf()
            for (i in line.indices) {
                if (line[i] == '#') {
                    indexOfLastRock.add(i)
                }
            }
            indexOfLastRock.add(line.size)

            var currentIndex = 0
            for (i in indexOfLastRock.indices) {
                var amountOfRoundRocks = 0
                while (currentIndex < indexOfLastRock[i]) {
                    if (line[currentIndex] == 'O') {
                        amountOfRoundRocks++
                        line[currentIndex] = '.'
                    }
                    currentIndex++
                }

                for (j in indexOfLastRock[i] downTo 0) {
                    if (amountOfRoundRocks > 0){
                        line[j - 1] = 'O'
                        amountOfRoundRocks--
                    }
                }
            }
        }

        return this
    }

    fun calculateRightLoad() : Int {
        var load = 0
        for (line in grid) {
            for (i in line.indices) {
                if (line[i] == 'O') {
                    load += i + 1
                }
            }
        }
        return load
    }

    fun clear() {
        grid.clear()
    }

    fun add(line: CharArray) {
        grid.add(line)
    }

    fun getHash() : String {
        var hash = ""
        for (line in grid) {
            hash += line.joinToString("")
        }
        return hash
    }

    fun rotateCycle() : Grid {
        // north
        rotateClockwise().rollRight()
        // west
        rotateClockwise().rollRight()
        // south
        rotateClockwise().rollRight()
        // east
        rotateClockwise().rollRight()

        return this
    }

    fun getCycleTime() : Pair<Int, Int> {
        var cycleHash = ""
        val hashList = mutableListOf<String>()
        var cycleTime = 0
        var firstCycleTime = 0
        while (true) {
            val hash = getHash()
            if (hashList.contains(hash)) {
                firstCycleTime = cycleTime
                cycleHash = hash
                break
            }
            hashList.add(hash)
            cycleTime++
            rotateCycle()
        }

        cycleTime = 1
        while (true) {
            rotateCycle()
            if (cycleHash == getHash()) {
                break
            }
            cycleTime++
        }

        return Pair(firstCycleTime - cycleTime, cycleTime)
    }

    fun copy() : Grid {
        val newGrid = mutableListOf<CharArray>()
        for (line in grid) {
            newGrid.add(line.copyOf())
        }
        return Grid(newGrid)
    }
}