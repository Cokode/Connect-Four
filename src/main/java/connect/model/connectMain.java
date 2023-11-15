package connect.model;

import connect.Controller;

import java.util.Random;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class connectMain {

  static Scanner scanner = new Scanner(in);
  static Random random = new Random();

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

  private static String startGame() {

    return """
  -->   Only two(2) players can play this game at a time
        You can play with a human, or with a machine.
        
        Press ( 1 ) to play with another human.
        Press ( 2 ) to play with a machine.
  """;
  }

  private static int playerSelection(int selection) {
    int val = 0;
    if(selection == 2) {
      out.println("\tGreat Choice! you will play with a machine\n");
     return 2;
    }
    if (selection == 1) {
      out.println("\t\tYou will play with another human\n");
      val = 1;
    } else {
      out.println("\t\tInvalid selection.\n");
      out.println(startGame());
    }
    return val;
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

    val = sName == null? "Computer".toUpperCase() : sName.toUpperCase();
    words3 = "\t\t" +fName.toUpperCase() +
            "  VS  "+ val;

    out.println("\n"+words3);
  }

  public static void main(String[] args) {
    Controller controller = new Controller();

    out.println(gameIntro());  // PART 1 TODO PART_ONE
    out.println(startGame());

    String words, word2, word3,  fName = "",
            sName = "", warning = "";
    words = "\tPlayer 1 Enter Your name: ";
    word2 = "\tPlayer 1 Enter Your name: ";

    int selection = scanner.nextInt(); //  TODO PART 2
    scanner.nextLine();

    selection = playerSelection(selection);

    out.println(words);
    fName = scanner.nextLine();

    if ( selection == 2) {
      sName = null;
    } else if (selection == 1){
      out.println(word2);
      sName = scanner.next();
    } else {
      out.println(startGame());
    }

    playerSettings(controller, fName, sName); // TODO PART 3

    int firstCard, secondCard;
  //   words = "LOADING... \n\nChoose your card number : "; TODO REMOVE
    word3 = """
    \n\t\t--> SELECT any number from 1 - 9 (inclusively)
          as your disc(player card)""";
    warning = "Please only enter valid unique numbers as play card";
    words = "\n\t\tCHOOSE CARDS :";

    out.println(words);
    out.println(word3);

    words = " select your card: ";
    word2 = " select your card: ";

    while (true) {
      out.println("\n\n"+fName + words);
      firstCard = scanner.nextInt();
      scanner.nextLine();

      if(sName == null) {
        secondCard = random.nextInt(8);
      } else {
        out.println("\n\n"+sName + word2);
        secondCard = scanner.nextInt();
      }


      if (controller.loadPlayerCard(firstCard, secondCard)){
        out.println("\t\tPreparing game environment... \n\n\n");
        break;
      } else {
        out.println(warning);
        out.println(word3);
      }
    }

    controller.getGameBoard().printBoard();


    int[][] arr2 = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 5, 0, 0, 0},
            {0, 0, 0, 5, 0, 0, 0},
            {5, 5, 5, 5, 0, 0, 0},
            {5, 0, 5, 5, 0, 5, 0},
    };


    boolean hasWinner = controller.getGameBoard().checkWinner(5);
    out.println(hasWinner);

  }
}
