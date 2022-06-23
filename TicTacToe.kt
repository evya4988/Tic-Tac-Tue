/**
 * Created by EvyaH on 18/06/2022
 */
class Excersize {}

fun main() {
    val s1 = mutableListOf<String>(" ", " ", " ", " ", " ", " ", " ", " ", " ")
    var grid = """
    ---------
    | ${s1[0]} ${s1[1]} ${s1[2]} |
    | ${s1[3]} ${s1[4]} ${s1[5]} |
    | ${s1[6]} ${s1[7]} ${s1[8]} |
    ---------
    """.trimIndent()
    println(grid)

    var wins = 0
    val occ = "This cell is occupied! Choose another one!"
    var countX = 0
    var countY = 0
    loop@ for (i in (1..99)) {

        val topRowWin = s1[0] == s1[1] && s1[0] == s1[2] && s1[0] != "_" && s1[0] != " "
        val midRowWin = s1[3] == s1[4] && s1[3] == s1[5] && s1[3] != "_" && s1[3] != " "
        val dowRowWin = s1[6] == s1[7] && s1[6] == s1[8] && s1[6] != "_" && s1[6] != " "

        val leftColumnWin = s1[0] == s1[3] && s1[0] == s1[6] && s1[0] != "_" && s1[0] != " "
        val midColumnWin = s1[1] == s1[4] && s1[1] == s1[7] && s1[1] != "_" && s1[1] != " "
        val rigColumnWin = s1[2] == s1[5] && s1[2] == s1[8] && s1[2] != "_" && s1[2] != " "

        val sideWayWinLeftToRight = s1[0] == s1[4] && s1[0] == s1[8] && s1[0] != "_" && s1[0] != " "
        val sideWayWinRightToLeft = s1[2] == s1[4] && s1[2] == s1[6] && s1[2] != "_" && s1[2] != " "

        val winConditions = booleanArrayOf(
            topRowWin, midRowWin, dowRowWin, leftColumnWin, midColumnWin,
            rigColumnWin, sideWayWinLeftToRight, sideWayWinRightToLeft
        )
        for (win in winConditions) {
            if (win) {
                wins++
            }
        }

        if (wins >= 1) {
            if (rigColumnWin || sideWayWinRightToLeft) {
                println(s1[2] + " wins")
            } else if (topRowWin || leftColumnWin || sideWayWinLeftToRight) {
                println(s1[0] + " wins")
            } else if (midRowWin) {
                println(s1[3] + " wins")
            } else if (dowRowWin) {
                println(s1[6] + " wins")
            } else if (midColumnWin) {
                println(s1[1] + " wins")
            }
            break
        }
        if (wins == 0) {
            var count = 0
            for (i in s1) {
                if (i == "X" || i == "O") count++
            }
            if (count == 9) {
                println("Draw")
                break
            }
        }
        print("Enter the coordinates: ")
        val (s2: String, s3: String) = readln().split(" ")
        if (!(s2.first().isDigit() && s3.first().isDigit())) {
            println("You should enter numbers!")
            continue@loop
        }
        if (s2.toInt() in 1..3 && s3.toInt() in 1..3) {
            val list = mutableListOf<Int>()
            list.clear()
            list.add(0, s3.toInt())
            list.add(0, s2.toInt())
            when {
                list[0] == 1 && list[1] == 1 -> {
                    if (s1[0] == "O" || s1[0] == "X") {
                        println(occ)
                        continue@loop
                    } else {
                        if (countX == 0) {
                            s1[0] = "X"
                            countX++
                        } else if (countX == 1) {
                            if (countY != 0) {
                                s1[0] = "X"
                                countY--
                            } else {
                                s1[0] = "O"
                                countY++
                            }
                        }
                    }
                }
                list[0] == 1 && list[1] == 2 -> {
                    if (s1[1] == "O" || s1[1] == "X") {
                        println(occ)
                        continue@loop
                    } else {
                        if (countX == 0) {
                            s1[1] = "X"
                            countX++
                        } else if (countX == 1) {
                            if (countY != 0) {
                                s1[1] = "X"
                                countY--
                            } else {
                                s1[1] = "O"
                                countY++
                            }
                        }
                    }
                }
                list[0] == 1 && list[1] == 3 -> {
                    if (s1[2] == "O" || s1[2] == "X") {
                        println(occ)
                        continue@loop
                    } else {
                        if (countX == 0) {
                            s1[2] = "X"
                            countX++
                        } else if (countX == 1) {
                            if (countY != 0) {
                                s1[2] = "X"
                                countY--
                            } else {
                                s1[2] = "O"
                                countY++
                            }
                        }
                    }
                }
                list[0] == 2 && list[1] == 1 -> {
                    if (s1[3] == "O" || s1[3] == "X") {
                        println(occ)
                        continue@loop
                    } else {
                        if (countX == 0) {
                            s1[3] = "X"
                            countX++
                        } else if (countX == 1) {
                            if (countY != 0) {
                                s1[3] = "X"
                                countY--
                            } else {
                                s1[3] = "O"
                                countY++
                            }
                        }
                    }
                }
                list[0] == 2 && list[1] == 2 -> {
                    if (s1[4] == "O" || s1[4] == "X") {
                        println(occ)
                        continue@loop
                    } else {
                        if (countX == 0) {
                            s1[4] = "X"
                            countX++
                        } else if (countX == 1) {
                            if (countY != 0) {
                                s1[4] = "X"
                                countY--
                            } else {
                                s1[4] = "O"
                                countY++
                            }
                        }
                    }
                }
                list[0] == 2 && list[1] == 3 -> {
                    if (s1[5] == "O" || s1[5] == "X") {
                        println(occ)
                        continue@loop
                    } else {
                        if (countX == 0) {
                            s1[5] = "X"
                            countX++
                        } else if (countX == 1) {
                            if (countY != 0) {
                                s1[5] = "X"
                                countY--
                            } else {
                                s1[5] = "O"
                                countY++
                            }
                        }
                    }
                }
                list[0] == 3 && list[1] == 1 -> {
                    if (s1[6] == "O" || s1[6] == "X") {
                        println(occ)
                        continue@loop
                    } else {
                        if (countX == 0) {
                            s1[6] = "X"
                            countX++
                        } else if (countX == 1) {
                            if (countY != 0) {
                                s1[6] = "X"
                                countY--
                            } else {
                                s1[6] = "O"
                                countY++
                            }
                        }
                    }
                }
                list[0] == 3 && list[1] == 2 -> {
                    if (s1[7] == "O" || s1[7] == "X") {
                        println(occ)
                        continue@loop
                    } else {
                        if (countX == 0) {
                            s1[7] = "X"
                            countX++
                        } else if (countX == 1) {
                            if (countY != 0) {
                                s1[7] = "X"
                                countY--
                            } else {
                                s1[7] = "O"
                                countY++
                            }
                        }
                    }
                }
                list[0] == 3 && list[1] == 3 -> {
                    if (s1[8] == "O" || s1[8] == "X") {
                        println(occ)
                        continue@loop
                    } else {
                        if (countX == 0) {
                            s1[8] = "X"
                            countX++
                        } else if (countX == 1) {
                            if (countY != 0) {
                                s1[8] = "X"
                                countY--
                            } else {
                                s1[8] = "O"
                                countY++
                            }
                        }
                    }
                }

            }

            grid = """
    ---------
    | ${s1[0]} ${s1[1]} ${s1[2]} |
    | ${s1[3]} ${s1[4]} ${s1[5]} |
    | ${s1[6]} ${s1[7]} ${s1[8]} |
    ---------
    """.trimIndent()

        } else {
            when {
                s2.toInt() < 1 || s2.toInt() > 3 || s3.toInt() > 3 || s3.toInt() < 1 -> {
                    println("Coordinates should be from 1 to 3!")
                    continue@loop
                }

                s2.toFloat().isNaN() -> {
                    println("You should enter numbers!")
                    continue@loop
                }
            }
        }
        println(grid)
    }
}