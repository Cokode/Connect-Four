package connect.model;

import connect.Controller;
import connect.Player;

import java.util.InputMismatchException;
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


  /**
   * Generates the initial game instructions and options for player selection.
   * Players can choose to play with another human or a machine.
   *
   * @return The formatted game instructions and options.
   */
  private static String startGame() {
    return """
        --> Only two(2) players can play this game at a time.
        --> You can play with a human or with a machine.
        
        Press (1) to play with another human.
        Press (2) to play with a machine.
        """;
  }


  /**
   * Prompts the user to choose between playing with another human or a machine.
   * Returns the chosen option: 1 for playing with another human, 2 for playing with a machine.
   *
   * @return An integer representing the user's choice.
   */
  public static int choosePlayerType() {
    int value = 0;
    String reIntro = """
        Press (1) to play with another human.
        Press (2) to play with a machine.
        """;

    boolean isValid = false;

    while (!isValid) {
      System.out.println("Enter 1 or 2:");
      if (scanner.hasNextInt()) {
        int number = scanner.nextInt();

        if (number == 2) {
          System.out.println("\t\tGreat Choice! You will play with a machine\n");
          value = 2;
          isValid = true;
        } else if (number == 1) {
          System.out.println("\t\tYou will play with another human\n");
          value = 1;
          isValid = true;
        } else {
          System.out.println(reIntro);
        }
      } else {
        System.out.println("Invalid input. Please enter 1 or 2.");
        scanner.next(); // Discard invalid input
      }
    }

    scanner.nextLine(); // Clear the scanner buffer
    return value;
  }

  /**
   * Sets up player names and loads game settings in the controller.
   *
   * @param controller The game controller to manage player settings and game loading.
   * @param player1Name The name of the first player.
   * @param player2Name The name of the second player. If null, defaults to "Computer".
   */
  public static void playerSettings(Controller controller, String player1Name, String player2Name) {
    controller.loadPlayers(player1Name);
    controller.loadPlayers(player2Name);
    controller.loadGame(); // TODO: Remove this and use default settings

    String secondPlayerName = (player2Name == null) ? "Computer" : player2Name;
    String displayNames = "\t\t" + player1Name.toUpperCase() + "  VS  " + secondPlayerName.toUpperCase();

    System.out.println("\n" + displayNames);
  }


  /**
   * Manages the introduction and collection of player names for the game.
   *
   * @param controller The game controller object for managing game settings.
   */
  public static void introAndPlayerNameCollection(Controller controller) {
    // Display game introduction and start message
    System.out.println(gameIntro());
    System.out.println(startGame());

    // Define prompt messages and initialize player names
    String player1Prompt = "\t\tPlayer 1, enter your name: ";
    String player2Prompt = "\t\tPlayer 2, enter your name: ";
    String playerName1 = "";
    String playerName2 = null;

    // Determine player types and collect names accordingly
    int playerType = choosePlayerType();

    System.out.println(player1Prompt);
    playerName1 = scanner.nextLine();

    if (playerType == 1) {
      System.out.println(player2Prompt);
      playerName2 = scanner.nextLine();
    }

    // Pass player settings to the game controller
    playerSettings(controller, playerName1, playerName2);
  }


  /**
   * Loads player cards for the game after prompting players to choose cards.
   * Uses a Controller to facilitate loading player cards for the game environment setup.
   *
   * @param controller The Controller instance handling the game logic.
   */
  public static void loadPlayerCards(Controller controller) {
    int firstCard, secondCard;

    String fName = controller.getPlayers().get(0).getName();
    String sName = controller.getPlayers().get(1).getName();

    String word3 = """
        \n\t--> SELECT any number from 1 - 9 (inclusive)
              \tas your disc (player card)""";

    String warning = "\t\tPlayers must only enter valid unique numbers as player cards";
    String words = "\n\t\tCHOOSE CARDS :";

    System.out.println(words + word3);

    Random random = new Random();
    Scanner scanner = new Scanner(System.in);

    while (true) {
      try {
        System.out.println("\n\n\t\t" + fName + " select your card: ");
        firstCard = scanner.nextInt();
        scanner.nextLine();

        if (sName == null || sName.equalsIgnoreCase("Computer")) {
          secondCard = (random.nextInt(10) + firstCard % 2);
        } else {
          System.out.println("\n\t\t" + sName + " select your card: ");
          secondCard = scanner.nextInt();
          scanner.nextLine();
        }

        if (controller.loadPlayerCard(firstCard, secondCard)) {
          System.out.println("\t\t...Preparing game environment \n");
          break;
        } else {
          System.out.println(warning);
          System.out.println(word3);
        }
      } catch (InputMismatchException e) {
        scanner.nextLine();
        System.out.println("\t\tMake sure you have entered valid numbers from 1 - 9");
      }
    }
    //scanner.close(); todo: may be uncommented if does not trow and error.
  }


