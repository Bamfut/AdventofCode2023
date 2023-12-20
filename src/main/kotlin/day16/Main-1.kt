package day16

import java.io.File
import java.io.InputStream

fun main(){
    val inputStream: InputStream = File("src/main/kotlin/day16/input.txt").inputStream()
    val lineList = mutableListOf<String>()
    var count = 0

    inputStream.bufferedReader().forEachLine { lineList.add(it) }
    val energizedGrid:MutableList<MutableList<MutableList<Direction>>> =
        MutableList(lineList.size){MutableList(lineList[0].length){mutableListOf<Direction>()}}

    lightBeam(lineList, energizedGrid, Direction.DOWN, Pair(0, 1))

    lightBeam(lineList, energizedGrid, Direction.DOWN, Pair(0, 1))
    // print the grid, if the tile has no directions, print a 0, else 1
    for (i in 0..<energizedGrid.size){
        for (j in 0..<energizedGrid[i].size){
            if (energizedGrid[i][j].isEmpty()){
                //print(0)
            } else {
                //print(1)
                count++
            }
        }
        //println()
    }
    println(count)
}

fun lightBeam(lineList: MutableList<String>, energizedGrid:MutableList<MutableList<MutableList<Direction>>>, direction:Direction , next:Pair<Int, Int>): MutableList<MutableList<MutableList<Direction>>>{
    var newNext = next
    var newDirection = direction

    // check if the index is out of bounds, if so, return the energized grid
    if (next.first < 0 || next.first >= lineList.size || next.second < 0 || next.second >= lineList[next.first].length){
        return energizedGrid
    }

    if (energizedGrid[next.first][next.second].contains(direction)){
        return energizedGrid
    }
    energizedGrid[next.first][next.second].add(direction)

    when (lineList[next.first][next.second]){
        '|' -> {
            if (direction == Direction.DOWN){
                newNext = Pair(next.first + 1, next.second)
            } else if (direction == Direction.UP){
                newNext = Pair(next.first - 1, next.second)
            } else if (direction == Direction.LEFT || direction == Direction.RIGHT){
                lightBeam(lineList, energizedGrid, Direction.UP, Pair(next.first - 1, next.second))

                lightBeam(lineList, energizedGrid, Direction.DOWN, Pair(next.first + 1, next.second))

                return energizedGrid
            }
        }
        '-' -> {
            if (direction == Direction.LEFT){
                newNext = Pair(next.first, next.second - 1)
            } else if (direction == Direction.RIGHT){
                newNext = Pair(next.first, next.second + 1)
            } else if (direction == Direction.UP || direction == Direction.DOWN){
                lightBeam(lineList, energizedGrid, Direction.LEFT, Pair(next.first, next.second - 1))

                lightBeam(lineList, energizedGrid, Direction.RIGHT, Pair(next.first, next.second + 1))

                return energizedGrid
            }
        }
        '\\' -> {
            if (direction == Direction.DOWN){
                newDirection = Direction.RIGHT
                newNext = Pair(next.first, next.second + 1)
            } else if (direction == Direction.UP){
                newDirection = Direction.LEFT
                newNext = Pair(next.first, next.second - 1)
            } else if (direction == Direction.LEFT){
                newDirection = Direction.UP
                newNext = Pair(next.first - 1, next.second)
            } else if (direction == Direction.RIGHT){
                newDirection = Direction.DOWN
                newNext = Pair(next.first + 1, next.second)
            }
        }
        '/' -> {
            if (direction == Direction.DOWN){
                newDirection = Direction.LEFT
                newNext = Pair(next.first, next.second - 1)
            } else if (direction == Direction.UP){
                newDirection = Direction.RIGHT
                newNext = Pair(next.first, next.second + 1)
            } else if (direction == Direction.LEFT){
                newDirection = Direction.DOWN
                newNext = Pair(next.first + 1, next.second)
            } else if (direction == Direction.RIGHT){
                newDirection = Direction.UP
                newNext = Pair(next.first - 1, next.second)
            }
        }
        else -> {
            if (direction == Direction.DOWN){
                newNext = Pair(next.first + 1, next.second)
            } else if (direction == Direction.UP){
                newNext = Pair(next.first - 1, next.second)
            } else if (direction == Direction.LEFT){
                newNext = Pair(next.first, next.second - 1)
            } else if (direction == Direction.RIGHT){
                newNext = Pair(next.first, next.second + 1)
            }
        }
    }

    return lightBeam(lineList, energizedGrid, newDirection, newNext)
}
enum class Direction(var value:Int){
    UP(1),
    DOWN(2),
    LEFT(3),
    RIGHT(4)
}