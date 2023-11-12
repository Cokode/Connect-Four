package connect;

import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
import java.util.Random;

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

      if(index != arr[0].length && ((temp[index] == playCard) && sum != (playCard * 4))) {
        sum += temp[index];
        --i;
      } else if (sum == (playCard * 4)){
        return true;
      } else {
        sum = 0;

        if (i < 3 && column != 0) { //remove this if statement if it fails
          i = arr.length-1;
          column--;
          index++;
        }
        --i;
//        if (i < 3 && column != 0) {
//          i = arr.length-1;
//          column--;
//          index++;
//        }
      }
    }

    return sum == playCard*4;
  }

  private boolean checkForWinnerHorizontally(int[][] arr, int playCard) {
    int sum = 0;
    int i =  arr.length-1, row = arr[0].length;
    int index = 0;

    while (i >= 0) {
      int[] temp = arr[i];

      if(index != arr[0].length && ((temp[index] == playCard) && sum != (playCard * 4))) {
        sum += temp[index];
        ++index;
      } else if (sum == (playCard * 4)){
        return true;
      } else {
        sum = 0;
        if (index == temp.length - 3) {
          --i;
          index = 0;
        }
        ++index;
      }
    }

    return sum == playCard*4;
  }


  public static void main(String[] args) {

    // for test purpose only
    ConnectModel connectModel = new ConnectModel();

    int[][] arr = connectModel.createBoard(7, 6);
    int[][] arr2 = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {5, 0, 0, 0, 0, 0, 0},
            {5, 0, 5, 5, 0, 5, 0},
    };

    connectModel.printTable(arr2);
   // int random2 = new Random().nextInt(1, 7);

//    int i = 0;
//    while(i < 5) {
//      int random = new Random().nextInt(7);
//      if (random == 0) random++;
//      connectModel.addToBoard(random, 5, arr2);
//      ++i;
//    }

    if(connectModel.checkForWinnerHorizontally(arr2, 5)){
      System.out.println("\n\n");
      System.out.println("\t Hurray! we got a winner: player 5 \n");
      connectModel.printTable(arr2);
    } else
      System.out.println("You failed! ");

    System.out.println(Arrays.deepToString(arr2));
  }



}
