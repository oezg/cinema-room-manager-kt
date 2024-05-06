package cinema

class Cinema(val numberRows: Int, val numberCols: Int) {
    enum class Seat {
        S,
        B
    }

    class Ticket(val price: Int) {
        fun printPrice() {
            println("Ticket price: \$$price")
        }
    }

    val matrix = (1..numberRows).map { MutableList(numberCols) { _ -> Seat.S } }
    var numberOfSoldTickets = 0
    val numberOfSeats = numberRows * numberCols

    var currentIncome = 0
    val totalIncome =
        if (numberOfSeats <= 60)
            10 * numberOfSeats
        else
            numberOfSeats * 8 + numberRows / 2 * numberCols * 2

    fun sellTicket(row: Int, col: Int): Ticket {
        if (row > numberRows || col > numberCols) {
            throw ArrayIndexOutOfBoundsException()
        }
        if (matrix[row - 1][col - 1] == Seat.B) {
            throw IllegalArgumentException()
        }
        matrix[row - 1][col - 1] = Seat.B
        numberOfSoldTickets++
        val price = if (numberOfSeats <= 60) 10 else if (row > numberRows / 2) 8 else 10
        currentIncome += price
        return Ticket(price)
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

    fun printStatistics() {
        val percentage = String.format("%.2f", numberOfSoldTickets.toDouble().div(numberOfSeats) * 100)
        println("Number of purchased tickets: $numberOfSoldTickets\n" +
                "Percentage: $percentage%\n" +
                "Current income: \$$currentIncome\n" +
                "Total income: \$$totalIncome")
    }
}

fun main() {
    val cinema = getCiname()
    while (true) {
        printMenu()
        when (readln()) {
            "0" -> break
            "1" -> cinema.printRoom()
            "2" -> buyTicket(cinema)
            "3" -> cinema.printStatistics()
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

fun buyTicket(cinema: Cinema) {
    println()
    println("Enter a row number:")
    val rowNumber = readln().toInt()
    println("Enter a seat number in that row:")
    val colNumber = readln().toInt()
    try {
        val ticket = cinema.sellTicket(rowNumber, colNumber)
        ticket.printPrice()
        println()
    } catch (e: ArrayIndexOutOfBoundsException) {
        println("Wrong input!")
        buyTicket(cinema)
    } catch (e: IllegalArgumentException) {
        println("That ticket has already been purchased!")
        buyTicket(cinema)
    }
}

fun printMenu() {
    println("""
        1. Show the seats
        2. Buy a ticket
        3. Statistics
        0. Exit
        """.trimIndent())
}