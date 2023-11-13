package connect;

public class Player {
  private final String name;
  private int playerScore;
  private boolean isHuman;

  private boolean isWinner;

  public Player (String name) {
    this.name = name;
    this.playerScore = getPlayerScore();
    this.isHuman = true;
  }

  /**
   * Default constructor for a Computer as Player
   */
  public Player () {
    this.name = "Computer";
    this.playerScore = getPlayerScore();
    this.isHuman = false;
  }

  public String getName() {
    return name;
  }

  public int getPlayerScore() {
    return playerScore;
  }

  public void setPlayerScore(int playerScore) {
    this.playerScore += Math.max(playerScore, 0);
  }

  public boolean isHuman() {
    return isHuman;
  }


  @Override
  public String toString() {
    return "Player{" +
            "name='" + name + '\'' +
            ", playerScore=" + playerScore +
            ", isHuman=" + isHuman +
            ", isWinner=" + isWinner +
            '}';
  }
}
