/**
 * Main Method
 * @author Michael Ruocco
 * @date 2/2/2023
 */
val FIR_board = FourInARow()

/** The entry main method (the program starts here)  */
fun main() {
    var currentState: Int = GameConstants.PLAYING
    FIR_board.printBoard()
    var userInput = ""
    println("Welcome to connect Four,\nyou are player B.\nEnter 'Q' at any time to quit.")
    //game loop
    do {
        // detect player spot or quit
        println("Please enter your move (1-6).")
        userInput = readln().uppercase()
        if (userInput == "Q"){
            currentState = GameConstants.EMPTY
        }
        var playerMove = try {
            (userInput).toInt() - 1
        } catch (e: NumberFormatException) {
            -1
        }

        if (userInput != "Q") {
            // check for invalid input
            while (playerMove <= -1 || playerMove >= GameConstants.COLS) {
                println("Invalid input, pick another slot.")
                println("Please enter your move (1-6).")
                playerMove = try {
                    (readln() ?: "").toInt() - 1
                } catch (e: NumberFormatException) {
                    -1
                }
            }

            // set player piece, check if won
            FIR_board.setMove(1, playerMove)
            var winner = FIR_board.checkForWinner()

            // set cp piece, check if won
            if (winner != 1) {
                FIR_board.setMove(2, FIR_board.computerMove)
                winner = FIR_board.checkForWinner()
            }
            FIR_board.printBoard()
            when (winner) {
                1 -> {
                    currentState = GameConstants.BLUE_WON
                    println("Congrats, you win!")
                }

                2 -> {
                    currentState = GameConstants.RED_WON
                    println("Ooof, you lost.")
                }

                3 -> {
                    currentState = GameConstants.TIE
                    println("It's a tie.")
                }
            }

            if (currentState != GameConstants.PLAYING) {
                println("Play Again? (Y/N)")
                val repeat = readln().uppercase()
                if (repeat == "Y") {
                    FIR_board.clearBoard()
                    FIR_board.printBoard()
                    currentState = GameConstants.PLAYING
                } else {
                    userInput = "Q"
                }
            }
        }

        if (userInput == "Q"){
            println("Thanks for playing. Goodbye.")
        }

    } while (currentState == GameConstants.PLAYING && userInput != "Q")


// repeat if not game-over
}
 