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
    System.out.println("Game loading...\n".toUpperCase());
    gameBoard = new GameBoard();
  }

  public void loadPlayers(Player player) {
    assert player != null;
     players.add(player);
  }

  public GameBoard getGameBoard() {
    return gameBoard;
  }


  public List<Player> getPlayers() {
    return players;
  }

}


