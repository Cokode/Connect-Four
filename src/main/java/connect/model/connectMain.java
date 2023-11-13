package connect.model;

public class connectMain {

  private static String gameIntro () {

    return """
        Welcome to CONNECT-FOUR game
        
  Objective :
  -->   The goal of each player is to connect
        four (4) of their own discs(number) in\040
        a row, either horizontally, vertically,
        or diagonally.
  """;
  }

  private static String startGme () {

    return """
  -->   Only two(2) players can play this game at a time
        You can play with a human, or with a machine.
        
        Press ( 1 ) to play with another human.
        Press ( 2 ) to play with a machine.
  """;
  }

  private static int playerSelection(int selection) {

    if(selection == 2) {
      System.out.println("great Choice! you will play with a machine\n");
    } else if (selection == 1) {
      System.out.println("You will play with another human\n");
    } else {
      System.out.println("Invalid selection.\n");
      System.out.println(startGme());
    }
    return selection;
  }

  public static void main(String[] args) {

  }
}
