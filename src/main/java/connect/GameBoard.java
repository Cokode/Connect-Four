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
    for (int i = boardTable.length-1; i > 0; --i) {
      if (boardTable[i][--position] == 0) {
        boardTable[i][position] = playerCard;
        return true;
      } else
        --i;
      ++position;
    }

    return false;
  }

  /**

   * @param playCard TODO
   * @return TODO
   */
  @Override
  public boolean checkForWinnerVertical(int playCard) {
    int sum = 0;
    int i =  boardTable.length-1, column = boardTable[0].length;
    int index = 0;

    while (i >= 0) {
      int[] temp = boardTable[i];

      if(index != boardTable[0].length && ((temp[index] == playCard) && sum != (playCard * 4))) {
        sum += temp[index];
        --i;
      } else if (sum == (playCard * 4)){
        return true;
      } else {
        sum = 0;

        if (i < 3 && column != 0) { //remove this if statement if it fails
          i = boardTable.length-1;
          column--;
          index++;
        }
        --i;
//        if (i < 3 && column != 0) {
//          i = boardTable.length-1;
//          column--;
//          index++;
//        }
      }
    }

    return sum == playCard*4;
  }

  public boolean checkWinner(int playerCard) {
    int sum = 0;
    int value = playerCard * 4;

    for(int i = boardTable.length-1; i >= 0; --i) {
      for(int j = 0; j < boardTable[i].length; ++j) {
        sum += boardTable[i][j] == playerCard ? playerCard : (-1) * sum;
        if (sum == value * 2) return true;
      }
    }

    sum = 0;

    for(int i = 0; i < boardTable[0].length; ++i) {
      for(int k = boardTable.length-1; k > 0; --k) {
        sum += boardTable[k][i] == playerCard ? playerCard : (-1) * sum;
        if (sum == value) return true;
      }
    }

    return false;
  }

  @Override
  public boolean checkForWinnerHorizontally(int playCard) {
    int sum = 0;
    int i =  boardTable.length-1, row = boardTable[0].length;
    int index = 0;

    while (i >= 0) {
      int[] temp = boardTable[i];

      if(index != boardTable[0].length &&
              ((temp[index] == playCard) &&
                      sum != (playCard * 4)))
      {
        sum += temp[index];
        ++index;
      } else if (sum == (playCard * 4)){
        return true;
      } else {
        sum = 0;
        if (index == temp.length - 3) {
          --i;
          index = -1;
        }
        ++index;
      }
    }

    return sum == playCard*4;
  }

  @Override
  public boolean checkForWinnerLeft(int playerCard) {
    int i = 3, index = 0, sum = 0;

    while(i < boardTable.length-1) {
      sum += (boardTable[i++][index++] == playerCard) ?
              playerCard : (sum * -1);
    }

    return sum == (playerCard*4);
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
