package connect;

import java.util.Arrays;

public class ConnectModel {

  // can be integrated as class property
  public int[][] createBoard(int row, int colomn) {
    return new int[colomn][row];
  }

  public void printTable(int[][] table) {
    for (int[] ints : table) {
      for (int anInt : ints) {
        System.out.print("   [ "+anInt+" ]");
      }
      System.out.println("\n");
    }
  }

  public String displayBoard() {
    return
            """
            to play with default board settings
            
            """;
  }

  public boolean addToBoard(int position, int playerCard, int[][] table) {
    if(position > table[0].length || position < 1) return false;

    for (int i = table.length-1; i > 0; --i) {
      int value = (table[i][--position]);
      if (value == 0){
        table[i][position] = playerCard;
        //   printTable(table);
        return true;
      }
      ++position;
    }

    return false;
  }

  private boolean checkForWinnerVertical(int[][] arr, int playCard) {
    int sum = 0;
    int i =  arr.length-1, column = arr[0].length;
    int index = 0;

    while (i >= 0) {
      int[] temp = arr[i];

      if((temp[index] == playCard) && sum != (playCard * 4)) {
        sum += temp[index];
        --i;
      } else if (sum == (playCard * 4)){
        return true;
      } else {
        sum = 0;
        --i;
        if (i < 3 && column != 0) {
          i = arr.length-1;
          column--;
          index++;
        }
      }
    }

    return sum == playCard*4;
  }


  public static void main(String[] args) {

    // for test purpose only
    ConnectModel connectModel = new ConnectModel();

    int[][] arr = connectModel.createBoard(7, 6);
    connectModel.printTable(arr);

    int i = 0;
    while(i < 5) {
      connectModel.addToBoard(4, 5, arr);
      ++i;
    }

    if(connectModel.checkForWinnerVertical(arr, 5)){
      System.out.println("\n\n");
      System.out.println("\t Hurray! we got a winner: player 5 \n");
      connectModel.printTable(arr);
    }
  }


}
