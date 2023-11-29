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
            {0, 0, 5, 5, 5, 0, 5},
            {5, 0, 0, 0, 0, 5, 0},
            {5, 5, 5, 0, 5, 5, 5},
    };
    gameBoard = new GameBoard(customArray);
    boolean actual = gameBoard.checkWinnerXAndYAxis(5);
    boolean expected = false;

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


}
