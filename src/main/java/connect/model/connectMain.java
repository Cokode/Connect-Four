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
    controller.loadGame();

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
    String playerName1;
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

    /**
     * Simulates the gameplay of a specific game.
     *
     * @param controller The controller managing the game.
     */
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

/**
 * Plays a move for a player in the game.
 *
 * @param name       The name of the player making the move.
 * @param scanner    The Scanner object to capture user input.
 * @param card       The card (representing the player) to be placed on the board.
 * @param controller The controller managing the game.
 */
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

/**
 * Plays a move for a player in the game.
 *
 * @param name       The name of the player making the move.
 * @param card       The card (representing the player) to be placed on the board.
 * @param controller The controller managing the game.
 */
  private static void playMove(String name, int card, Controller controller, int position) {
    System.out.println(name + " make a move");
    if (controller.getGameBoard().addToBoard(position, card)) {
      controller.getGameBoard().printBoard();
      controller.switchPlayer();
    } else {
      System.out.println("This column is filled");
    }
  }

  /**
   * Reads and validates user input from the scanner to obtain a position between 1 and 7.
   * If the input is invalid or out of range, it prompts the user to enter a valid position.
   *
   * @param scanner The Scanner object used for input.
   * @return An integer representing the validated position (between 1 and 7), or -1 if the input is invalid.
   */
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

  /**
   * Announces the winner of the game and displays the player's information.
   *
   * @param name   The name of the winning player.
   * @param player The Player object representing the winning player.
   */
  private static void announceWinner(String name, Player player) {
    System.out.println(name + " Wins the game! ");
    out.println(player.displayInfo());
  }


  /**
   * The entry point for starting the game application.
   * Initializes the controller, collects player names, loads player cards, and starts the game.
   *
   * @param args The command-line arguments passed to the program (not used in this application).
   */
  public static void main(String[] args) {
    Controller controller = new Controller();

    // Collect player names
    introAndPlayerNameCollection(controller);

    // Load player cards
    loadPlayerCards(controller);

    // Print initial game board
    controller.getGameBoard().printBoard();

    // Start the game
    playGame(controller);
  }

}
