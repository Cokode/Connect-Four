package connect.model;

import connect.Controller;
import connect.Player;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class connectMain {

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

  // MAKED TO BE REMOVED
  public static void playerSettings(List<Player> players, Player player) {
    assert player != null && players != null;
    players.add(player);
  }

  public static void main(String[] args) {
    Controller controller = new Controller();
    Scanner scanner = new Scanner(in);


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

      Player playerOne = new Player(firstPlayer_name);
      Player playerTwo = new Player(secondPlayer_name);

      controller.loadPlayers(playerOne);
      controller.loadPlayers(playerTwo);

    } else {

      Player playerOne = new Player(firstPlayer_name);
      controller.loadPlayers(playerOne);

      Player computer = new Player();
      controller.loadPlayers(computer);
    }



    controller.loadGame();

    int[][] arr2 = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {5, 5, 5, 5, 0, 0, 0},
            {5, 0, 5, 5, 0, 5, 0},
    };

    controller.getGameBoard().addToBoard(3,5);
    boolean hasWinner = controller.getGameBoard().checkForWinnerVertical(5);
    controller.getGameBoard().printBoard();
    out.println(hasWinner);

    Player player = new Player("Collins");
    controller.loadPlayers(player);

    out.println(controller.getPlayers());
    for (Player px : controller.getPlayers()) {
      px.setPlayerScore(5);
      out.println(px);
    }

  }
}
