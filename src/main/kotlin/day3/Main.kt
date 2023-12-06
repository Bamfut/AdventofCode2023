package day3

import java.io.File
import java.io.InputStream


fun main() {
    /*val inputStream: InputStream = File("src/main/kotlin/day3/1.txt").inputStream()
    val lineList = mutableListOf<String>()
    var sum = 0

    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    for (line in 0..<lineList.size) {
        var j = 0
        while (j < lineList[line].length) {
            if (lineList[line][j].isDigit()) {
                var number = ""
                val startIndex = j
                while (lineList[line][j].isDigit()) {
                    number += lineList[line][j]
                    if (j == lineList[line].length-1) {
                        break
                    }
                    j++
                }

                if (validateNumber(startIndex, j, line, lineList)) {
                    sum += number.toInt()
                }
            }
            j++
        }
    }

    println(sum)*/

    val inputStream: InputStream = File("src/main/kotlin/day3/1.txt").inputStream()
    val lineList = mutableListOf<String>()
    var sum = 0

    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    val matrix: Array<Array<String>> = Array(lineList.size) { Array(lineList[0].length) { "" } }

    for (line in 0..<lineList.size) {
        var j = 0
        while (j < lineList[line].length) {
            println("Matrix at line: $line $j")
            if (lineList[line][j].isDigit()) {
                var number = ""
                val startIndex = j
                while (lineList[line][j].isDigit()) {
                    number += lineList[line][j]
                    if (j == lineList[line].length-1) {
                        break
                    }
                    j++
                }

                for (k in startIndex..<j) {
                    matrix[line][k] = number
                }

                if (j != lineList[line].length-1) {
                    matrix[line][j] = lineList[line][j].toString()
                }
            } else {
                matrix[line][j] = lineList[line][j].toString()
            }
            j++
        }
    }

    println("Matrix created")

    // print the matrix
    for (i in matrix.indices) {
        for (j in 0..<matrix[i].size) {
            print(matrix[i][j])
        }
        println()
    }

    for (i in matrix.indices) {
        for (j in 0..<matrix[i].size) {
            if (matrix[i][j] == "*") {
                println("At line: $i")
                sum += validateGear(j, i, matrix)
            }
        }
    }

    println(sum)
}

fun validateNumber(indexStart:Int, indexEnd:Int, line:Int, lineList:MutableList<String>): Boolean {
    var stringToCheck = ""
    val specialCharacters = arrayListOf("*", "#", "@", "!", "&", "%", "$", "^", "~", "`", "(", ")", "-", "_", "+", "=", "[", "]", "{", "}", "|", "/", "?", "<", ">", ",", ":", ";", "'")
    if (line == 0){ // start of the file
        if (indexStart == 0) {
            stringToCheck += lineList[line].substring(indexStart, indexEnd+1)
            stringToCheck += lineList[line+1].substring(indexStart, indexEnd+1)
        } else if (indexEnd == lineList[line].length-1) {
            stringToCheck += lineList[line].substring(indexStart-1, indexEnd)
            stringToCheck += lineList[line+1].substring(indexStart-1, indexEnd)
        } else {
            stringToCheck += lineList[line].substring(indexStart-1, indexEnd+1)
            stringToCheck += lineList[line+1].substring(indexStart-1, indexEnd+1)
        }
    } else if (line == lineList.size-1) { // end of the file
        if (indexStart == 0) {
            stringToCheck += lineList[line-1].substring(indexStart, indexEnd+1)
            stringToCheck += lineList[line].substring(indexStart, indexEnd+1)
        } else if (indexEnd == lineList[line].length-1) {
            stringToCheck += lineList[line-1].substring(indexStart-1, indexEnd)
            stringToCheck += lineList[line].substring(indexStart-1, indexEnd)
        } else {
            stringToCheck += lineList[line-1].substring(indexStart-1, indexEnd+1)
            stringToCheck += lineList[line].substring(indexStart-1, indexEnd+1)
        }
    } else { // middle of the file
        if (indexStart == 0) {
            stringToCheck += lineList[line-1].substring(indexStart, indexEnd+1)
            stringToCheck += lineList[line].substring(indexStart, indexEnd+1)
            stringToCheck += lineList[line+1].substring(indexStart, indexEnd+1)
        } else if (indexEnd == lineList[line].length-1) {
            stringToCheck += lineList[line-1].substring(indexStart-1, indexEnd)
            stringToCheck += lineList[line].substring(indexStart-1, indexEnd)
            stringToCheck += lineList[line+1].substring(indexStart-1, indexEnd)
        } else {
            stringToCheck += lineList[line-1].substring(indexStart-1, indexEnd+1)
            stringToCheck += lineList[line].substring(indexStart-1, indexEnd+1)
            stringToCheck += lineList[line+1].substring(indexStart-1, indexEnd+1)
        }
    }
    // check if there is a *, #, @... in the string
    for (i in 0..<specialCharacters.size) {
        if (stringToCheck.contains(specialCharacters[i])) {
            return true
        }
    }
    return false
}

