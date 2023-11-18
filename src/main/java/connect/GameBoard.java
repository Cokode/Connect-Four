package connect;
import connect.model.BoardLogicInterface;

public class GameBoard implements  BoardLogicInterface{
  private final int[][] boardTable;

  /**
   * Constructs a Connect Four game using custom table dimensions.
   *
   * @param row    The number of rows for the custom boardGame
   * @param column The number of columns for the custom boardGame
   */

  private GameBoard (int row, int column) {
    boardTable = new int[row][column];
  }

  /**
   * This method sets up a Connect Four game using the default table size and dimensions.
   * The game is played on a standard Connect Four board.
   *
   *  `Row` The number of rows for the Connect Four board (default size)
   * `column` The number of columns for the Connect Four board (default size)
   */

  public GameBoard () {
    int column = 6;
    int row = 7;
    boardTable = new int[column][row];
  }

  /**
   * Constructs an instance of the class, primarily intended for testing purposes.
   * It allows users to provide a customized 2D array to test the accuracy of
   * the class methods in sorting the array.
   *
   * @param arr An Integer 2D array that may contain pre-inputted elements for testing purposes.
   */

  public GameBoard(int[][] arr) {
    this.boardTable = arr;
  }


  /**
   * Adds a player's card to the game board in the specified column.
   *
   * @param column     The column index where the player wants to place their card.
   * @param playerCard The integer value representing the player's card.
   * @return True if the card was successfully added, false otherwise.
   *         Returns false if the column is full or an invalid column index is provided.
   */
  @Override
  public boolean addToBoard(int column, int playerCard) {
    if (column < 1 || column > boardTable[0].length) {
      System.out.println("Column " + column + " is invalid.");
      return false;
    }

    for (int row = boardTable.length - 1; row >= 0; row--) {
      if (boardTable[row][--column] == 0) {
        boardTable[row][column] = playerCard;
        return true;
      }
      ++column; // todo  remove if does not fix the IOB issue
    }

    System.out.println("Column " + column + " is full.");
    return false;
  }

  /**
   * Checks for a winning pattern for the provided player card in both X and Y axes on the game board.
   *
   * @param playerCard The integer value representing the player's card.
   * @return True if a winning pattern is found in either X or Y axes, false otherwise.
   */
  public boolean checkWinnerXAndYAxis(int playerCard) {
    int winValue = playerCard * 4;

    // Checking rows for a winning pattern
    for (int row = boardTable.length - 1; row >= 0; row--) {
      int rowSum = 0;
      for (int column = 0; column < boardTable[row].length; column++) {
        int check = boardTable[row][column] == playerCard ? playerCard : -rowSum;
        rowSum += check;
        if (rowSum == winValue) return true;
      }
    }

    // Checking columns for a winning pattern
    for (int column = 0; column < boardTable[0].length; column++) {
      int colSum = 0;
      for (int row = boardTable.length - 1; row >= 0; row--) {
        int check = boardTable[row][column] == playerCard ? playerCard : -colSum;
        colSum += check;
        if (colSum == winValue) return true;
      }
    }

    return false;
  }

  /**
   * Checks if the provided player card is arranged diagonally from the second column to the last column.
   * The diagonal is formed from the top-left to the bottom-right direction.
   *
   * @param playerCard The integer value representing the player's card.
   * @return True if the player's card is arranged diagonally, false otherwise.
   */
  public boolean sortLeftDiagonalSecond(int playerCard) {
    int sum = 0;
    int value = playerCard * 4;

    for (int i = 1; i < boardTable[0].length; ++i) {
      int movingIndex = i;
      for (int j = 0; j < boardTable.length && movingIndex <= boardTable[0].length - 1; ++j) {
        int check = boardTable[j][movingIndex] == playerCard ? playerCard : (-1) * sum;
        sum += check;
        movingIndex += 1;
        if (sum == value) {
          return true;
        }
      }
    }

    return false;
  }


  /**
   * Checks if the specified player's card forms a winning sequence diagonally from
   * the top-right to the bottom-left direction on the game board.
   *
   * @param playerCard The integer value representing the player's card.
   * @return True if a winning sequence is found, false otherwise.
   */
  public boolean sortRightDiagonalFirst(int playerCard) {
    int rows = boardTable.length;
    int cols = boardTable[0].length;
    int value = playerCard * 4;

    for (int i = rows - 1; i >= 0; --i) {
      int movingIndex = cols - 1;
      int sum = 0;

      for (int j = i; j >= 0 && movingIndex >= 0; --j) {
        int check = boardTable[j][movingIndex--] == playerCard ? playerCard : -sum;
        sum += check;

        if (sum == value) {
          return true;
        }
      }
    }

    return false;
  }


  /**
   * Checks if the player's card sequence appears diagonally in the bottom right
   * half of the game board.
   *
   * @param playerCard The integer value representing the player's card.
   * @return True if the card sequence appears diagonally, false otherwise.
   */
  public boolean sortRightDiagonalSecond(int playerCard) {
    int sum = 0;
    int value = playerCard * 4; // Assuming a sequence of 4 cards in a row

    for (int i = boardTable.length - 1; i >= 0; i--) {
      int movingIndex = 0;
      for (int j = i; j >= 0 && movingIndex < boardTable[0].length; j--) {
        int check = boardTable[j][movingIndex] == playerCard ? playerCard : (-1) * sum;
        sum += check;
        movingIndex++;
        if (sum == value) {
          return true;
        }
      }
    }

    return false;
  }


  /**
   * Checks if the board has a winning sequence diagonally from left to right.
   *
   * @param playerCard The player's card value to check for in the sequence.
   * @return True if a winning sequence is found diagonally from left to right for the player's card, false otherwise.
   */
  @Override
  public boolean sortLeftDiagonalFirst(int playerCard) {
    int requiredSum = playerCard * 4;
    int sum = 0;

    for (int i = boardTable.length - 4; i >= 0; --i) {
      int columnIndex = 0;
      for (int j = i; j < boardTable.length; ++j) {
        int check = boardTable[j][columnIndex++] == playerCard ? playerCard : -sum;
        sum += check;

        if (sum == requiredSum) {
          return true;
        }
      }
    }

    return false;
  }


  /**
   * Checks if the grid is completely filled (no empty boxes).
   *
   * @return True if the grid is filled, false otherwise.
   */
  @Override
  public boolean checkGridIsFilled() {
    for (int[] row : boardTable) {
      for (int box : row) {
        if (box == 0) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Prints the current game board to the console.
   * Displays the column headers and the current state of the board.
   */
  public void printBoard() {
    System.out.println("      A        B        C        D        E        F        G\n");

    for (int[] row : boardTable) {
      for (int cell : row) {
        System.out.print("   |  "+cell+" |");
        //System.out.print(" | " + cell + " |");
      }
      System.out.println("\n");
    }
  }

}
