package connect.model;

import connect.Controller;
import connect.Player;

import java.util.List;
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

    if(selection == 2) {
      out.println("great Choice! you will play with a machine\n");
    } else if (selection == 1) {
      out.println("You will play with another human\n");
    } else {
      out.println("Invalid selection.\n");
      out.println(startGme());
    }
    return selection;
  }

  /**
   * This method takes the controller as parameter
   * and loads the players into the controller
   * @param controller controller is received loaded
   */
  public static void playerSettings(Controller controller) {
    out.println(gameIntro());

    out.println(startGme());

    int playWithWho = playerSelection(scanner.nextInt());

    String firstPlayer_name,
            secondPlayer_name,
            words, word2;

    words = "Player 1 Enter Your name: ";
    word2 = "Player 2 Enter Your name: ";

    out.println(words);
    firstPlayer_name = scanner.next().toUpperCase();
    scanner.nextLine();

    if(playWithWho == 1) {

      out.println(word2);
      secondPlayer_name = scanner.nextLine().toUpperCase();

      controller.loadPlayers(firstPlayer_name);
      controller.loadPlayers(secondPlayer_name);

    } else {

      controller.loadPlayers(firstPlayer_name);
      controller.loadPlayers(null);
    }

    controller.loadGame();
  }

  public static void main(String[] args) {
    Controller controller = new Controller();


    playerSettings(controller);

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
