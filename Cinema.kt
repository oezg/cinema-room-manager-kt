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
        println("Cinema:")
        println("  ${(1..numberCols).joinToString(" ")}")
        for (row in 1..numberRows) {
            println("$row ${matrix[row - 1].joinToString(" ")}")
        }
    }
}

fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val cols = readln().toInt()

    println()
    val cinema = Cinema(rows, cols)
    cinema.printRoom()

    println()
    println("Enter a row number:")
    val rowNumber = readln().toInt()
    println("Enter a seat number in that row:")
    val colNumber = readln().toInt()

    println()
    val ticket = cinema.buyTicket(rowNumber, colNumber)
    ticket.printPrice()

    println()
    cinema.printRoom()
}