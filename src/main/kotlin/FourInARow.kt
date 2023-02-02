/**
 * TicTacToe class implements the interface
 * @author Michael Ruocco
 * @date 2/2/2023
 */
class FourInARow
/**
 * clear board and set current player
 */
    : IGame {
    // game board in 2D array initialized to zeros
    private val board = Array(GameConstants.ROWS) { IntArray(GameConstants.COLS) { 0 } }

    // resets board
    override fun clearBoard() {
        for (row in 0 until GameConstants.ROWS) {
            for (col in 0 until GameConstants.COLS) {
                board[row][col] = 0
            }
        }
    }

    // sets player or cp piece
    override fun setMove(player: Int, location: Int) {
        var placed = false
        var spot = location

        while (!placed) {
            for (row in GameConstants.ROWS - 1 downTo 0) {
                if (spot in 0..5) {
                    if (board[row][spot] == 0 && !placed) {
                        board[row][spot] = player
                        placed = true
                    } else {
                    }
                } else {
                }
            }

            // if trying to place in a full column
            if (!placed) {
                spot = if (player == 1) {
                    println("Invalid input, pick another slot.")
                    try {
                        (readln() ?: "").toInt() - 1
                    } catch (e: NumberFormatException) {
                        -1
                    }
                } else {
                    computerMove
                }
            }
        }
    }

    // determines cp move
    override val computerMove: Int
        get() = (0..5).random()

    // return value: 0 = no winner, 1 = player wins, 2 = cp wins, 3 = tie
    override fun checkForWinner(): Int {

        // checks for a tie, if the top row is full at start of a new cycle
        if (!board[0].contains(0)) {
            return 3
        }

        for (row in 0 until GameConstants.ROWS) {
            for (col in 0 until GameConstants.COLS) {
                val inPlayer = board[row][col]
                var countRow: Int
                var countCol: Int
                var outBounds = false
                if (inPlayer != 0) {
                    //check horizontal
                    countCol = col
                    while (board[row][countCol] == inPlayer && !outBounds) {
                        if (countCol + 1 < GameConstants.COLS) {
                            countCol++
                        } else {
                            outBounds= true
                        }
                    }

                    // this is here to still add a piece to the count if it's on the edge
                    if (outBounds) {
                        countCol++
                    }

                    if (countCol - col >= 4) {
                        if (inPlayer == 1) {
                            return 1
                        } else if (inPlayer == 2) {
                            return 2
                        }
                    }

                    //check vertical
                    countRow = row
                    outBounds = false
                    while (board[countRow][col] == inPlayer && !outBounds) {
                        if (countRow + 1 < GameConstants.ROWS) {
                            countRow++
                        } else {
                            outBounds = true
                        }
                    }

                    if (outBounds) {
                        countRow++
                    }

                    if (countRow - row >= 4) {
                        if (inPlayer == 1) {
                            return 1
                        } else if (inPlayer == 2) {
                            return 2
                        }
                    }

                    //check diagDown
                    countRow = row
                    countCol = col
                    outBounds = false
                    while (board[countRow][countCol] == inPlayer && !outBounds) {
                        if (countRow + 1 < GameConstants.ROWS && countCol + 1 < GameConstants.COLS) {
                            countRow++
                            countCol++
                        } else {
                            outBounds = true
                        }
                    }

                    if (outBounds) {
                        countRow++
                    }

                    if (countRow - row >= 4) {
                        if (inPlayer == 1) {
                            return 1
                        } else if (inPlayer == 2) {
                            return 2
                        }
                    }

                    //check diagUp
                    countRow = row
                    countCol = col
                    outBounds = false
                    while (board[countRow][countCol] == inPlayer && !outBounds) {
                        if (countRow + 1 < GameConstants.ROWS && countCol - 1 > 0) {
                            countRow++
                            countCol--
                        } else {
                            outBounds = true
                        }
                    }

                    if (outBounds) {
                        countRow++
                    }

                    if (countRow - row >= 4) {
                        if (inPlayer == 1) {
                            return 1
                        } else if (inPlayer == 2) {
                            return 2
                        }
                    }
                }
            }
        }
        return 0
    }

    /**
     * Print the game board
     */
    fun printBoard() {
        for (row in 0 until GameConstants.ROWS) {
            for (col in 0 until GameConstants.COLS) {
                printCell(board[row][col]) // print each of the cells
                if (col != GameConstants.COLS - 1) {
                    print("|") // print vertical partition
                }
            }
            println()
            if (row != GameConstants.ROWS - 1) {
                println("-----------------------") // print horizontal partition
            }
        }
        println("-1---2---3---4---5---6")
        println()
    }

    /**
     * Print a cell with the specified "content"
     * @param content either BLUE, RED or EMPTY
     */
    private fun printCell(content: Int) {
        when (content) {
            GameConstants.EMPTY -> print("   ")
            GameConstants.BLUE -> print(" B ")
            GameConstants.RED -> print(" R ")
        }
    }
}

