package day3

import java.io.File

fun countTreesAtSlope(list: List<String>, slopeX : Int, slopeY: Int) : Int {
    val n = list.size
    val m = list[0].length
    var count = 0
    var x = 0
    var y = 0
    while (y < n) {
        if (list[y][x] == '#') {
            count++
        }
        x = (x + slopeX).rem(m)
        y += slopeY
    }

    return count
}

fun countTreesPart2(list: List<String>) : Long {
    return countTreesAtSlope(list, 1, 1).toLong() *
            countTreesAtSlope(list, 3,1) *
            countTreesAtSlope(list, 5, 1) *
            countTreesAtSlope(list, 7, 1) *
            countTreesAtSlope(list, 1, 2)
}

fun main() {
    val list = File("src/main/resources/inputDay3").readLines()
    println("Solution 1: " + countTreesAtSlope(list, 3, 1))
    println("Solution 2: " + countTreesPart2(list))
}