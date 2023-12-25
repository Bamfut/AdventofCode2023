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
            value += findMirror(pattern)
            pattern.clear()
            continue
        }

        if (lineList[i] == "") {
            // printPattern(pattern)
            value += findMirror(pattern)
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

fun findMirror(pattern: MutableList<MutableList<Char>>) : Int{
    var value = 0
    var index:Pair<Int, Int> = Pair(0, 0)
    rotatePattern(pattern)

    for (i in 0..<pattern.size - 1) {
        if (pattern[i] == pattern[i + 1]) {
            if (findMirrorSize(pattern, i) > index.second) {
                index = Pair(i + 1, findMirrorSize(pattern, i))
            }
        }
    }
    value = index.first

    rotatePattern(pattern)
    rotatePattern(pattern)
    rotatePattern(pattern)

    for (i in 0..<pattern.size - 1) {
        if (pattern[i] == pattern[i + 1]) {
            if (findMirrorSize(pattern, i) > index.second) {
                index = Pair(i + 1, findMirrorSize(pattern, i))
            }
        }
    }

    if (index.first > value) {
        value = index.first * 100
    }

    return value
}

fun findMirrorSize(pattern: MutableList<MutableList<Char>>, index: Int) : Int {
    var validFor = 1


    for (i in 1..<pattern.size) {
        if (index - i < 0 || index + i  + 1>= pattern.size) {
            return validFor
        }

        if (pattern[index - i] == pattern[index + i + 1]) {
            validFor++
        } else {
            return 0
        }
    }

    return validFor
}

fun printPattern(patten: MutableList<MutableList<Char>>) {
    for (i in patten.indices) {
        for (j in patten[i].indices) {
            print(patten[i][j])
        }
        println()
    }
    println()
}

fun rotatePattern(pattern: MutableList<MutableList<Char>>) {
    val newPatten: MutableList<MutableList<Char>> = mutableListOf()

    for (i in pattern[0].indices) {
        val row = mutableListOf<Char>()
        for (j in pattern.indices) {
            row.add(pattern[j][i])
        }
        newPatten.add(row)
    }

    pattern.clear()
    for (i in newPatten.indices) {
        pattern.add(newPatten[i].reversed().toMutableList())
    }
}