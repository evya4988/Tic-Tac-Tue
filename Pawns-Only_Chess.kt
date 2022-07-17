/**
 * Created by EvyaH on 10/07/2022
 */

//class Chessboard {
//}


fun main() {
    val tempGrid = """
  +---+---+---+---+---+---+---+---+
8 |   |   |   |   |   |   |   |   |
  +---+---+---+---+---+---+---+---+
7 | B | B | B | B | B | B | B | B |
  +---+---+---+---+---+---+---+---+
6 |   |   |   |   |   |   |   |   |
  +---+---+---+---+---+---+---+---+
5 |   |   |   |   |   |   |   |   |
  +---+---+---+---+---+---+---+---+
4 |   |   |   |   |   |   |   |   |
  +---+---+---+---+---+---+---+---+
3 |   |   |   |   |   |   |   |   |
  +---+---+---+---+---+---+---+---+
2 | W | W | W | W | W | W | W | W |
  +---+---+---+---+---+---+---+---+
1 |   |   |   |   |   |   |   |   |
  +---+---+---+---+---+---+---+---+
    a   b   c   d   e   f   g   h
    """.trimIndent()

    var boardGrid: String
    var boardList = arrayOf<Array<Char>>()
    val boardLimit = 8
    var fPName: String
    var sPName: String
    var choosingName: String
    val wPlayer = 'W'
    val bPlayer = 'B'
    var verticalLineStart = 0
    var verticalLineEnd = 0
    var lastOpponentVerticalEndMove = 0
    var lastOpponentEndPos = 0
    var lastOpponentStartPos = 0
    var stalemate = false
    for (i in 0 until boardLimit) {
        var listArray = arrayOf<Char>()
        for (j in 0 until boardLimit) {
            listArray += when (i) {
                1 -> 'W'
                6 -> 'B'
                else -> ' '
            }
        }
        boardList += listArray
    }

    loop@ while (true) {
        println("Pawns-Only Chess")
        println("First Player's name:")
        fPName = readln()
        println("Second Player's name:")
        sPName = readln()
        println(tempGrid)
        if (fPName == sPName || (sPName.isEmpty() || fPName.isEmpty())) {
            println("Invalid input!\n")
            continue@loop
        }
        choosingName = fPName
        break
    }
    loop@ while (true) {
        if (stalemate) {
            println("Stalemate!\nBye!")
            break@loop
        }
        println("\n$choosingName's turn:")
        val move = readln()
        val playersMove = "[a-h][1-8][a-h][1-8]".toRegex()
        val startPos = move[1].toString()
        val endPos = move[3].toString()
        var flag = true
        when {
            move[0] == 'a' -> verticalLineStart = 0
            move[0] == 'b' -> verticalLineStart = 1
            move[0] == 'c' -> verticalLineStart = 2
            move[0] == 'd' -> verticalLineStart = 3
            move[0] == 'e' -> verticalLineStart = 4
            move[0] == 'f' -> verticalLineStart = 5
            move[0] == 'g' -> verticalLineStart = 6
            move[0] == 'h' -> verticalLineStart = 7
        }
        when {
            move[2] == 'a' -> verticalLineEnd = 0
            move[2] == 'b' -> verticalLineEnd = 1
            move[2] == 'c' -> verticalLineEnd = 2
            move[2] == 'd' -> verticalLineEnd = 3
            move[2] == 'e' -> verticalLineEnd = 4
            move[2] == 'f' -> verticalLineEnd = 5
            move[2] == 'g' -> verticalLineEnd = 6
            move[2] == 'h' -> verticalLineEnd = 7
        }
        when {
            move == "exit" -> {
                println("Bye!")
                return
            }
            !move.matches(playersMove) -> {
                println("Invalid Input")
                println("$choosingName's turn:")
                continue@loop
            }
            else -> {
                if (choosingName == fPName) {
                    /*First player*/
                    if (boardList[startPos.toInt() - 1][verticalLineStart] == wPlayer) {
                        if ((verticalLineEnd == verticalLineStart + 1 || verticalLineEnd == verticalLineStart - 1) && endPos.toInt() == startPos.toInt() + 1) {
                            if (verticalLineStart == 7) {
                                if (boardList[endPos.toInt() - 1][verticalLineStart - 1] == 'B') {
                                    boardList[endPos.toInt() - 1][verticalLineStart - 1] = 'W'
                                    boardList[startPos.toInt() - 1][verticalLineStart] = ' '
                                    choosingName = sPName
                                    flag = false
                                } else if (boardList[endPos.toInt() - 1][verticalLineStart - 1] == ' ') {
                                    flag = true
                                } else {
                                    println("Invalid Input")
                                    continue@loop
                                }
                            } else if (verticalLineStart == 0) {
                                if (boardList[endPos.toInt() - 1][1] == 'B') {
                                    boardList[endPos.toInt() - 1][1] = 'W'
                                    boardList[startPos.toInt() - 1][verticalLineStart] = ' '
                                    choosingName = sPName
                                    flag = false
                                } else if (boardList[endPos.toInt() - 1][1] == ' ') {
                                    flag = true
                                } else {
                                    println("Invalid Input")
                                    continue@loop
                                }
                            } else {
                                if (boardList[endPos.toInt() - 1][verticalLineStart - 1] == 'B') {
                                    boardList[endPos.toInt() - 1][verticalLineStart - 1] = 'W'
                                    boardList[startPos.toInt() - 1][verticalLineStart] = ' '
                                    choosingName = sPName
                                    flag = false
                                } else if (boardList[endPos.toInt() - 1][verticalLineStart + 1] == 'B') {
                                    boardList[endPos.toInt() - 1][verticalLineStart + 1] = 'W'
                                    boardList[startPos.toInt() - 1][verticalLineStart] = ' '
                                    choosingName = sPName
                                    flag = false
                                } else if (boardList[endPos.toInt() - 1][verticalLineStart - 1] == ' ' || boardList[endPos.toInt() - 1][verticalLineStart + 1] == ' ') {
                                    flag = true
                                } else {
                                    println("Invalid Input")
                                    continue@loop
                                }
                            }
                        }
                        if (startPos.toInt() == 2 && flag) {
                            if (endPos.toInt() == startPos.toInt() + 2 &&
                                verticalLineEnd == verticalLineStart &&
                                boardList[endPos.toInt() - 1][verticalLineEnd] != bPlayer &&
                                boardList[endPos.toInt() - 2][verticalLineEnd] != bPlayer
                            ) {
                                boardList[startPos.toInt() - 1][verticalLineStart] = ' '
                                boardList[endPos.toInt() - 1][verticalLineEnd] = 'W'
                                choosingName = sPName
                            } else if (verticalLineEnd == verticalLineStart &&
                                endPos.toInt() == startPos.toInt() + 1 &&
                                boardList[endPos.toInt() - 1][verticalLineEnd] != bPlayer
                            ) {
                                boardList[startPos.toInt() - 1][verticalLineStart] = ' '
                                boardList[endPos.toInt() - 1][verticalLineEnd] = 'W'
                                choosingName = sPName
                            } else {
                                println("Invalid Input")
                                continue@loop
                            }
                        } else {
                            if (flag) {
                                if (verticalLineEnd == verticalLineStart &&
                                    endPos.toInt() == startPos.toInt() + 1 &&
                                    boardList[endPos.toInt() - 1][verticalLineEnd] != bPlayer &&
                                    boardList[endPos.toInt() - 2][verticalLineEnd] != bPlayer
                                ) {
                                    boardList[startPos.toInt() - 1][verticalLineStart] = ' '
                                    boardList[endPos.toInt() - 1][verticalLineEnd] = 'W'
                                    choosingName = sPName
                                } else if (startPos.toInt() == 5) {
                                    if (verticalLineEnd == verticalLineStart &&
                                        endPos.toInt() == startPos.toInt() + 1 &&
                                        boardList[endPos.toInt() - 1][verticalLineEnd] != bPlayer &&
                                        boardList[endPos.toInt() - 2][verticalLineEnd] != bPlayer

                                    ) {
                                        boardList[startPos.toInt() - 1][verticalLineStart] = ' '
                                        boardList[endPos.toInt() - 1][verticalLineEnd] = 'W'
                                        choosingName = sPName
                                    } else if (verticalLineEnd == lastOpponentVerticalEndMove && lastOpponentStartPos - 2 == lastOpponentEndPos) {
                                        if (endPos.toInt() == 6 &&
                                            (verticalLineEnd == verticalLineStart + 1 || verticalLineEnd == verticalLineStart - 1)
                                        ) {
                                            boardList[startPos.toInt() - 1][verticalLineStart] = ' '
                                            boardList[startPos.toInt() - 1][verticalLineEnd] = ' '
                                            boardList[endPos.toInt() - 1][verticalLineEnd] = 'W'
                                            choosingName = sPName
                                        } else {
                                            println("Invalid Input")
                                            continue@loop
                                        }
                                    } else {
                                        println("Invalid Input")
                                        continue@loop
                                    }
                                } else {
                                    println("Invalid Input")
                                    continue@loop
                                }
                            }

                        }
                    } else {
                        println("No white pawn at ${move[0]}${move[1]}")
                        continue@loop
                    }

                } else {
                    /*Second player*/
                    if (boardList[startPos.toInt() - 1][verticalLineStart] == bPlayer) {
                        if ((verticalLineEnd == verticalLineStart + 1 || verticalLineEnd == verticalLineStart - 1) && endPos.toInt() == startPos.toInt() - 1) {
                            if (verticalLineStart == 7) {
                                if (boardList[endPos.toInt() - 1][verticalLineStart - 1] == 'W') {
                                    boardList[endPos.toInt() - 1][verticalLineStart - 1] = 'B'
                                    boardList[startPos.toInt() - 1][verticalLineStart] = ' '
                                    choosingName = fPName
                                    flag = false
                                } else if (boardList[endPos.toInt() - 1][verticalLineStart - 1] == ' ') {
                                    flag = true
                                } else {
                                    println("Invalid Input")
                                    continue@loop
                                }
                            } else if (verticalLineStart == 0) {
                                if (boardList[endPos.toInt() - 1][1] == 'W') {
                                    boardList[endPos.toInt() - 1][1] = 'B'
                                    boardList[startPos.toInt() - 1][verticalLineStart] = ' '
                                    choosingName = fPName
                                    flag = false
                                } else if (boardList[endPos.toInt() - 1][1] == ' ') {
                                    flag = true
                                } else {
                                    println("Invalid Input")
                                    continue@loop
                                }
                            } else {
                                if (boardList[endPos.toInt() - 1][verticalLineStart - 1] == 'W') {
                                    boardList[endPos.toInt() - 1][verticalLineStart - 1] = 'B'
                                    boardList[startPos.toInt() - 1][verticalLineStart] = ' '
                                    choosingName = fPName
                                    flag = false
                                } else if (boardList[endPos.toInt() - 1][verticalLineStart + 1] == 'W') {
                                    boardList[endPos.toInt() - 1][verticalLineStart + 1] = 'B'
                                    boardList[startPos.toInt() - 1][verticalLineStart] = ' '
                                    choosingName = fPName
                                    flag = false
                                } else if (boardList[endPos.toInt() - 1][verticalLineStart - 1] == ' ' || boardList[endPos.toInt() - 1][verticalLineStart + 1] == ' ') {
                                    flag = true
                                } else {
                                    println("Invalid Input")
                                    continue@loop
                                }
                            }
                        }
                        if (startPos.toInt() == 7 && flag) {
                            if (verticalLineEnd == verticalLineStart &&
                                endPos.toInt() == startPos.toInt() - 2 &&
                                boardList[endPos.toInt() - 1][verticalLineEnd] != wPlayer &&
                                boardList[endPos.toInt() - 2][verticalLineEnd] != bPlayer
                            ) {
                                boardList[startPos.toInt() - 1][verticalLineStart] = ' '
                                boardList[endPos.toInt() - 1][verticalLineEnd] = 'B'
                                choosingName = fPName
                            } else if (verticalLineEnd == verticalLineStart &&
                                endPos.toInt() == startPos.toInt() - 1 &&
                                boardList[endPos.toInt() - 1][verticalLineEnd] != wPlayer &&
                                boardList[endPos.toInt() - 2][verticalLineEnd] != bPlayer
                            ) {
                                boardList[startPos.toInt() - 1][verticalLineStart] = ' '
                                boardList[endPos.toInt() - 1][verticalLineEnd] = 'B'
                                choosingName = fPName
                            } else {
                                println("Invalid Input")
                                continue@loop
                            }
                        } else {
                            if (flag) {
                                if (verticalLineEnd == verticalLineStart &&
                                    endPos.toInt() == startPos.toInt() - 1 &&
                                    boardList[endPos.toInt() - 1][verticalLineEnd] != wPlayer
//                                    && boardList[endPos.toInt() - 2][verticalLineEnd] != bPlayer
                                ) {
                                    boardList[startPos.toInt() - 1][verticalLineStart] = ' '
                                    boardList[endPos.toInt() - 1][verticalLineEnd] = 'B'
                                    choosingName = fPName
                                } else if (startPos.toInt() == 4) {
                                    if (verticalLineEnd == verticalLineStart &&
                                        endPos.toInt() == startPos.toInt() - 1 &&
                                        boardList[endPos.toInt() - 1][verticalLineEnd] != bPlayer &&
                                        boardList[endPos.toInt() - 2][verticalLineEnd] != bPlayer
                                    ) {
                                        boardList[startPos.toInt() - 1][verticalLineStart] = ' '
                                        boardList[endPos.toInt() - 1][verticalLineEnd] = 'B'
                                        choosingName = fPName
                                    } else if (verticalLineEnd == lastOpponentVerticalEndMove && lastOpponentStartPos + 2 == lastOpponentEndPos) {
                                        if (endPos.toInt() == 3 &&
                                            (verticalLineEnd == verticalLineStart + 1 || verticalLineEnd == verticalLineStart - 1)
                                        ) {
                                            boardList[startPos.toInt() - 1][verticalLineStart] = ' '
                                            boardList[startPos.toInt() - 1][verticalLineEnd] = ' '
                                            boardList[endPos.toInt() - 1][verticalLineEnd] = 'B'
                                            choosingName = fPName
                                        } else {
                                            println("Invalid Input")
                                            continue@loop
                                        }
                                    } else {
                                        println("Invalid Input")
                                        continue@loop
                                    }
                                } else {
                                    println("Invalid Input")
                                    continue@loop
                                }
                            }
                        }
                    } else {
                        println("No black pawn at ${move[0]}${move[1]}")
                        continue@loop
                    }
                }
                lastOpponentVerticalEndMove = verticalLineEnd
                lastOpponentStartPos = startPos.toInt()
                lastOpponentEndPos = endPos.toInt()
            }
        }

        boardGrid = """
  +---+---+---+---+---+---+---+---+
8 | ${boardList[7][0]} | ${boardList[7][1]} | ${boardList[7][2]} | ${boardList[7][3]} | ${boardList[7][4]} | ${boardList[7][5]} | ${boardList[7][6]} | ${boardList[7][7]} |
  +---+---+---+---+---+---+---+---+
7 | ${boardList[6][0]} | ${boardList[6][1]} | ${boardList[6][2]} | ${boardList[6][3]} | ${boardList[6][4]} | ${boardList[6][5]} | ${boardList[6][6]} | ${boardList[6][7]} |
  +---+---+---+---+---+---+---+---+
6 | ${boardList[5][0]} | ${boardList[5][1]} | ${boardList[5][2]} | ${boardList[5][3]} | ${boardList[5][4]} | ${boardList[5][5]} | ${boardList[5][6]} | ${boardList[5][7]} |
  +---+---+---+---+---+---+---+---+
5 | ${boardList[4][0]} | ${boardList[4][1]} | ${boardList[4][2]} | ${boardList[4][3]} | ${boardList[4][4]} | ${boardList[4][5]} | ${boardList[4][6]} | ${boardList[4][7]} |
  +---+---+---+---+---+---+---+---+
4 | ${boardList[3][0]} | ${boardList[3][1]} | ${boardList[3][2]} | ${boardList[3][3]} | ${boardList[3][4]} | ${boardList[3][5]} | ${boardList[3][6]} | ${boardList[3][7]} |
  +---+---+---+---+---+---+---+---+
3 | ${boardList[2][0]} | ${boardList[2][1]} | ${boardList[2][2]} | ${boardList[2][3]} | ${boardList[2][4]} | ${boardList[2][5]} | ${boardList[2][6]} | ${boardList[2][7]} |
  +---+---+---+---+---+---+---+---+
2 | ${boardList[1][0]} | ${boardList[1][1]} | ${boardList[1][2]} | ${boardList[1][3]} | ${boardList[1][4]} | ${boardList[1][5]} | ${boardList[1][6]} | ${boardList[1][7]} |
  +---+---+---+---+---+---+---+---+
1 | ${boardList[0][0]} | ${boardList[0][1]} | ${boardList[0][2]} | ${boardList[0][3]} | ${boardList[0][4]} | ${boardList[0][5]} | ${boardList[0][6]} | ${boardList[0][7]} |
  +---+---+---+---+---+---+---+---+
    a   b   c   d   e   f   g   h
    """.trimIndent()
        println(boardGrid)

        var countW = 0
        var countB = 0
//Checking if there's not White-pawn or Black-pawn on the board
        for (i in boardList) {
            for (j in i) {
                if (j == 'W') {
                    countW++
                } else if (j == 'B') {
                    countB++
                }
            }
        }
        if (countB == 0) {
            println("White Wins!\nBye!")
            println("No black pawns")
            break@loop
        } else if (countW == 0) {
            println("Black Wins!\nBye!")
            println("No White pawns")
            break@loop
        }
//Checking if a player Manages to reach the first level of the opponent
        var incBlockW = 0
        var incBlockB = 0
        for (i in boardList.indices) {
            if (i == 7) {
                for (j in boardList[i]) {
                    if (j == 'W') {
                        println("White Wins!\nBye!")
                        break@loop
                    }
                }
            } else if (i == 0) {
                for (j in boardList[i]) {
                    if (j == 'B') {
                        println("Black Wins!\nBye!")
                        break@loop
                    }
                }
            }
//Checking stalemate condition
            for (j in boardList[i].indices) {
                if (i in 1..6) {
                    if (boardList[i][j] == 'W' && boardList[i + 1][j] == 'B') {
                        if (j == 0) {
                            if (boardList[i + 1][1] != 'B') {
                                incBlockW++
                            }
                        } else if (j == 7) {
                            if (boardList[i - 1][6] != 'B') {
                                incBlockW++
                            }
                        } else {
                            if (boardList[i + 1][j + 1] != 'B' || boardList[i + 1][j - 1] != 'B') {
                                incBlockW++
                            }
                        }
                    } else if (boardList[i][j] == 'B' && boardList[i - 1][j] == 'W') {
                        if (j == 0) {
                            if (boardList[i + 1][1] != 'W') {
                                incBlockB++
                            }
                        } else if (j == 7) {
                            if (boardList[i - 1][6] != 'W') {
                                incBlockB++
                            }
                        } else {
                            if (boardList[i + 1][j + 1] != 'W' || boardList[i + 1][j - 1] != 'W') {
                                incBlockB++
                            }
                        }
                    }
                }
            }
        }
        if (incBlockW == countW) {
            stalemate = true
        }
        if (incBlockB == countB) {
            stalemate = true
        }
    }
}