fun validateGear(index:Int, line:Int, matrix:Array<Array<String>>): Int {
    var validNumbers = 0
    var number = 1
    val matrixToCheck: Array<Array<String>> = Array(3) { Array(3) { "" } }

    if (line == 0){ // start of the file
        if (index == 0) {
            matrixToCheck[1][0] += matrix[line][index]
            matrixToCheck[1][1] += matrix[line][index+1]
            matrixToCheck[2][0] += matrix[line+1][index]
            matrixToCheck[2][1] += matrix[line+1][index+1]
        } else if (index == matrix[line].size-1) {
            matrixToCheck[1][0] += matrix[line][index-1]
            matrixToCheck[1][2] += matrix[line][index]
            matrixToCheck[2][0] += matrix[line+1][index-1]
            matrixToCheck[2][1] += matrix[line+1][index]
        } else {
            matrixToCheck[1][0] += matrix[line][index-1]
            matrixToCheck[1][1] += matrix[line][index]
            matrixToCheck[1][2] += matrix[line][index+1]
            matrixToCheck[2][0] += matrix[line+1][index-1]
            matrixToCheck[2][1] += matrix[line+1][index]
            matrixToCheck[2][2] += matrix[line+1][index+1]
        }
    } else if (line == matrix.size-1) { // end of the file
        if (index == 0) {
            matrixToCheck[0][0] += matrix[line-1][index]
            matrixToCheck[0][1] += matrix[line-1][index+1]
            matrixToCheck[1][0] += matrix[line][index]
            matrixToCheck[1][1] += matrix[line][index+1]
        } else if (index == matrix[line].size-1) {
            matrixToCheck[0][0] += matrix[line-1][index-1]
            matrixToCheck[0][1] += matrix[line-1][index]
            matrixToCheck[1][0] += matrix[line][index-1]
            matrixToCheck[1][1] += matrix[line][index]
        } else {
            matrixToCheck[0][0] += matrix[line-1][index-1]
            matrixToCheck[0][1] += matrix[line-1][index]
            matrixToCheck[0][2] += matrix[line-1][index+1]
            matrixToCheck[1][0] += matrix[line][index-1]
            matrixToCheck[1][1] += matrix[line][index]
            matrixToCheck[1][2] += matrix[line][index+1]
        }
    } else { // middle of the file
        if (index == 0) {
            matrixToCheck[0][0] += matrix[line-1][index]
            matrixToCheck[0][1] += matrix[line-1][index+1]
            matrixToCheck[1][2] += matrix[line][index]
            matrixToCheck[1][0] += matrix[line][index+1]
            matrixToCheck[2][1] += matrix[line+1][index]
            matrixToCheck[2][2] += matrix[line+1][index+1]
        } else if (index == matrix[line].size-1) {
            matrixToCheck[0][0] += matrix[line-1][index-1]
            matrixToCheck[0][1] += matrix[line-1][index]
            matrixToCheck[1][0] += matrix[line][index-1]
            matrixToCheck[1][1] += matrix[line][index]
            matrixToCheck[2][0] += matrix[line+1][index-1]
            matrixToCheck[2][1] += matrix[line+1][index]
        } else {
            matrixToCheck[0][0] += matrix[line-1][index-1]
            matrixToCheck[0][1] += matrix[line-1][index]
            matrixToCheck[0][2] += matrix[line-1][index+1]
            matrixToCheck[1][0] += matrix[line][index-1]
            matrixToCheck[1][1] += matrix[line][index]
            matrixToCheck[1][2] += matrix[line][index+1]
            matrixToCheck[2][0] += matrix[line+1][index-1]
            matrixToCheck[2][1] += matrix[line+1][index]
            matrixToCheck[2][2] += matrix[line+1][index+1]
        }
    }

    for (i in matrixToCheck.indices) {
        if (null != matrixToCheck[i][0].toIntOrNull()) {
            number *= matrixToCheck[i][0].toInt()
            validNumbers++
            //println(matrixToCheck[i][0] + " added")
        }
        if (null != matrixToCheck[i][2].toIntOrNull() && matrixToCheck[i][1].toIntOrNull() == null) {
            number *= matrixToCheck[i][2].toInt()
            validNumbers++
            //println(matrixToCheck[i][2] + " added")
        }
        if (null == matrixToCheck[i][2].toIntOrNull() && matrixToCheck[i][1].toIntOrNull() != null && null == matrixToCheck[i][0].toIntOrNull()) {
            number *= matrixToCheck[i][1].toInt()
            validNumbers++
            //println(matrixToCheck[i][1] + " added")
        }
        if (null != matrixToCheck[i][2].toIntOrNull() && null != matrixToCheck[i][1].toIntOrNull() && null == matrixToCheck[i][0].toIntOrNull()) {
            number *= matrixToCheck[i][1].toInt()
            validNumbers++
            //println(matrixToCheck[i][1] + " added")
        }
    }

    if (validNumbers == 2){
        return number
    } else {
        return 0
    }
}