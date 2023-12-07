package day4

import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("src/main/kotlin/day4/1.txt").inputStream()
    val lineList = mutableListOf<String>()
    var points = 0

    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    lineList.forEach {it ->
        var cardPoints = 0

        val scratchNumbers = it.split(":")[1]
        val numbers = scratchNumbers.split("|")
        val winningNumbers = numbers[0].split(" ").filter { it != "" }
        val ownNumbers = numbers[1].split(" ").filter { it != "" }

        winningNumbers.forEach { winningNumber ->
            ownNumbers.forEach { ownNumber ->
                if (winningNumber == ownNumber) {
                    if (cardPoints == 0) {
                        cardPoints = 1
                    } else {
                        cardPoints *= 2
                    }
                }
            }
        }
        points += cardPoints
    }
    println(points)
}
