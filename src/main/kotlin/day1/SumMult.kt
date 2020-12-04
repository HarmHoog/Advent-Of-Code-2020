package day1

import java.io.File

fun readLinesToIntList(fileName: String) : List<Int> {
    val result = mutableListOf<Int>()
    File(fileName).forEachLine {result.add(it.toInt()) }
    return result
}

fun sumMult2020(fileName: String) : Int {
    val numbers = readLinesToIntList(fileName)
    for (i in numbers.indices) {
        for (j in (i + 1) until numbers.size) {
                if (numbers[i] + numbers[j] == 2020) {
                    return numbers[i] * numbers[j]
                }
        }
    }

    return -1
}

fun sumMult2020Part2(fileName: String) : Int {
    val numbers = readLinesToIntList(fileName)
    for (i in numbers.indices) {
        for (j in (i + 1) until numbers.size) {
            for (k in (j + 1) until numbers.size) {
                if (numbers[i] + numbers[j] + numbers[k] == 2020) {
                    return numbers[i] * numbers[j] * numbers[k]
                }
            }
        }
    }

    return -1
}

fun main(args: Array<String>) {
    println("Solution 1: " + sumMult2020("src/main/resources/inputDay1"))
    println("Solution 2: " + sumMult2020Part2("src/main/resources/inputDay1"))
}