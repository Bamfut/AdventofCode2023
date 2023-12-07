package day4

import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("src/main/kotlin/day4/1.txt").inputStream()
    val lineList = mutableListOf<String>()
    val cards:MutableMap<Int, Card> = mutableMapOf()

    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    lineList.forEach {it ->

        val scratchNumbers = it.split(":")[1]
        val numbers = scratchNumbers.split("|")
        val winningNumbers = numbers[0].split(" ").filter { it != "" }
        val ownNumbers = numbers[1].split(" ").filter { it != "" }

        cards[it.split(":")[0].split(" ").last().toInt()] = Card(winningNumbers, ownNumbers, 1)
    }

    cards.forEach { (key, value) ->

        var points = 0

        value.winningNumbers.forEach { winningNumber ->
            value.ownNumbers.forEach { ownNumber ->
                if (winningNumber == ownNumber) {
                    points++
                }
            }
        }

        for (i in 1..points) {
            cards[key+i]?.amountOfCards = cards[key+i]?.amountOfCards!! + (1 * value.amountOfCards)
        }
    }

    var totalScratchcards = 0
    cards.forEach { (key, value) ->
        totalScratchcards += value.amountOfCards
    }

    println(totalScratchcards)
}
