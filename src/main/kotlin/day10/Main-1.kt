package day10

import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("src/main/kotlin/day10/1.txt").inputStream()
    val lineList = mutableListOf<String>()

    var currentLine = 54
    var currentIndex = 15
    var nextLine = 54
    var nextIndex = 14
    var listOfPipes = mutableListOf<String>()

    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    println(lineList[54][14])


    var i = 0
    while (true){
        println("Current : ${lineList[nextLine][nextIndex]}")
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

    println((listOfPipes.size+1)/2)
}