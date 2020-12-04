package day2

import java.io.File

data class Password(val min: Int, val max: Int, val character: Char, val password: String)

fun readLinesToArray(fileName: String) : List<List<String>> {
    return File(fileName).readLines().map({it.trim().split("\\s+".toRegex())})
}

private fun splitLines(fileName: String) = readLinesToArray(fileName).map {
    Password(it[0].split("-")[0].toInt(),
        it[0].split("-")[1].toInt(),
        it[1].replace(":", "").first(),
        it[2])
}

fun checkPasswords(fileName: String) : Int {
    val list = splitLines(fileName)
    return list.fold(0) { acc, e ->
        val current = e.password.filter {it == e.character }.count()
        if (current >= e.min && current <= e.max) {
            acc + 1
        } else {
            acc
        }
    }
}

fun checkPasswordsPart2(fileName: String) : Int {
    val list = splitLines(fileName)
    return list.fold(0) { acc, e ->
        val atPos1 = e.password.get(e.min - 1) == e.character
        val atPos2 = e.password.get(e.max - 1) == e.character
        if (atPos1.xor(atPos2)) acc + 1 else acc
    }
}

fun main() {
    println("Solution 1: " + checkPasswords("src/main/resources/inputDay2"))
    println("Solution 2: " + checkPasswordsPart2("src/main/resources/inputDay2"))
}