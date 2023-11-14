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
    // gameBoard = new GameBoard(); REMOVE HIGHLIGHT
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

  public GameBoard getGameBoard() {
    return gameBoard;
  }


  public List<Player> getPlayers() {
    return players;
  }

}


