package connect;


import java.util.Scanner;

import static java.lang.System.in;

public class Controller {

  static GameBoard gameBoard;
  static Scanner scanner = new Scanner(in);

private static String gameIntro () {

  return """
        Welcome to CONNECT-FOUR game
        
  Objective :
  -->   The goal of each player is to connect
        four (4) of their own discs(number) in\040
        a row, either horizontally, vertically,
        or diagonally.
  """;

}

private static String startGme () {

  return """
  -->   Only two(2) players can play this game at a time
        You can play with a human, or with a machine.
        
        Press ( 1 ) to play with another human.
        Press ( 2 ) to play with a machine.
  """;
}

private static int playerSetting() {
  return scanner.nextInt();
}

private static int playerSelection(int selection) {

  if(selection == 2) {
    System.out.println("great Choice! you will play with a machine\n");
  } else if (selection == 1) {
    System.out.println("You will play with another human\n");
  } else {
    System.out.println("Invalid selection.\n");
    System.out.println(startGme());
  }
  return selection;
}

  public static void loadGame() {
  System.out.println("Game loading...\n");
  gameBoard = new GameBoard();
}






  public static void main(String[] args) {

  // text for methods
    System.out.println(gameIntro());
    System.out.println(startGme());
    int ok = playerSetting();

    int start = playerSelection(ok);
    loadGame();

    gameBoard.printBoard();



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
  }


}


