package connect;


import java.util.ArrayList;
import java.util.List;

public class Controller {

   private static GameBoard gameBoard;
   private List<Player> players;

   public Controller() {
     players = new ArrayList<>(2);
   }

  public static void loadGame() {
    System.out.println("Game loading...\n");
    gameBoard = new GameBoard();
  }

public void loadPlayers(Player player) {
    players.add(player);
}

  public GameBoard getGameBoard() {
    return gameBoard;
  }


  public List<Player> getPlayers() {
    return players;
  }


  public static void main(String[] args) {

  // text for methods
//    System.out.println(gameIntro());
//    System.out.println(startGme());
//    int ok = playerSetting();

    //int start = playerSelection(ok);
    loadGame();

    gameBoard.printBoard();
//    gameBoard.addToBoard(2, 5);
//    System.out.println();
//    gameBoard.printBoard();



    int[][] arr2 = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {5, 5, 5, 5, 0, 0, 0},
            {5, 0, 5, 5, 0, 5, 0},
    };

    // for test purpose only
    GameBoard connectModel = new GameBoard(arr2);
    //connectModel.printBoard();

    if(connectModel.checkForWinnerHorizontally(5)){
      System.out.println("\n\n");
      System.out.println("\t Hurray! we got a winner: player 5 \n");
      connectModel.printBoard();
    } else
      System.out.println("You failed! ");
  }


}


