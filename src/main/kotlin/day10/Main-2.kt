package day10

import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("src/main/kotlin/day10/1.txt").inputStream()
    var lineList = mutableListOf<String>()

    var currentLine = 54
    var currentIndex = 15
    var nextLine = 54
    var nextIndex = 14
    var listOfPipes = mutableListOf<String>()
    var enclosedTiles = 0

    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    var i = 0
    while (true){
        // println("Current : ${lineList[nextLine][nextIndex]}")
        listOfPipes.add(lineList[nextLine][nextIndex].toString())
        when (lineList[nextLine][nextIndex]) {
            '|' -> {
                if (currentLine > nextLine) {
                    currentLine = nextLine
                    currentIndex = nextIndex

                    nextLine = currentLine - 1
                } else if (currentLine < nextLine) {
                    currentLine = nextLine
                    currentIndex = nextIndex

                    nextLine = currentLine + 1
                }
            }
            '-' -> {
                if (currentIndex > nextIndex) {
                    currentLine = nextLine
                    currentIndex = nextIndex

                    nextIndex = currentIndex - 1
                } else if (currentIndex < nextIndex) {
                    currentLine = nextLine
                    currentIndex = nextIndex

                    nextIndex = currentIndex + 1
                }
            }
            'L' -> {
                if (currentLine < nextLine) {
                    currentLine = nextLine
                    currentIndex = nextIndex

                    nextIndex = currentIndex + 1
                } else if (currentLine == nextLine) {
                    currentLine = nextLine
                    currentIndex = nextIndex

                    nextLine = currentLine - 1
                }
            }
            'J' -> {
                if (currentLine < nextLine) {
                    currentLine = nextLine
                    currentIndex = nextIndex

                    nextIndex = currentIndex - 1
                } else if (currentLine == nextLine) {
                    currentLine = nextLine
                    currentIndex = nextIndex

                    nextLine = currentLine - 1
                }
            }
            '7' -> {
                if (currentLine > nextLine) {
                    currentLine = nextLine
                    currentIndex = nextIndex

                    nextIndex = currentIndex - 1
                } else if (currentLine == nextLine) {
                    currentLine = nextLine
                    currentIndex = nextIndex

                    nextLine = currentLine + 1
                }
            }
            'F' -> {
                if (currentLine > nextLine) {
                    currentLine = nextLine
                    currentIndex = nextIndex

                    nextIndex = currentIndex + 1
                } else if (currentLine == nextLine) {
                    currentLine = nextLine
                    currentIndex = nextIndex

                    nextLine = currentLine + 1
                }
            }
        }
        if (lineList[nextLine][nextIndex] == 'S') {
            break
        }
        i++
    }

    listOfPipes.add("S")

    println(listOfPipes)
    println((listOfPipes.size)/2)

    lineList = lineList.map { ".".repeat(it.length) }.toMutableList()

    currentLine = 54
    currentIndex = 15
    nextLine = 54
    nextIndex = 14
    listOfPipes.forEach { it ->
        lineList[nextLine] = lineList[nextLine].replaceRange(nextIndex, nextIndex+1, it)

        when (it) {
            "|" -> {
                if (currentLine > nextLine) {
                    currentLine = nextLine
                    currentIndex = nextIndex

                    nextLine = currentLine - 1
                } else if (currentLine < nextLine) {
                    currentLine = nextLine
                    currentIndex = nextIndex

                    nextLine = currentLine + 1
                }
            }
            "-" -> {
                if (currentIndex > nextIndex) {
                    currentLine = nextLine
                    currentIndex = nextIndex

                    nextIndex = currentIndex - 1
                } else if (currentIndex < nextIndex) {
                    currentLine = nextLine
                    currentIndex = nextIndex

                    nextIndex = currentIndex + 1
                }
            }
            "L" -> {
                if (currentLine < nextLine) {
                    currentLine = nextLine
                    currentIndex = nextIndex

                    nextIndex = currentIndex + 1
                } else if (currentLine == nextLine) {
                    currentLine = nextLine
                    currentIndex = nextIndex

                    nextLine = currentLine - 1
                }
            }
            "J" -> {
                if (currentLine < nextLine) {
                    currentLine = nextLine
                    currentIndex = nextIndex

                    nextIndex = currentIndex - 1
                } else if (currentLine == nextLine) {
                    currentLine = nextLine
                    currentIndex = nextIndex

                    nextLine = currentLine - 1
                }
            }
            "7" -> {
                if (currentLine > nextLine) {
                    currentLine = nextLine
                    currentIndex = nextIndex

                    nextIndex = currentIndex - 1
                } else if (currentLine == nextLine) {
                    currentLine = nextLine
                    currentIndex = nextIndex

                    nextLine = currentLine + 1
                }
            }
            "F" -> {
                if (currentLine > nextLine) {
                    currentLine = nextLine
                    currentIndex = nextIndex

                    nextIndex = currentIndex + 1
                } else if (currentLine == nextLine) {
                    currentLine = nextLine
                    currentIndex = nextIndex

                    nextLine = currentLine + 1
                }
            }
        }
    }

    lineList.forEach { println(it) }

    lineList.forEach { it ->
        var line = it.toCharArray()
        line.forEachIndexed { index, c ->
            if (c == '.'){
                var left =  line.copyOfRange(0, index+1).groupBy { it }
                var sumOfStuff = 0

                if (left['J'] != null) {
                    sumOfStuff += left['J']!!.size
                }
                if (left['|'] != null) {
                    sumOfStuff += left['|']!!.size
                }
                if (left['L'] != null) {
                    sumOfStuff += left['L']!!.size
                }
                if (left['S'] != null) {
                    sumOfStuff += left['S']!!.size
                }

                if (sumOfStuff % 2 != 0) {
                    enclosedTiles++
                }
            }
        }
    }
    println(enclosedTiles)
}