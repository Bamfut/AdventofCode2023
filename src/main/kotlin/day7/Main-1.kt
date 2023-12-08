package day7

import java.io.File
import java.io.InputStream

fun main(args: Array<String>) {

    val inputStream: InputStream = File("src/main/kotlin/day7/1.txt").inputStream()
    val lineList = mutableListOf<String>()

    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    val handMap = mutableMapOf<String, Int>()
    var totalWinnings = 0
    val allCards = listOf("2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A")

    lineList.forEach {
        handMap[it.split(" ")[0]] = it.split(" ")[1].toInt()
    }

    val sortedMap = handMap.toList()
        .sortedWith(compareBy({ getHand(it.first).value }, { allCards.indexOf(it.first[0].toString()) }, { allCards.indexOf(it.first[1].toString()) }, { allCards.indexOf(it.first[2].toString()) }, { allCards.indexOf(it.first[3].toString()) }, { allCards.indexOf(it.first[4].toString())}))
        .toMap()

    for (i in 0..<sortedMap.size) {
        totalWinnings += sortedMap.toList()[i].second * (i + 1)
    }

    println("Total Winnings: $totalWinnings")
}

enum class Hand(val value: Int) {
    FIVE_OF_A_KIND(7),
    FOUR_OF_A_KIND(6),
    FULL_HOUSE(5),
    THREE_OF_A_KIND(4),
    TWO_PAIR(3),
    ONE_PAIR(2),
    HIGH_CARD(1)
}

fun getHand(stringHand: String): Hand {
    if (stringHand.groupBy { it }.values.any { it.size == 5 }) {
        return Hand.FIVE_OF_A_KIND
    }

    if (stringHand.groupBy { it }.values.any { it.size == 4 }) {
        return Hand.FOUR_OF_A_KIND
    }

    if (stringHand.groupBy { it }.values.any { it.size == 3 } && stringHand.groupBy { it }.values.any { it.size == 2 }) {
        return Hand.FULL_HOUSE
    }

    if (stringHand.groupBy { it }.values.any { it.size == 3 }) {
        return Hand.THREE_OF_A_KIND
    }

    if (stringHand.groupBy { it }.values.filter { it.size == 2 }.size == 2) {
        return Hand.TWO_PAIR
    }

    if (stringHand.groupBy { it }.values.any { it.size == 2 }) {
        return Hand.ONE_PAIR
    }

    return Hand.HIGH_CARD
}