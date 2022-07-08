/**
 * Created by EvyaH on 08/07/2022
 */

var cinema = arrayOf<Array<String>>()
var numOfRows = 0
var seatsInRow = 0
var frontPurchasedTickets = 0
var backPurchasedTickets = 0
fun main() {
    println("Enter the number of rows:")
    numOfRows = readln().toInt()
    println("Enter the number of seats in each row:")
    seatsInRow = readln().toInt()
    for (i in 0 until numOfRows) {
        var array = arrayOf<String>()
        for (j in 0 until seatsInRow) {
            array += "S"
        }
        cinema += array
    }
    while (true) {
        println("\n1. Show the seats \n2. Buy a ticket \n3. Statistics \n0. Exit")
        when (readln().toInt()) {
            1 -> getGrid(seatsInRow, numOfRows)
            2 -> purchaseTicket()
            3 -> {
                val totalTickets = frontPurchasedTickets + backPurchasedTickets
                println("\nNumber of purchased tickets: $totalTickets")
                val percentage = (totalTickets.toDouble() / (numOfRows * seatsInRow)) * 100
                val formatPercentage = "%.2f".format(percentage)
                println("Percentage: $formatPercentage%")
                println("Current income: $${(frontPurchasedTickets * 10) + (backPurchasedTickets * 8)}")
                getTotalIncome()
            }
            0 -> return
        }
    }
}

fun getGrid(seatsInRow: Int, numOfRows: Int) {
    println("\nCinema:")
    print("  ")
    for (i in 1..seatsInRow) {
        print("$i ")
    }
    println()
    var count = 1
    for (array in cinema) {
        if (count <= numOfRows) {
            print("$count ")
        }
        count++
        for (value in array) {
            print("$value ")
        }
        println()
    }
}

fun purchaseTicket() {
    loop@ while (true) {
        println("\nEnter a row number: ")
        val rows = readln().toInt()
        println("Enter a seat number in that row: ")
        val seats = readln().toInt()
        if (rows > numOfRows || seats > seatsInRow) {
            println("\nWrong input!")
            continue@loop
        }

        for (i in 0 until  numOfRows) {
            for (j in 0 until  seatsInRow) {
                when {
                    i + 1 == rows && j + 1 == seats -> {
                        if (cinema[i][j] == "B") {
                            println("\nThat ticket has already been purchased!")
                            continue@loop
                        } else {
                            cinema[i][j] = "B"
                            if (numOfRows * seatsInRow > 60) {
                                if (rows <= numOfRows / 2) {
                                    println("\nTicket price: $10")
                                    frontPurchasedTickets++
                                    break@loop
                                } else {
                                    println("\nTicket price: $8")
                                    backPurchasedTickets++
                                    break@loop
                                }
                            } else {
                                println("\nTicket price: $10")
                                frontPurchasedTickets++
                                break@loop
                            }
                        }
                    }
                }
            }
        }
    }
}


fun getTotalIncome() {
    if (numOfRows * seatsInRow > 60) {
        val frontScreenRoom = numOfRows / 2
        val backScreenRoom = numOfRows - frontScreenRoom
        val finalIncome: Int
        if (numOfRows % 2 != 0) {
            finalIncome = ((frontScreenRoom * seatsInRow) * 10) + ((backScreenRoom * seatsInRow) * 8)
            println("Total income: $$finalIncome")
        } else {
            finalIncome = ((frontScreenRoom * seatsInRow) * 10) + ((frontScreenRoom * seatsInRow) * 8)
            println("Total income: $finalIncome")
        }
    } else {
        println("Total income: $${(numOfRows * seatsInRow) * 10}")
    }
}