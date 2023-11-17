package connect;
import connect.model.BoardLogicInterface;
import java.util.Arrays;

public class GameBoard implements  BoardLogicInterface{
  private int row;
  private int column;
  private final int[][] boardTable;


  /**
   * In case of custom table, but this game will only
   * be played with default connect four table size and dimensions.
   * @param row This indicates number of rows for the boardGame
   * @param column This indicates number of columns for the boardGame
   */
  private GameBoard (int row, int column) {
    boardTable = new int[row][column];
  }

  /**
   * This project will use this contractor
   * as default and only possible boardgame size.
   */
  public GameBoard () {
    boardTable = new int[6][7];
  }

  public GameBoard(int[][] arr) {
    this.boardTable = arr;
  }

  @Override
  public boolean addToBoard(int position, int playerCard) {
    // ADD filter condition to control method
    try {
      for (int i = boardTable.length-1; i >= 0; --i) {
        if (boardTable[i][--position] == 0) {
          boardTable[i][position] = playerCard;
          return true;
        } else
          ++position;
      }
    } catch (IndexOutOfBoundsException e) {
      System.out.println("Column " + ++position+ " does not exist.");
    }

    return false;
  }

  public boolean checkWinnerXAndYAxis(int playerCard) {
    int sum = 0;
    int value = playerCard * 4;

    for(int i = boardTable.length-1; i >= 0; --i) {
      for(int j = 0; j < boardTable[i].length; ++j) {
        int check = boardTable[i][j] == playerCard ?
                playerCard : (-1) * sum;
        sum += check;
        if (sum == value) return true;
      }
    }

    sum = 0;

    for(int i = 0; i < boardTable[0].length; ++i) {
      for(int k = boardTable.length-1; k >= 0; --k) {
        sum += boardTable[k][i] == playerCard ?
                playerCard : (-1) * sum;
        if (sum == value) return true;
      }
    }

    return false;
  }

  public boolean sortLeftDiagonalSecond(int playerCard ) {
    int movingIndex,sum = 0, value = (playerCard * 4);

    for(int i = 1; i < boardTable[0].length; ++i) {
      movingIndex = i;
      for (int j = 0; j < boardTable.length && movingIndex <= boardTable[0].length-1; ++j) {
        int check = boardTable[j][movingIndex] ==
                playerCard ? playerCard : (-1) * sum;
        sum += check;
        movingIndex += 1;
        if (sum == value) return true;
      }
    }

    return false;
  }

  public boolean sortRightDiagonalFirst(int playerCard) {
    int movingIndex = boardTable[0].length - 1, sum = 0, value = (playerCard*4);

    for(int i = boardTable.length-1; i >= 0; --i) {
      for (int j = i; j >= 0 && movingIndex >= 0; --j) {
        int check = boardTable[j][movingIndex--] ==
                playerCard ? playerCard : (-1) * sum;
        sum += check;
//        movingIndex -= 1; // Todo replaced with line 134 movingIndex--
        if (sum == value) return true;
      }
      movingIndex = boardTable[0].length-1;
    }

    return false;
  }

  public boolean sortRightDiagonalSecond(int playerCard) {
    int movingIndex, sum = 0, value = (playerCard*4);

    for(int i = boardTable.length-1; i >= 0; --i) {
      movingIndex = 0;
      for (int j = i; j >= 0 && movingIndex <= boardTable[0].length-1; --j) {
        int check = boardTable[j][movingIndex++] ==
                playerCard ? playerCard : (-1) * sum;
        sum += check;
//        movingIndex += 1; // Todo replaced with line 134 movingIndex++
        if (sum == value) return true;
      }
    }

    return false;
  }

  @Override
  public boolean sortLeftDiagonalFirst(int playerCard) {
    int movingIndex, sum = 0, value = (playerCard * 4);
    for(int i = boardTable.length-4; i >= 0; --i) {
      movingIndex = 0;
      for (int j = i; j < boardTable.length; ++j) {
        int check = boardTable[j][movingIndex++] ==
                playerCard ? playerCard : (-1) * sum;
        sum += check;
//        movingIndex += 1; Todo replaced with line 134 movingIndex++
        if (sum == value) return true;
      }
    }

    return false;
  }

  @Override
  public boolean checkGridIsFilled() {
    int[] grid = boardTable[0];
    for (int box : grid) {
      if(box == 0) return false;
    }
    return true;
  }

  public void printBoard() {
    System.out.print("""
                  A        B        C        D        E        F        G\s

            """);

    for (int[] ints : boardTable) {
      for (int anInt : ints) {
        System.out.print("   |  "+anInt+" |");
      }
      System.out.println("\n");
    }
  }

}
