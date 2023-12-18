package day16

import java.io.File
import java.io.InputStream

fun main(){
    val inputStream: InputStream = File("src/main/kotlin/day15/input.txt").inputStream()
    val lineList = mutableListOf<String>()
    val energizedGrid:MutableList<MutableList<Boolean>>

    inputStream.bufferedReader().forEachLine { lineList.add(it) }


}

fun lightBeam(lineList: MutableList<String>, energizedGrid:MutableList<MutableList<Boolean>>): MutableList<MutableList<Boolean>>{

    return energizedGrid
}