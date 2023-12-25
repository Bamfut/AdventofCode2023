import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("src/main/kotlin/day13/input.txt").inputStream()
    val lineList = mutableListOf<String>()
    var value = 0

    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    val pattern: MutableList<MutableList<Char>> = mutableListOf()

    for (i in lineList.indices) {
        if (i == lineList.size - 1) {
            val row = mutableListOf<Char>()
            lineList[i].forEach { row.add(it) }
            pattern.add(row)

            // printPattern(pattern)
            value += findMirror2(pattern)
            pattern.clear()
            continue
        }

        if (lineList[i] == "") {
            // printPattern(pattern)
            value += findMirror2(pattern)
            // println()
            pattern.clear()
            continue
        }

        val row = mutableListOf<Char>()
        lineList[i].forEach { row.add(it) }
        pattern.add(row)
    }

    println(value)
}

fun findMirror2(pattern: MutableList<MutableList<Char>>) : Int{
    var value = 0
    var index:Pair<Int, Int> = Pair(0, 0)
    rotatePattern(pattern)

    for (i in 0..<pattern.size - 1) {
        if (pattern[i] == pattern[i + 1] || compareWithError(pattern[i], pattern[i + 1]) == 1) {
            if (findMirrorSize2(pattern, i, compareWithError(pattern[i], pattern[i + 1])) > index.second) {
                index = Pair(i + 1, findMirrorSize2(pattern, i, compareWithError(pattern[i], pattern[i + 1])))
            }
        }
    }

    value = index.first

    rotatePattern(pattern)
    rotatePattern(pattern)
    rotatePattern(pattern)

    for (i in 0..<pattern.size - 1) {
        if (pattern[i] == pattern[i + 1] || compareWithError(pattern[i], pattern[i + 1]) == 1) {
            if (findMirrorSize2(pattern, i, compareWithError(pattern[i], pattern[i + 1])) > index.second) {
                index = Pair(i + 1, findMirrorSize2(pattern, i, compareWithError(pattern[i], pattern[i + 1])))
            }
        }
    }

    if (index.first > value) {
        value = index.first * 100
    }

    return value
}

fun findMirrorSize2(pattern: MutableList<MutableList<Char>>, index: Int, errors: Int) : Int {
    var validFor = 1
    var newErrors = errors

    for (i in 1..<pattern.size) {
        if ((index - i < 0 || index + i  + 1>= pattern.size) && newErrors == 1) {
            return validFor
        } else if (index - i < 0 || index + i  + 1>= pattern.size) {
            return 0
        }

        if (pattern[index - i] == pattern[index + i + 1]) {
            validFor++
        } else if (compareWithError(pattern[index - i], pattern[index + i + 1]) == 1) {
            validFor++
            newErrors++
            if (newErrors == 1 && index - i < 0 || index + i  + 1>= pattern.size) {
                return validFor
            }
        } else {
            return 0
        }
    }

    return 0
}

fun compareWithError(charList1: MutableList<Char>, charList2: MutableList<Char>) : Int {
    var errors = 0

    for (i in charList1.indices) {
        if (charList1[i] != charList2[i]) {
            errors++
        }
    }

    return errors
}