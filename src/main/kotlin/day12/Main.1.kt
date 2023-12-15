package day12

import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("src/main/kotlin/day12/input.txt").inputStream()
    val lineList = mutableListOf<String>()
    var possibilities = 0

    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    lineList.forEach { line ->
        val split = line.split(" ")
        val key = "." + split[0] + "."
        val values = split[1].split(",").map { it.toInt() }.toMutableList()

        possibilities += getPossibilities(key, values, 0)
    }

    println("Possibilities: $possibilities")
}

fun getPossibilities(key: String, numbers: MutableList<Int>, possibilities: Int): Int {
    val newNumbers = numbers.toMutableList()
    var newPossibilities = possibilities
    var newKey: String

    if (key.contains('?')){
        newKey = key.replaceFirst('?', '#')

        newPossibilities += getPossibilities(newKey, numbers, possibilities)

        newKey = key.replaceFirst('?', '.')

        newPossibilities += getPossibilities(newKey, numbers, possibilities)
    } else {
        if (isPossible(key, newNumbers)) {
            // println("Key: $key, Numbers: $numbers, ${isPossible(key, newNumbers)}")
            return 1
        }
    }

    return possibilities + newPossibilities
}

fun isPossible(key: String, numbers: MutableList<Int>): Boolean {
    if (numbers.isEmpty())
        return true
    val number = numbers[0]
    numbers.removeAt(0)

    for (i in 0..key.length - number) {
        if (!key.substring(i, i + number).contains('.')) {
            if (key[i - 1] == '#' || key[i + number] == '#')
                continue

            if (numbers.isEmpty() && key.substring(i + number, key.length).contains('#'))
                return false

            if (key.substring(0, i).contains('#'))
                return false

            if (numbers.isEmpty())
                return true

            return isPossible("." + key.substring(i + number + 1), numbers)
        }
    }
    return false
}