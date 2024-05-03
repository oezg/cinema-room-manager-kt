package cinema

fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val cols = readln().toInt()
    println("Total income:")
    val income = if (rows * cols > 60) {
        rows / 2 * cols * 10 + (rows - rows / 2) * cols * 8
    } else {
        rows * cols * 10
    }
    println("${'$'}$income")
}