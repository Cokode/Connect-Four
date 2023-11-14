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
    System.out.println("Game loading...\n".toUpperCase()); // remember to load game with default
    // game board
    // gameBoard = new GameBoard(); REMOVE HIGHLIGHT TODO
    int[][] arr2 = {
            {0, 0, 5, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 5, 5, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {5, 0, 5, 5, 0, 0, 0},
            {5, 0, 5, 5, 0, 5, 0},
    };

    gameBoard = new GameBoard(arr2);
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

  public GameBoard getGameBoard() {
    return gameBoard;
  }


  private List<Player> getPlayers() {
    return players;
  }

}


