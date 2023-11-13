package connect.model;

public interface BoardLogicInterface {

  void printBoard();
  boolean addToBoard(int position, int playerCard);
  boolean checkForWinnerVertical( int playCard);
  boolean checkForWinnerLeft(int playerCard);
  boolean checkForWinnerHorizontally(int playCard);

}
