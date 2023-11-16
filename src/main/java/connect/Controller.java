package connect;


import java.util.ArrayList;
import java.util.List;

public class Controller {

   private static GameBoard gameBoard;
   private final List<Player> players;

   public Controller() {
     players = new ArrayList<>(2);
   }

  public void loadGame() {
    System.out.println("\n\n\t\tGame loading...\n".toUpperCase()); // remember to load game with default
    // game board
    gameBoard = new GameBoard();
//    int[][] arr2 = {
//            {0, 0, 5, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 5, 5, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0},
//            {5, 0, 5, 5, 0, 0, 0},
//            {5, 0, 5, 5, 0, 5, 0},
//    };
//
//    gameBoard = new GameBoard(arr2); todo remove
  }

  private void loadPlayers(Player player) {
    assert player != null;
     players.add(player);
  }

  public void loadPlayers(String name) {
    if(name == null){
      loadPlayers(new Player());
      return;
    }
    loadPlayers(new Player(name));
  }

  private void setCard ( int playerCard, int index ) {
    players.get(index).setPlayerCard(playerCard);
  }

  public boolean loadPlayerCard(int firstPlayerCard, int secondPlayerCard) {
     if ((firstPlayerCard >= 1 && firstPlayerCard <= 9) &&
             (secondPlayerCard >= 1 && secondPlayerCard <= 9) &&
             (firstPlayerCard != secondPlayerCard)) {
       setCard(firstPlayerCard, 0);
       setCard(secondPlayerCard, 1);
       return true;
     }
     return false;
  }

  public boolean sorting (int playerCard, GameBoard gameBoard){
    boolean sortOne = gameBoard.checkWinnerXAndYAxis(playerCard);
    boolean sortTwo = gameBoard.sortLeftDiagonalFirst(playerCard);
    boolean sortThree =gameBoard.sortLeftDiagonalSecond(playerCard);
    boolean sortFour =gameBoard.sortRightDiagonalFirst(playerCard);
    boolean sortFive = gameBoard.sortRightDiagonalSecond(playerCard);

    return sortOne || sortTwo || sortThree || sortFour || sortFive;
  }

  public GameBoard getGameBoard() {
    return gameBoard;
  }

  public List<Player> getPlayers() {
    return players;
  }
}


