/*
* This program uses recursion to find all the magic squares of 15 in java.
*
* @author  Douglass Jeffrey
* @version 1.0
* @since   2021-01-08
*/

// Import the Scanner class
import java.util.Random;
// Import the Scanner class
import java.util.Scanner;



public class TicTacToe {

  /**
   * Main function to recieve user input and call tictatoe bot function.
   */
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
      System.out.println("\nWhich space would you like to put the X? ");
      if (input.hasNextInt()) {
        int space = input.nextInt();

        if (space >= 1 && space <= 9 && isNumeric(board[space - 1])) {
          board[space - 1] = "X";

          // place a function call here to get the best move for O
          // set the variable that is used to stop recursion to 0
          int countPlace = 0;
          //call the O move calculator
          compNextMove(board, countPlace);

          //After O moves print board
          printBoard(board);

        } else if (board[space - 1].equalsIgnoreCase("X") 
                   || board[space - 1].equalsIgnoreCase("O")) {
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
      
      // check to see if anyone wins
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
  
  /**
   * function to check wins or losses for bot and player.
   */
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

  /**
   * calculates the best next move for computer based on current board.
   */
  public static String [] compNextMove(String [] currentBoard, int countPlace) {
    // try to catch an out of bounds error
    try {
      // if countplace > 9 keep recursing.
      if (countPlace < 9) {
                
        // find where is numeric on the board
        if (isNumeric(currentBoard[countPlace])) {
          
          // hold the value of a location in the array
          String valHolder = currentBoard[countPlace];
          // set the value of said location to "O"
          currentBoard[countPlace] = "O";
          
          //check if victory would be achieved in said scenario
          if (winOrLost(currentBoard, "O")) {
            //set the value that causes victory to "O"
            currentBoard[countPlace] = "O";
            //increase countplace to induce a outofbounds error
            countPlace += 30;
                    
          // if victory cannot be achieved reset the value of array
          } else {
            // reset the value of a location in the array
            currentBoard[countPlace] = valHolder;
          }
                    
          // set the value of said location to "X"
          currentBoard[countPlace] = "X";
                    
          //check if x victory possible
          if (winOrLost(currentBoard, "X")) {
            //if it is block it
            currentBoard[countPlace] = "O";
            //increase loop variable to prevent further looping
            countPlace += 30;

          } else {
            // reset the value of a location in the array
            currentBoard[countPlace] = valHolder;
          }
        }
        // recursive call.
        compNextMove(currentBoard, countPlace + 1);
    
      } else if (countPlace > 8 && countPlace < 20) {
        int randomNum = (int) (Math.random() * (3 - 0 + 1) + 0);
                
        if (isNumeric(currentBoard[4])) {
          currentBoard[4] = "O";
                
        // eliminating extreme cases where player victory may be possible
        } else if ((currentBoard[4]).equals("O")
                    && (currentBoard[0]).equals("X")
                    && (currentBoard[8]).equals("X")
                    && isNumeric(currentBoard[1])
                    && isNumeric(currentBoard[3])
                    && isNumeric(currentBoard[5])
                    && isNumeric(currentBoard[7])) {
                               
          if (randomNum == 0 && isNumeric(currentBoard[1])) {
            currentBoard[1] = "O";

          } else if (randomNum == 1 && isNumeric(currentBoard[3])) {
            currentBoard[3] = "O";

          } else if (randomNum == 2 && isNumeric(currentBoard[5])) {
            currentBoard[5] = "O";

          } else if (randomNum == 3 && isNumeric(currentBoard[7])) {
            currentBoard[7] = "O";
          }

        // eliminating MORE extreme cases where player victory may be possible
        } else if ((currentBoard[4]).equals("O") 
                    && (currentBoard[2]).equals("X")
                    && (currentBoard[6]).equals("X")
                    && isNumeric(currentBoard[1])
                    && isNumeric(currentBoard[3])
                    && isNumeric(currentBoard[5])
                    && isNumeric(currentBoard[7])) {
                               
          if (randomNum == 0 && isNumeric(currentBoard[1])) {
            currentBoard[1] = "O";

          } else if (randomNum == 1 && isNumeric(currentBoard[3])) {
            currentBoard[3] = "O";

          } else if (randomNum == 2 && isNumeric(currentBoard[5])) {
            currentBoard[5] = "O";
                    
          } else if (randomNum == 3 && isNumeric(currentBoard[7])) {
            currentBoard[7] = "O";
          }

        } else if ((currentBoard[4]).equals("O")
                    && (currentBoard[1]).equals("X")
                    && (currentBoard[3]).equals("X")
                    && isNumeric(currentBoard[0])
                    && isNumeric(currentBoard[2])
                    && isNumeric(currentBoard[5])
                    && isNumeric(currentBoard[6])
                    && isNumeric(currentBoard[7])
                    && isNumeric(currentBoard[8])) {
          currentBoard[0] = "O";
                    
        } else if ((currentBoard[4]).equals("O")
                    && (currentBoard[1]).equals("X")
                    && (currentBoard[5]).equals("X")
                    && isNumeric(currentBoard[0])
                    && isNumeric(currentBoard[2])
                    && isNumeric(currentBoard[3])
                    && isNumeric(currentBoard[6])
                    && isNumeric(currentBoard[7])
                    && isNumeric(currentBoard[8])) {
          currentBoard[2] = "O";
                    
        } else if ((currentBoard[4]).equals("O")
                    && (currentBoard[3]).equals("X")
                    && (currentBoard[7]).equals("X")
                    && isNumeric(currentBoard[0])
                    && isNumeric(currentBoard[1])
                    && isNumeric(currentBoard[2])
                    && isNumeric(currentBoard[5])
                    && isNumeric(currentBoard[6])
                    && isNumeric(currentBoard[8])) {
          currentBoard[6] = "O";
                    
        } else if ((currentBoard[4]).equals("O")
                    && (currentBoard[5]).equals("X")
                    && (currentBoard[7]).equals("X")
                    && isNumeric(currentBoard[0])
                    && isNumeric(currentBoard[1])
                    && isNumeric(currentBoard[2])
                    && isNumeric(currentBoard[3])
                    && isNumeric(currentBoard[6])
                    && isNumeric(currentBoard[8])) {
          currentBoard[8] = "O";
                    
        } else if (randomNum == 1 && isNumeric(currentBoard[2])) {
          currentBoard[2] = "O";

        } else if (randomNum == 2 && isNumeric(currentBoard[6])) {
          currentBoard[6] = "O";

        } else if (randomNum == 3 && isNumeric(currentBoard[8])) {
          currentBoard[8] = "O";
                    
        } else if (randomNum == 0 && isNumeric(currentBoard[1])) {
          currentBoard[1] = "O";
                    
        } else if (randomNum == 1 && isNumeric(currentBoard[3])) {
          currentBoard[3] = "O";

        } else if (randomNum == 2 && isNumeric(currentBoard[5])) {
          currentBoard[5] = "O";
                    
        } else if (randomNum == 3 && isNumeric(currentBoard[7])) {
          currentBoard[7] = "O";
        }
      }
      return currentBoard;
    /*
     * when an outofbounds error is caught it will only ever be because 
     * a win condition was found
     */
    } catch (ArrayIndexOutOfBoundsException exception) {
      return currentBoard;
    }
  }

  /**
   * returns whether board is full or not.
   */
  public static boolean isFull(String[] presentBoard) {
    boolean full = true;
    for (int counter = 0; counter < presentBoard.length; counter++) {
      if (isNumeric(presentBoard[counter])) {
        full = false;
        break;
      } 
    }
    return full;
  }

  /**
   * Prints out board.
   */
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

  /**
   * checks if spot in array is numeric.
   */
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
