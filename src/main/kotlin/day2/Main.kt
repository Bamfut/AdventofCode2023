package day2

import java.io.File
import java.io.InputStream

fun main(args: Array<String>) {
    /*val inputStream: InputStream = File("src/main/kotlin/day2/1.txt").inputStream()
    val lineList = mutableListOf<String>()

    val minCubeMap = mutableMapOf<Int, Cubes>()

    inputStream.bufferedReader().forEachLine { lineList.add(it) }
    lineList.forEach{ it ->
        val split = it.split(":")
        val gameId = split[0].split(" ")[1].toInt()
        val rounds = split[1].split(";")

        minCubeMap[gameId] = Cubes(0, 0, 0)

        rounds.forEach { round ->
            var cubeData = round.split(" ")
            cubeData = cubeData.filter { it != "" }

            for (i in 0..<cubeData.size/2) {
                if (cubeData[i*2+1].contains("red")) {
                    minCubeMap[gameId]!!.replaceIfBigger(cubeData[i*2].toInt(), 0, 0)
                } else if (cubeData[i*2+1].contains("green")) {
                    minCubeMap[gameId]!!.replaceIfBigger(0, cubeData[i*2].toInt(), 0)
                } else if (cubeData[i*2+1].contains("blue")) {
                    minCubeMap[gameId]!!.replaceIfBigger(0, 0, cubeData[i*2].toInt())
                }
            }
        }
    }
    minCubeMap = minCubeMap.filter { it.value.red <= 12 && it.value.green <= 13 && it.value.blue <= 14 }.toMutableMap()

    // sum up all the keys
    var sum = 0
    minCubeMap.forEach { (gameId, cubes) ->
        sum += gameId
    }

    minCubeMap.forEach { (gameId, cubes) ->
        println("Game $gameId: ${cubes.red} ${cubes.green} ${cubes.blue}")
    }

    println("Sum: $sum")*/

    val inputStream: InputStream = File("src/main/kotlin/day2/2.txt").inputStream()
    val lineList = mutableListOf<String>()

    val minCubeMap = mutableMapOf<Int, Cubes>()

    inputStream.bufferedReader().forEachLine { lineList.add(it) }
    lineList.forEach{ it ->
        val split = it.split(":")
        val gameId = split[0].split(" ")[1].toInt()
        val rounds = split[1].split(";")

        minCubeMap[gameId] = Cubes(0, 0, 0)

        rounds.forEach { round ->
            var cubeData = round.split(" ")
            cubeData = cubeData.filter { it != "" }

            for (i in 0..<cubeData.size/2) {
                if (cubeData[i*2+1].contains("red")) {
                    minCubeMap[gameId]!!.replaceIfBigger(cubeData[i*2].toInt(), 0, 0)
                } else if (cubeData[i*2+1].contains("green")) {
                    minCubeMap[gameId]!!.replaceIfBigger(0, cubeData[i*2].toInt(), 0)
                } else if (cubeData[i*2+1].contains("blue")) {
                    minCubeMap[gameId]!!.replaceIfBigger(0, 0, cubeData[i*2].toInt())
                }
            }
        }
    }

    minCubeMap.forEach { (gameId, cubes) ->
        minCubeMap[gameId]!!.red = cubes.red * cubes.green * cubes.blue
    }

    // sum up all the keys
    var sum = 0
    minCubeMap.forEach { (gameId, cubes) ->
        sum += cubes.red
    }

    minCubeMap.forEach { (gameId, cubes) ->
        println("Game $gameId: ${cubes.red} ${cubes.green} ${cubes.blue}")
    }

    println("Sum: $sum")
}