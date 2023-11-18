package connect.model;

import connect.Controller;

import java.util.Random;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class connectMain {

  static Scanner scanner = new Scanner(in);
  static Random random = new Random();

  /**
   * Provides the introduction and rules for the CONNECT-FOUR game.
   * The objective is to connect four discs (numbers) in a row horizontally,
   * vertically, or diagonally.
   *
   * @return A formatted string with the game's introduction and objective.
   */
  private static String gameIntro() {
    return """
        \nWelcome to CONNECT-FOUR game
        
        Objective:
        --> The goal of each player is to connect
            four (4) of their own discs (numbers) in 
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

  private static int playerSelection() {
    int number, value = 0;
    String reIntro = """
  -->
        Press ( 1 ) to play with another human.
        Press ( 2 ) to play with a machine.
  """;

    boolean isValid = false;

    while (!isValid) {
      out.println("Enter an integer:");
      if (scanner.hasNextInt()) {
        number = scanner.nextInt();

        if(number == 2) {
          out.println("\t\tGreat Choice! you will play with a machine\n");
          value = 2;
          isValid = true;
        } else if (number == 1) {
          out.println("\t\tYou will play with another human\n");
         value = 1;
         isValid = true;
        } else {
          out.println(reIntro);
        }
      } else {
        out.println("Invalid input. Please enter an integer.");
        scanner.next(); // Discard invalid input
      }
    }

  scanner.nextLine();
    return value;
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

  public static void introAndPlayerNameCollection(Controller controller) {
    out.println(gameIntro());
    out.println(startGame());

    String words, word2, fName = "",
            sName = "";
    words = "\t\tPlayer 1 Enter Your name: ";
    word2 = "\t\tPlayer 2 Enter Your name: ";

    int selection = playerSelection();

    out.println(words);
    fName = scanner.nextLine();

    if ( selection == 2) {
      sName = null;
    } else if (selection == 1){
      out.println(word2);
      sName = scanner.nextLine();
    }

    playerSettings(controller, fName, sName);
  }

  public static void loadPlayerCards(Controller controller) {
    int firstCard, secondCard;
    String word3, warning, words, word2;
    String fName = controller.getPlayers().get(0).getName();
    String sName = controller.getPlayers().get(1).getName();
    //   words = "LOADING... \n\nChoose your card number : "; TODO REMOVE
    word3 = """
    \n\t--> SELECT any number from 1 - 9(inclusively)
          \tas your disc(player card)""";
    warning = "\t\tPlayers must only enter valid unique numbers as play card";
    words = "\n\t\tCHOOSE CARDS :";

    out.println(words);
    out.println(word3);

    words = " select your card: ";
    word2 = " select your card: ";

    while (true) {
      try {
        out.println("\n\n\t\t" + fName + words);
        firstCard = scanner.nextInt();
        scanner.nextLine();

        if (sName == null || sName.equalsIgnoreCase("Computer")) {
          secondCard = (random.nextInt(10) + firstCard % 2);
        } else {
          out.println("\n\t\t" + sName + word2);
          secondCard = scanner.nextInt();
        }

        if (controller.loadPlayerCard(firstCard, secondCard)) {
          out.println("\t\t...Preparing game environment \n");
          break;
        } else {
          out.println(warning);
          out.println(word3);
        }
      } catch (Exception e ) {
        scanner.nextLine();
        out.println("" +
                "\t\tMake sure you have not entered incorrect types\n" +
                "\t\tonly enter valid numbers from 1 - 9");
      }
    }
  }

  public static void playGame (Controller controller) {
    Random random = new Random();

    int i = 1, position = 0;

    String fName = controller.getPlayers().get(0).getName();
    String sName = controller.getPlayers().get(1).getName();

    int firstCard = controller.getPlayers().get(0).getPlayerCard();
    int secondCard = controller.getPlayers().get(1).getPlayerCard();

    while (true) {
      if (controller.getGameBoard().checkGridIsFilled()) {
        out.println("No winners, game board is filled.");
        break;
      }

      switch (i) {
        case 1 -> {
          out.println(fName + " make a move");
          if (scanner.hasNextInt()) {
            position = scanner.nextInt();

            scanner.nextLine();
            if (controller.getGameBoard().addToBoard(position, firstCard)) {
              if (controller.sorting(firstCard, controller.getGameBoard())) {
                out.println(fName + " Wins the game ! ");
                controller.getGameBoard().printBoard();
                out.println(controller.getPlayers().get(0));
                return;
              }
              i = sName.equalsIgnoreCase("computer") ? 3 : 2;
              controller.getGameBoard().printBoard();
            } else
              out.println("\t\tThis column is filled");
          } else {
            i -= 1;
            out.println("\t\tonly enter integers from 1 - 7");
            scanner.next();
          }
        }
        case 2 -> {
          out.println(sName + " make a move");

          if (scanner.hasNextInt()) {
            position = scanner.nextInt();

            scanner.nextLine();
            if (controller.getGameBoard().addToBoard(position, secondCard)) {
              if (controller.sorting(secondCard, controller.getGameBoard())) {
                out.println(sName + " Wins the game ! ");
                controller.getGameBoard().printBoard();
                out.println(controller.getPlayers().get(1));
                return;
              }
              controller.getGameBoard().printBoard();
              i--;
            } else
              out.println("This column is filled");
          } else {
            out.println("\t\tonly enter integers from 1 - 7");
            scanner.next();
          }
        }
        case 3 -> {
          out.println(sName + " make a move");

          position = random.nextInt(8);
          if (position == 0) position++;

          if (controller.getGameBoard().addToBoard(position, secondCard)) {
            if (controller.sorting(secondCard, controller.getGameBoard())) {
              out.println(sName + " Wins the game ! ");
              controller.getGameBoard().printBoard();
              out.println(controller.getPlayers().get(1));
              return;
            }
            controller.getGameBoard().printBoard();
            i = 1;
          } else
            out.println("This column is filled");

        }
      }
    }
  }

  public static void main(String[] args) {
    Controller controller = new Controller();

    introAndPlayerNameCollection(controller); // TODO PART 1
    loadPlayerCards(controller);
    controller.getGameBoard().printBoard();
    playGame(controller);
  }
}
