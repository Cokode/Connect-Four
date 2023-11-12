package connect.model;

public interface BoardLogicInterface {

  public void printBoard();
  public void displayBoard();
  public boolean addToBoard(int position, int playerCard);
  public boolean checkForWinnerVertical( int playCard);

}
