package connect;

import connect.model.BoardLogicInterface;

import java.util.Arrays;

public class Controller {

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
    GameBoard connectModel = new GameBoard();

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


