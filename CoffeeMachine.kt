
/**
 * Created by EvyaH on 19/06/2022
 */

fun main() {
    loop@ while (true) {
        print("\nWrite action (buy, fill, take, remaining, exit): ")
        val actionInput = readln()
        when (actionInput.lowercase()) {
            "buy" -> {
                print("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
                val input = readln()
                when (input.lowercase()) {
                    "1" -> espresso()
                    "2" -> latte()
                    "3" -> cappuccino()
                    "back" -> continue@loop
                }
            }
            "fill" -> loadResources()
            "take" -> take()
            "remaining" -> availableResources()
            "exit" -> break
        }
    }
}

var currentWater = 400
var currentMilk = 540
var currentCBeans = 120
var cups = 9
var money = 550

const val waterConsumedEsp = 250
const val beansConsumedEsp = 16
fun espresso() {
    if (currentWater >= waterConsumedEsp && currentCBeans >= beansConsumedEsp && cups > 0) {
        currentWater -= waterConsumedEsp
        currentCBeans -= beansConsumedEsp
        cups -= 1
        money += 4
        println("I have enough resources, making you a coffee! ")
    } else if (currentWater < waterConsumedEsp) println("Sorry, not enough water! ")
    else if (currentCBeans < beansConsumedEsp) println("Sorry, not enough coffee beans! ")
    else if (cups == 0) println("Sorry, not enough cups! ")
}

const val waterConsumedLat = 350
const val milkConsumedLat = 75
const val beansConsumedLat = 20
fun latte() {
    if (currentWater >= waterConsumedLat && currentMilk >= milkConsumedLat && currentCBeans >= beansConsumedLat && cups > 0) {
        currentWater -= waterConsumedLat
        currentMilk -= milkConsumedLat
        currentCBeans -= beansConsumedLat
        cups -= 1
        money += 7
        println("I have enough resources, making you a coffee! ")
    } else if (currentWater < waterConsumedLat) println("Sorry, not enough water! ")
    else if (currentMilk < milkConsumedLat) println("Sorry, not enough milk! ")
    else if (currentCBeans < beansConsumedLat) println("Sorry, not enough coffee beans! ")
    else if (cups == 0) println("Sorry, not enough cups! ")
}

const val waterConsumedCappu = 200
const val milkConsumedCappu = 100
const val beansConsumedCappu = 12
fun cappuccino() {
    if (currentWater >= waterConsumedCappu && currentMilk >= milkConsumedCappu && currentCBeans >= beansConsumedCappu && cups > 0) {
        currentWater -= waterConsumedCappu
        currentMilk -= milkConsumedCappu
        currentCBeans -= beansConsumedCappu
        cups -= 1
        money += 6
        println("I have enough resources, making you a coffee! ")
    } else if (currentWater < waterConsumedCappu) println("Sorry, not enough water! ")
    else if (currentMilk < milkConsumedCappu) println("Sorry, not enough milk! ")
    else if (currentCBeans < beansConsumedCappu) println("Sorry, not enough coffee beans! ")
    else if (cups == 0) println("Sorry, not enough cups! ")
}

fun availableResources() {
    println()
    println(
        """
        The coffee machine has:
        $currentWater ml of water
        $currentMilk ml of milk
        $currentCBeans g of coffee beans
        $cups disposable cups
        $money of money
    """.trimIndent()
    )
}

fun loadResources() {
    print("\nWrite how many ml of water do you want to add: ")
    currentWater += readln().toInt()
    print("Write how many ml of milk do you want to add: ")
    currentMilk += readln().toInt()
    print("Write how many grams of coffee beans do you want to add ")
    currentCBeans += readln().toInt()
    print("Write how many disposable cups of coffee do you want to add: ")
    cups += readln().toInt()
}

fun take() {
    val tempMoney = money
    money = 0
    println("\nI gave you $$tempMoney")
}