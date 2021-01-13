/*
* This program uses recursion to find all the magic squares of 15 in java.
*
* @author  Douglass Jeffrey
* @version 1.0
* @since   2021-01-08
*/

// Import the Scanner class
import java.util.Scanner;

// Import the Scanner class
import java.util.Random;

public class TicTacToe {

    public static void main(String[] args) {
        // main stub, get user input here
        boolean boardFull = false;
        boolean checkWinnerX = false;
        boolean checkWinnerO = false;

        Scanner input = new Scanner(System.in);
        String[] board = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        System.out.println("Welcome to tic tac toe!\n");

        printBoard(board);
        do {
            System.out.println("\nWhich space would you like to the X? ");
            if (input.hasNextInt()) {
                int space = input.nextInt();

                if (space >= 1 && space <= 9 && isNumeric(board[space-1])) {
                    board[space-1] = "X";

                    // place a function call here to get the best move for O
                    // set the variable that is used to stop recursion to 0
                    int countPlace = 0;
                    
                    //call the O move calculator
                    compNextMove(board, countPlace);
                    
                    //After O moves print board
                    printBoard(board);
                
                } else if (board[space-1].equalsIgnoreCase("X") || board[space-1].equalsIgnoreCase("O")) {
                    System.out.println("That spot's taken!");
                    printBoard(board);
                } else {
                    System.out.println("Error");
                    break;
                }
            } else {
                System.out.println("Error");
                break;
            }

            checkWinnerX = winOrLost(board, "X");
            checkWinnerO = winOrLost(board, "O");
            if (checkWinnerX == true) {
                System.out.println("\nX has won.");
                break;
            } else if (checkWinnerO == true) {
                System.out.println("\nO has won.");
                break;
            }

            boardFull = isFull(board);
        } while (boardFull == false);
        
        System.out.println("\nGame Over.");
    }

    public static boolean winOrLost(String[] board, String takenSpace) {
        // returns true or false for whether or not inputted array is a magic square
        if ((board[0] == takenSpace && board[1] == takenSpace && board[2] == takenSpace)
                || (board[3] == takenSpace && board[4] == takenSpace && board[5] == takenSpace)
                || (board[6] == takenSpace && board[7] == takenSpace && board[8] == takenSpace)
                || (board[0] == takenSpace && board[3] == takenSpace && board[6] == takenSpace)
                || (board[1] == takenSpace && board[4] == takenSpace && board[7] == takenSpace)
                || (board[2] == takenSpace && board[5] == takenSpace && board[8] == takenSpace)
                || (board[0] == takenSpace && board[4] == takenSpace && board[8] == takenSpace)
                || (board[2] == takenSpace && board[4] == takenSpace && board[6] == takenSpace)) {
            return true;
        } else {
            return false;
        }
    }

    public static String [] compNextMove(String [] currentBoard, int countPlace) {
        // calculates the best next move for computer based on current board
        String[] newBoard = currentBoard;

        if (isNumeric(newBoard[4])) {
            // Always take middle if possible
            currentBoard[4] = "O";
            return currentBoard;

        } else {
            // <------------------
            // find the move for O, where it will win the game; using recursion
            // <------------------
            // If a slot is numeric, check if any moves made around it will cause victory
            if (isNumeric(newBoard[countPlace])) {

                // holds the value of the number at the countPlaceth place
                String valHolder = newBoard[countPlace];

                // set the value of countplace to O to check if victory is possible
                newBoard[countPlace] = "O";

                // check if victory can be made if O is put at countplace slot
                if (winOrLost(newBoard[countPlace], "O")) {
                    // if it can then move and win
                    currentBoard[countPlace] = "O";
                    return currentBoard;
                }

                // set the value of countplace to X to check if victory is possible
                newBoard[countPlace] = "X";

                // check if victory can be made if X is put at countplace slot
                if (winOrLost(newBoard[countPlace], "X")) {

                    // if it can then block
                    currentBoard[countPlace] = "O";
                    return currentBoard;
                }

                /*
                 * if neither a victory or a block is possible, reset the value
                 * of the the array at the countplaceth spot to its initial
                 * value
                 */
                
                newBoard[countPlace] = valHolder;
                
            } else {
                if (countPlace < 9) {
                    compNextMove(currentBoard, countPlace + 1);
                }

                int randomNum = rand.nextInt((3 - 0) + 1) + 0;

                if (randomNum == 0 && isNumeric(newBoard[0])) {
                    currentBoard[0] = "O";
                    return currentBoard;

                } else if (randomNum == 1 && isNumeric(newBoard[2])) {
                    currentBoard[2] = "O";
                    return currentBoard;
                    
                } else if (randomNum == 2 && isNumeric(newBoard[6])) {
                    currentBoard[6] = "O";
                    return currentBoard;
                        
                } else if (randomNum == 3 && isNumeric(newBoard[8])) {
                    currentBoard[8] = "O";
                    return currentBoard;

                } else {

                    if (randomNum == 0 && isNumeric(newBoard[1])) {
                        currentBoard[1] = "O";
                        return currentBoard;
    
                    } else if (randomNum == 1 && isNumeric(newBoard[3])) {
                        currentBoard[3] = "O";
                        return currentBoard;
                        
                    } else if (randomNum == 2 && isNumeric(newBoard[5])) {
                        currentBoard[5] = "O";
                        return currentBoard;

                    } else if (randomNum == 3 && isNumeric(newBoard[7])) {
                        currentBoard[7] = "O";
                        return currentBoard;

                    } else {
                        throw null;
                    }

                currentBoard[slotSelect] = "O";
                return currentBoard;
                }
            }
        }
    }
    

    public static boolean isFull(String[] presentBoard) {
        // returns whether board is full or not
        boolean full = true;
        for (int counter = 0; counter < presentBoard.length; counter++) {
            if (isNumeric(presentBoard[counter])) {
                full = false;
                break;
            } 
        }
        return full;
    }

    public static void printBoard(String[] theBoard) {
        // prints current game state
        System.out.println("----+----+----");
        for (int count = 0; count < 9; count++) {
            if (count == 2 || count == 5 || count == 8) {
                System.out.print("| " + theBoard[count] + " |\n");
                System.out.println("----+----+----");
            } else {
                System.out.print("| " + theBoard[count] + " ");
            }
        }
    }


    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}