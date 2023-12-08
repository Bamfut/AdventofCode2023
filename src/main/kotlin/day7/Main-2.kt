package day7

import java.io.File
import java.io.InputStream

fun main(args: Array<String>) {

    val inputStream: InputStream = File("src/main/kotlin/day7/1.txt").inputStream()
    val lineList = mutableListOf<String>()

    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    val handMap = mutableMapOf<String, Int>()
    var totalWinnings = 0
    val allCards = listOf("J", "2", "3", "4", "5", "6", "7", "8", "9", "T", "Q", "K", "A")

    lineList.forEach {
        handMap[it.split(" ")[0]] = it.split(" ")[1].toInt()
    }

    val sortedMap = handMap.toList()
        .sortedWith(compareBy({ getHand2(it.first).value }, { allCards.indexOf(it.first[0].toString()) }, { allCards.indexOf(it.first[1].toString()) }, { allCards.indexOf(it.first[2].toString()) }, { allCards.indexOf(it.first[3].toString()) }, { allCards.indexOf(it.first[4].toString())}))
        .toMap()

    sortedMap.forEach { (k, v) -> println("$k = $v ${getHand2(k)}") }

    for (i in 0..<sortedMap.size) {
        totalWinnings += sortedMap.toList()[i].second * (i + 1)
    }

    println("Total Winnings: $totalWinnings")
}

fun getHand2(stringHand: String): Hand {
    val stringHandNoJokers = stringHand.filter { it != 'J' }.groupBy { it }
    val amountOfJokers = stringHand.length - stringHandNoJokers.values.sumOf { it.size }

    if (stringHandNoJokers.values.any { it.size + amountOfJokers == 5 } || amountOfJokers == 5) {
        return Hand.FIVE_OF_A_KIND
    }

    if (stringHandNoJokers.values.any { it.size + amountOfJokers == 4 }) {
        return Hand.FOUR_OF_A_KIND
    }

    if (stringHandNoJokers.count() == 2) {
        return Hand.FULL_HOUSE
    }

    if (stringHandNoJokers.values.any { it.size + amountOfJokers == 3 }) {
        return Hand.THREE_OF_A_KIND
    }

    if (stringHand.groupBy { it }.values.filter { it.size == 2 }.size == 2) {
        return Hand.TWO_PAIR
    }

    if (stringHandNoJokers.values.any { it.size + amountOfJokers == 2 }) {
        return Hand.ONE_PAIR
    }

    return Hand.HIGH_CARD
}