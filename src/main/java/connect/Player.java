package connect;

public class Player {
  private final String name;
  private int playerScore;
  private boolean isHuman;

  private boolean isWinner;

  public Player (String name, int playerScore) {
    this.name = name;
    this.playerScore = playerScore;
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

  public void setHuman(boolean human) {
    isHuman = human;
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
