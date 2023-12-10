package day9

import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("src/main/kotlin/day9/1.txt").inputStream()
    val lineList = mutableListOf<String>()
    var sum = 0L

    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    val sequence:MutableList<MutableList<MutableList<Long>>> = mutableListOf()

    lineList.forEach { it ->
        val list = mutableListOf<MutableList<Long>>()
        val list2 = mutableListOf<Long>()
        it.split(" ").forEach { it ->
            list2.add(it.toLong())
        }
        list.add(list2)
        sequence.add(list)
    }

    sequence.forEach { it ->
        var j = 0
        while (true){
            val list = mutableListOf<Long>()
            for (i in 0..<it[j].size-1) {
                list.add(it[j][i+1] - it[j][i])
            }
            it.add(list)

            if (list.all { it == 0L }) {
                break
            }
            j++
        }
    }

    sequence.forEach { it ->
        for (i in 0..<it.size-1) {
            sum += it[i][it[i].size-1]
        }
    }

    println("Sum: $sum")
}
