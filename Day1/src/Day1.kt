import java.io.File
import java.time.Duration
import java.time.LocalDateTime

fun main(args: Array<String>) {
    val input = File("Day1/resources/Day1.txt")
            .readLines()
            .map { parseDelta(it) }

    var now = LocalDateTime.now()

    println("Part 1 : " + part1(input) + " time : " + Duration.between(now, LocalDateTime.now()).toMillis())

    now = LocalDateTime.now()
    println("Part 2 : " + part2(input) + " time : " + Duration.between(now, LocalDateTime.now()).toMillis())
}

private fun parseDelta(input: String): Int {
    return when (input.first()) {
        '+' -> input.drop(1).toInt()
        else -> -input.drop(1).toInt()
    }
}

private fun part1(input: List<Int>): Int {
    return input.fold(0) {
        sum: Int,
        element -> sum + element
    }
}

private fun part2(input: List<Int>): Int {
    return calculate(input, 0, mutableListOf(0))
}

tailrec private fun calculate(input: List<Int>, index: Int, frequencies: MutableList<Int>): Int {
    val frequency = frequencies.last() + input[index]

    if (frequencies.contains(frequency)) {
        return frequency
    }

    frequencies.add(frequency)

    return calculate(input, if (index + 1 == input.size) { 0 } else { index + 1 }, frequencies)
}