package day4

import java.io.File

data class Passport(val byr: String,
                    val ecl: String, val eyr: String,
                    val hcl: String, val hgt: String,
                    val iyr: String, val pid: String)

fun scanPassports(fileName: String) : List<List<String>> {
    val passList = mutableListOf<Passport>()
    return File(fileName).readLines().fold("") {
        acc, e -> if (e == "") "$acc##" else "$acc $e"
    }.split("##")
        .map { e ->
            e.split(" ").filter { it != ""}.sorted()
        }
        .filter { e -> e.size == 8 || (e.size == 7 && e.filter { it.contains("cid:") }.isEmpty())}
}

fun toPassportObjects(list: List<List<String>>) : List<Passport> {
    return list.map { e ->
        val k = e.filter { !it.contains("cid:")}
        Passport(k[0].replace("byr:", ""), k[1].replace("ecl:", ""),
            k[2].replace("eyr:", ""), k[3].replace("hcl:", ""),
            k[4].replace("hgt:", ""), k[5].replace("iyr:", ""),
            k[6].replace("pid:", ""))
    }
}

val fourDigitRegex = "^[0-9]{4}$".toRegex()
val hgtRegex = "^[0-9]+(cm|in)\$".toRegex()
val hclRegex = "^#[a-z0-9]{6}\$".toRegex()
val eclRegex = "^amb|blu|brn|gry|grn|hzl|oth\$".toRegex()
val pidRegex = "^[0-9]{9}$".toRegex()

fun checkPassport(passport: Passport) : Boolean {
    if (!pidRegex.matches(passport.pid) || !eclRegex.matches(passport.ecl) ||
            !hclRegex.matches(passport.hcl)) {
        return false;
    }

    if (fourDigitRegex.matches(passport.byr)) {
        val byr = passport.byr.toInt()
        if (byr < 1920 || byr > 2002) return false
    } else {
        return false
    }

    if (fourDigitRegex.matches(passport.iyr)) {
        val iyr = passport.iyr.toInt()
        if (iyr < 2010 || iyr > 2020) return false
    } else {
        return false
    }

    if (fourDigitRegex.matches(passport.eyr)) {
        val eyr = passport.eyr.toInt()
        if (eyr < 2020 || eyr > 2030) return false
    } else {
        return false
    }

    if (hgtRegex.matches(passport.hgt)) {
        val hgt = passport.hgt
        if (hgt.contains("in")) {
            val inValue = hgt.replace("in", "").toInt()
            if (inValue < 59 || inValue > 76) return false
        } else {
            val cmValue = hgt.replace("cm", "").toInt()
            if (cmValue < 150 || cmValue > 193) return false
        }
    } else {
        return false
    }

    return true
}

fun main(args: Array<String>) {
    val passports = scanPassports("src/main/resources/inputDay4")
    println("Solution 1: " + passports.size)
    println("Solution 2: " + toPassportObjects(passports).count { checkPassport(it)})
}