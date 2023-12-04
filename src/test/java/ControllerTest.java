import connect.Controller;
import connect.GameBoard;
import org.junit.jupiter.api.Test;

public class ControllerTest {

  GameBoard gameBoard;

  @Test
  public void testCheckWin () {

    int[][] customArray = {
            {0, 0, 5, 5, 0, 5, 5},
            {0, 0, 0, 0, 5, 5, 0},
            {0, 0, 5, 5, 5, 0, 0},
            {0, 0, 5, 5, 5, 5, 5},
            {5, 0, 0, 0, 0, 5, 0},
            {5, 5, 5, 0, 5, 5, 5},
    };
    gameBoard = new GameBoard(customArray);
    boolean actual = gameBoard.checkWinnerXAndYAxis(5);
    boolean expected = true ;

    assert (expected == actual);
  }

  @Test
  public void testCheckWin2 () {

    int[][] customArray = {
            {0, 0, 5, 5, 0, 5, 5},
            {0, 0, 0, 0, 5, 5, 0},
            {0, 0, 5, 5, 5, 0, 0},
            {0, 0, 5, 5, 5, 0, 5},
            {5, 0, 0, 0, 0, 5, 0},
            {5, 5, 5, 0, 5, 5, 5},
    };
    gameBoard = new GameBoard(customArray);
    boolean actual = gameBoard.sortLeftDiagonalSecond(5);
    boolean expected = true;

    assert (expected == actual);
  }

  @Test
  public void testCheckWin3 () {

    int[][] customArray = {
            {0, 0, 5, 5, 0, 5, 5},
            {0, 0, 0, 0, 5, 5, 0},
            {5, 0, 5, 5, 5, 0, 0},
            {0, 5, 5, 5, 5, 0, 5},
            {5, 0, 5, 0, 0, 5, 0},
            {5, 5, 5, 5, 5, 5, 5},
    };
    gameBoard = new GameBoard(customArray);
    boolean actual = gameBoard.sortLeftDiagonalFirst(5);
    boolean expected = true;

    assert (expected == actual);
  }

  @Test
  public void testCheckWin4 () {

    int[][] customArray = {
            {0, 0, 7, 0, 0, 0, 0},
            {0, 0, 0, 7, 0, 0, 0},
            {5, 0, 0, 0, 7, 0, 0},
            {0, 0, 0, 0, 0, 7, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
    };
    gameBoard = new GameBoard(customArray);
    boolean actual = gameBoard.sortRightDiagonalFirst(7);
    boolean expected = true;

    assert (expected == actual);
  }

  @Test
  public void testCheckWin5 () {

    int[][] customArray = {
            {0, 0, 7, 0, 0, 0, 0},
            {0, 0, 0, 7, 0, 0, 0},
            {5, 0, 0, 0, 7, 0, 0},
            {0, 0, 0, 0, 0, 7, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},

            // space
    };
    gameBoard = new GameBoard(customArray);
    boolean actual = gameBoard.sortRightDiagonalSecond(7);
    boolean expected = false;

    assert (expected == actual);
  }


  /**
   * Test must return false if both players card are the same
   * otherwise return true. this way, both players cannot have
   * the same card (number 1 - 9);
   */
  @Test
  public void testForAddingPlayerCard () {
    Controller controller = new Controller();

    controller.loadPlayers("Collins"); // added two players
    controller.loadPlayers("Frank");

    boolean expected = false;

    boolean actual = controller.loadPlayerCard(5, 5);
    // add player 1 and player 2 cards respectively

    assert (actual == expected);
  }

  @Test
  public void testForAddingPlayerCard2 () {
    Controller controller = new Controller();

    controller.loadPlayers("Collins"); // added two players
    controller.loadPlayers("Frank");

    boolean expected = false;

    boolean actual = controller.loadPlayerCard(5, 5);
    // add player 1 and player 2 cards respectively

    assert (actual == expected);
  }

}