//  public static void playGame (Controller controller) {
//    Random random = new Random();
//
//    int i = 1, position = 0;
//
//    String fName = controller.getPlayers().get(0).getName();
//    String sName = controller.getPlayers().get(1).getName();
//
//    int firstCard = controller.getPlayers().get(0).getPlayerCard();
//    int secondCard = controller.getPlayers().get(1).getPlayerCard();
//
//    while (true) {
//      if (controller.getGameBoard().checkGridIsFilled()) {
//        out.println("No winners, game board is filled.");
//        break;
//      }
//
//      switch (i) {
//        case 1 -> {
//          out.println(fName + " make a move");
//          if (scanner.hasNextInt()) {
//            position = scanner.nextInt();
//
//            scanner.nextLine();
//            if (controller.getGameBoard().addToBoard(position, firstCard)) {
//              if (controller.sorting(firstCard)) {
//                out.println(fName + " Wins the game ! ");
//                controller.getGameBoard().printBoard();
//                out.println(controller.getPlayers().get(0));
//                return;
//              }
//              i = sName.equalsIgnoreCase("computer") ? 3 : 2;
//              controller.getGameBoard().printBoard();
//            } else
//              out.println("\t\tThis column is filled");
//          } else {
//            i -= 1;
//            out.println("\t\tonly enter integers from 1 - 7");
//            scanner.next();
//          }
//        }
//        case 2 -> {
//          out.println(sName + " make a move");
//
//          if (scanner.hasNextInt()) {
//            position = scanner.nextInt();
//
//            scanner.nextLine();
//            if (controller.getGameBoard().addToBoard(position, secondCard)) {
//              if (controller.sorting(secondCard)) {
//                out.println(sName + " Wins the game ! ");
//                controller.getGameBoard().printBoard();
//                out.println(controller.getPlayers().get(1));
//                return;
//              }
//              controller.getGameBoard().printBoard();
//              i--;
//            } else
//              out.println("This column is filled");
//          } else {
//            out.println("\t\tonly enter integers from 1 - 7");
//            scanner.next();
//          }
//        }
//        case 3 -> {
//          out.println(sName + " make a move");
//
//          position = random.nextInt(8);
//          if (position == 0) position++;
//
//          if (controller.getGameBoard().addToBoard(position, secondCard)) {
//            if (controller.sorting(secondCard, controller.getGameBoard())) {
//              out.println(sName + " Wins the game ! ");
//              controller.getGameBoard().printBoard();
//              out.println(controller.getPlayers().get(1));
//              return;
//            }
//            controller.getGameBoard().printBoard();
//            i = 1;
//          } else
//            out.println("This column is filled");
//
//        }
//      }
//    }
//  }


    public static void playGame(Controller controller) {
      Scanner scanner = new Scanner(System.in);
      Random random = new Random();

      String fName = controller.getPlayers().get(0).getName();
      String sName = controller.getPlayers().get(1).getName();

      int firstCard = controller.getPlayers().get(0).getPlayerCard();
      int secondCard = controller.getPlayers().get(1).getPlayerCard();

      while (true) {
        if (controller.getGameBoard().checkGridIsFilled()) {
          System.out.println("No winners, the game board is filled.");
          break;
        }

        switch (controller.getCurrentPlayerIndex()) {
          case 0 -> {
            playMove(fName, scanner, firstCard, controller);
            if (controller.checkWin(firstCard)) {
              announceWinner(fName, controller.getPlayers().get(0));
              return;
            }
          }
          case 1 -> {
            playMove(sName, scanner, secondCard, controller);
            if (controller.checkWin(secondCard)) {
              announceWinner(sName, controller.getPlayers().get(1));
              return;
            }
          }
          case 2 -> {
            sName = "Computer";
            int position = random.nextInt(7) + 1;
            playMove(sName, secondCard, controller, position);
            if (controller.checkWin(secondCard)) {
              announceWinner(sName, controller.getPlayers().get(1));
              return;
            }
          }
        }
      }
      scanner.close();
    }

    private static void playMove(String name, Scanner scanner, int card, Controller controller) {
      System.out.println(name + " make a move");
      int position = getPosition(scanner);
      if (position != -1) {
        if (controller.getGameBoard().addToBoard(position, card)) {
          controller.getGameBoard().printBoard();
          controller.switchPlayer();
        } else {
          System.out.println("This column is filled");
        }
      }
    }

    private static void playMove(String name, int card, Controller controller, int position) {
      System.out.println(name + " make a move");
      if (controller.getGameBoard().addToBoard(position, card)) {
        controller.getGameBoard().printBoard();
        controller.switchPlayer();
      } else {
        System.out.println("This column is filled");
      }
    }

    private static int getPosition(Scanner scanner) {
      if (scanner.hasNextInt()) {
        int position = scanner.nextInt();
        scanner.nextLine();
        if (position >= 1 && position <= 7) {
          return position;
        } else {
          System.out.println("\t\tOnly enter integers from 1 - 7");
        }
      } else {
        scanner.next(); // clear invalid input
        System.out.println("\t\tOnly enter integers from 1 - 7");
      }
      return -1;
    }

    private static void announceWinner(String name, Player player) {
      System.out.println(name + " Wins the game! ");
      out.println(player.displayInfo());
    }


  public static void main(String[] args) {
    Controller controller = new Controller();

    introAndPlayerNameCollection(controller); // TODO PART 1
    loadPlayerCards(controller);
    controller.getGameBoard().printBoard();
    playGame(controller);
  }
}
