package connect.model;

public interface BoardLogicInterface {

  void printBoard();
  boolean addToBoard(int position, int playerCard);
  boolean checkForWinnerDiagonally(int playerCard);
  boolean checkWinnerXAndYAxis(int playCard);
  boolean checkGridIsFilled();

}
