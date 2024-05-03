package cinema

class Cinema(val numberRows: Int, val numberCols: Int) {
    enum class Seat {
        S,
        B
    }

    class Ticket(val row: Int, val col: Int, val price: Int) {
        fun printPrice() {
            println("Ticket price: ${'$'}$price")
        }
    }

    val matrix = (1..numberRows).map { MutableList(numberCols) { _ -> Seat.S } }

    fun buyTicket(row: Int, col: Int): Ticket {
        matrix[row - 1][col - 1] = Seat.B
        val price = if (numberRows * numberCols <= 60) 10 else if (row > numberRows / 2) 8 else 10
        return Ticket(row, col, price)
    }

    fun printRoom() {
        println()
        println("Cinema:")
        println("  ${(1..numberCols).joinToString(" ")}")
        for (row in 1..numberRows) {
            println("$row ${matrix[row - 1].joinToString(" ")}")
        }
        println()
    }
}

fun main() {
    val cinema = getCiname()
    while (true) {
        printMenu()
        when (readln()) {
            "0" -> break
            "1" -> cinema.printRoom()
            "2" -> sellTicket(cinema)
        }
    }
}

fun getCiname(): Cinema {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val cols = readln().toInt()
    println()
    return Cinema(rows, cols)
}

fun sellTicket(cinema: Cinema) {
    println()
    println("Enter a row number:")
    val rowNumber = readln().toInt()
    println("Enter a seat number in that row:")
    val colNumber = readln().toInt()
    val ticket = cinema.buyTicket(rowNumber, colNumber)
    ticket.printPrice()
    println()
}

fun printMenu() {
    println("""
        1. Show the seats
        2. Buy a ticket
        0. Exit
        """.trimIndent())
}