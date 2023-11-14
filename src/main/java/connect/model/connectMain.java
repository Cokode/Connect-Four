package connect.model;

import connect.Controller;
import connect.Player;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class connectMain {

  static Scanner scanner = new Scanner(in);

  private static String gameIntro () {

    return """
        \nWelcome to CONNECT-FOUR game
        
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

  private static int playerSelection(int selection) {
    int select = -1;

    if(selection == 2) {
      out.println("great Choice! you will play with a machine\n");
      select = 2;
    }
    if (selection == 1) {
      out.println("You will play with another human\n");
      select = 1;
    } else {
      out.println("Invalid selection.\n");
      out.println(startGme());
    }
    return select;
  }

  /**
   * This method takes the controller as parameter
   * and loads the players into the controller
   * @param controller controller is received loaded
   */
  public static void playerSettings
  (Controller controller, String fName, String sName)
  {
    String words3, val;

    controller.loadPlayers(fName);
    controller.loadPlayers(sName);
    controller.loadGame(); // REMOVE LATER USE DEFAULT TODO

    val = sName == null? "Computer" : sName.toUpperCase();
    words3 = fName.toUpperCase() +
            "  VS  "+ val;

    out.println("\n"+words3);
  }

  public static void main(String[] args) {
    Controller controller = new Controller();

    out.println(gameIntro());
    out.println(startGme());

    String words, word2, word3,  fName = "",
            sName = "", warning = "";
    words = "Player 1 Enter Your name: ";
    word2 = "Player 2 Enter Your name: ";

    int selection = playerSelection(scanner.nextInt());

    out.println(words);
    switch (selection) {
      case 1 -> {
        //out.println(words); REMOVE THIS LINE TODO
        fName = scanner.nextLine();

        out.println(word2);
        sName = scanner.nextLine();
      }
      case 2 -> {
        out.println(words);
        fName = scanner.nextLine();
        sName = null;
      }
      default -> {
        out.println(startGme());
      }
    }

    int firstCard, secondCard;
    words = "LOADING... \n\nChoose your card number : ";
    word3 = "\nnSELECT any number from 1 - 9 (inclusively) as your disc(player card).";
    warning = "Please only enter valid unique numbers as play card";

    out.println(words);
    out.println(word3);

    while (true) {
      firstCard = scanner.nextInt();
      secondCard = scanner.nextInt();

      if (controller.loadPlayerCard(firstCard, secondCard)){
        playerSettings(controller, fName, sName);
        return;
      } else {
        out.println(warning);
        out.println(word3);
      }
    }




    int[][] arr2 = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 5, 0, 0, 0},
            {0, 0, 0, 5, 0, 0, 0},
            {5, 5, 5, 5, 0, 0, 0},
            {5, 0, 5, 5, 0, 5, 0},
    };


    boolean hasWinner = controller.getGameBoard().checkWinner(5);
    controller.getGameBoard().printBoard();
    out.println(hasWinner);

    for (Player px : controller.getPlayers()) {
      px.setPlayerScore(5);
      out.println(px);
    }

  }
}
