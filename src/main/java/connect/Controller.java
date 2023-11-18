package connect;


import java.util.ArrayList;
import java.util.List;

public class Controller {

   private static GameBoard gameBoard;
   private final List<Player> players;
  private int currentPlayerIndex = 0;

   public Controller() {
     players = new ArrayList<>(2);
   }

  /**
   * Loads the game by initializing the game board.
   * Optionally, it can be configured with a custom 2D array for testing purposes.
   */
  public void loadGame() {
    System.out.println("\n\n\t\tGame loading...\n".toUpperCase());
    gameBoard = new GameBoard(); // Load the game with default settings

    // Uncomment the following code block to use a custom 2D array for testing
    /*
    int[][] customArray = {
            {0, 0, 5, 5, 0, 5, 5},
            {0, 0, 0, 0, 5, 5, 0},
            {0, 0, 5, 5, 5, 0, 0},
            {0, 0, 5, 5, 5, 0, 5},
            {5, 0, 0, 0, 0, 5, 0},
            {5, 5, 5, 0, 5, 5, 5},
    };
    gameBoard = new GameBoard(customArray);
    */
  }


  private void loadPlayers(Player player) {
    assert player != null;
     players.add(player);
  }

  /**
   * Loads players based on the provided name.
   * If the name is null, it loads a default player (Computer)
   * otherwise, it loads a player with the given name.
   *
   * @param name The name of the player to be loaded. Can be null.
   */

  public void loadPlayers(String name) {
    if(name == null){
      loadPlayers(new Player());
      return;
    }
    loadPlayers(new Player(name));
  }

  /**
   * Sets the player's card at the specified index.
   *
   * @param playerCard The integer value representing the player's card.
   * @param index      The index of the player whose card needs to be set.
   */
  private void setCard(int playerCard, int index) {
    if (index >= 0 && index < players.size()) {
      players.get(index).setPlayerCard(playerCard);
    } else {
      System.out.println("Invalid player index.");
      // Or throw an IllegalArgumentException or handle the error accordingly
    }
  }


  /**
   * Loads the cards for two players into the game.
   *
   * @param firstPlayerCard  The card value for the first player (must be between 1 and 9, inclusive).
   * @param secondPlayerCard The card value for the second player (must be between 1 and 9, inclusive
   *                         and different from the first player's card).
   * @return True if both card values are valid and distinct, false otherwise.
   */
  public boolean loadPlayerCard(int firstPlayerCard, int secondPlayerCard) {
    boolean isValidCardRange = (firstPlayerCard >= 1 && firstPlayerCard <= 9) &&
            (secondPlayerCard >= 1 && secondPlayerCard <= 9);
    boolean areDistinctCards = firstPlayerCard != secondPlayerCard;

    if (isValidCardRange && areDistinctCards) {
      setCard(firstPlayerCard, 0);
      setCard(secondPlayerCard, 1);
      return true;
    }

    return false;
  }


  /**
   * Attempts to sort and check for winning patterns for a player's card in the game board.
   *
   * @param playerCard The integer value representing the player's card.
   * @return True if any winning pattern is found for the player's card, false otherwise.
   */
  public boolean sorting(int playerCard) {
    boolean sortOne = gameBoard.checkWinnerXAndYAxis(playerCard);
    boolean sortTwo = gameBoard.sortLeftDiagonalFirst(playerCard);
    boolean sortThree = gameBoard.sortLeftDiagonalSecond(playerCard);
    boolean sortFour = gameBoard.sortRightDiagonalFirst(playerCard);
    boolean sortFive = gameBoard.sortRightDiagonalSecond(playerCard);

    return sortOne || sortTwo || sortThree || sortFour || sortFive;
  }

  public boolean checkWin(int playerCard) {
    return sorting(playerCard);
  }

  public GameBoard getGameBoard() {
    return gameBoard;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public int getCurrentPlayerIndex() {
    return currentPlayerIndex;
  }

  public void switchPlayer() {
    if(currentPlayerIndex == 2) {
      currentPlayerIndex = 0;
    } else if (currentPlayerIndex == 0 && players.get(1).isHuman()) {
      currentPlayerIndex++;
    } else if (currentPlayerIndex == 1 && (players.get(1).isHuman())) {
      currentPlayerIndex--;
    } else {
      currentPlayerIndex = 2;
    }
  }
}


