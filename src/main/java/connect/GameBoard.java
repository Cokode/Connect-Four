package connect;

import connect.model.BoardLogicInterface;

import java.util.Arrays;

public class GameBoard implements BoardLogicInterface {
  private int row;
  private int column;
  private int[][] boardTable;

  // can be integrated as class property
  public GameBoard (int row, int column) {
    boardTable = new int[row][column];
  }

  public GameBoard(int[][] arr) {
    this.boardTable = arr;
  }

  public void printBoard() {
    for (int[] ints : boardTable) {
      for (int anInt : ints) {
        System.out.print("   [ "+anInt+" ]");
      }
      System.out.println("\n");
    }
  }

  public void displayBoard() {

  }
  /**
   * @return
   */
  @Override
  public boolean addToBoard(int position, int playerCard) {
    if(position > boardTable[0].length || position < 1) return false;

    for (int i = boardTable.length-1; i > 0; --i) {
      int value = (boardTable[i][--position]);
      if (value == 0){
        boardTable[i][position] = playerCard;
        //   printTable(table);
        return true;
      }
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


  public boolean checkForWinnerLeft(int playerCard) {
    int i = 3, index = 0, sum = 0;

    while(i < boardTable.length-1) {
      sum += boardTable[i][++index] == playerCard ? playerCard : (sum * -1);
//      if (boardTable[i][index] == playerCard) {
//        ++i;
//        ++index;
//        sum += playerCard;
//      }
    }

    return sum == (playerCard*4);
  }

  private boolean checkForWinnerHorizontally(int playCard) {
    int sum = 0;
    int i =  boardTable.length-1, row = boardTable[0].length;
    int index = 0;

    while (i >= 0) {
      int[] temp = boardTable[i];

      if(index != boardTable[0].length && ((temp[index] == playCard) && sum != (playCard * 4))) {
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


  public static void main(String[] args) {

    int[][] arr2 = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {5, 5, 5, 5, 0, 0, 0},
            {5, 0, 5, 5, 0, 5, 0},
    };

    // for test purpose only
    GameBoard connectModel = new GameBoard(arr2);

    connectModel.printBoard();

    if(connectModel.checkForWinnerHorizontally(5)){
      System.out.println("\n\n");
      System.out.println("\t Hurray! we got a winner: player 5 \n");
      connectModel.printBoard();
    } else
      System.out.println("You failed! ");

    System.out.println(Arrays.deepToString(arr2));
  }

}
