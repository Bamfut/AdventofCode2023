package day15

import java.io.File
import java.io.InputStream

fun main(){
    val inputStream: InputStream = File("src/main/kotlin/day15/input.txt").inputStream()
    val lineList = mutableListOf<String>()
    val boxes:Array<MutableMap<String, Int>> = Array(256) { mutableMapOf() }
    var totalValue = 0

    inputStream.bufferedReader().forEachLine { lineList.add(it) }
    val input = lineList[0].split(",")

    input.forEach { it ->
        var value = 0

        when (it.contains("=")){
            true -> {
                var box = it.split("[-=]".toRegex())[0]
                box.forEach { it2 ->
                    value += it2.code
                    value *= 17
                    value = value.mod(256)
                }
                boxes[value][it.split("[-=]".toRegex())[0]] = it.split("[-=]".toRegex())[1].toInt()
            }
            false -> {
                var box = it.split("[-=]".toRegex())[0]
                box.forEach { it2 ->
                    value += it2.code
                    value *= 17
                    value = value.mod(256)
                }
                boxes[value].remove(it.split("[-=]".toRegex())[0])
            }
        }
    }
    boxes.forEachIndexed { index, mutableMap ->
        var i = 0
        mutableMap.forEach { t, u ->
            i++
            totalValue += (index + 1) * u * i
        }
    }
    println(totalValue)
}