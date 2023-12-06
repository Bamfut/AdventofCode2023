package day1

import java.io.File
import java.io.InputStream

fun main(args: Array<String>) {
    /*val inputStream: InputStream = File("src/main/kotlin/day1/1.txt").inputStream()
    val lineList = mutableListOf<String>()
    var sum = 0

    inputStream.bufferedReader().forEachLine { lineList.add(it) }
    lineList.forEach{
        var digits = ""
        for (i in 0.. it.length) {
            if (it[i].isDigit()) {
                digits += it[i].toString()
                break
            }
        }

        for (i in it.length - 1 downTo 0) {
            if (it[i].isDigit()) {
                digits += it[i].toString()
                break
            }
        }

        sum += digits.toInt()
    }

    println(sum)*/

    val inputStream: InputStream = File("src/main/kotlin/day1/2.txt").inputStream()
    val lineList = mutableListOf<String>()
    var sum = 0
    var line = 0

    val allDigitsWords = arrayListOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    val allDigits = arrayListOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")

    inputStream.bufferedReader().forEachLine { lineList.add(it) }
    lineList.forEach{ it ->
        line++
        var firstFoundIndex = 9999
        var firstFoundNumb = 0
        var lastFoundIndex = 0
        var lastFoundNumb = 0

        for (j in 0..< allDigitsWords.size) {
            if (it.indexOf(allDigitsWords[j]) < firstFoundIndex && it.indexOf(allDigitsWords[j]) != -1) {
                firstFoundIndex = it.indexOf(allDigitsWords[j])
                firstFoundNumb = j
            }
        }

        for (j in 0..< allDigits.size) {
            if (it.indexOf(allDigits[j]) < firstFoundIndex && it.indexOf(allDigits[j]) != -1) {
                firstFoundIndex = it.indexOf(allDigits[j])
                firstFoundNumb = j
            }
        }

        for (j in 0..< allDigitsWords.size) {
            if (it.lastIndexOf(allDigitsWords[j]) >= lastFoundIndex) {
                lastFoundIndex = it.lastIndexOf(allDigitsWords[j])
                lastFoundNumb = j
            }
        }

        for (j in 0..< allDigits.size) {
            if (it.lastIndexOf(allDigits[j]) >= lastFoundIndex) {
                lastFoundIndex = it.lastIndexOf(allDigits[j])
                lastFoundNumb = j
            }
        }

        sum += (firstFoundNumb.toString() + lastFoundNumb.toString()).toInt()
        // println("$line $firstFoundNumb$lastFoundNumb")
    }

    println(sum)
}