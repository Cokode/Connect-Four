#  Connect Four
> **Course projects from Vályi Sándor Zoltán (PhD) assoc. prof.,**
>> University of Nyíregyháza
>  Read about [Connect Four](https://en.wikipedia.org/wiki/Connect_Four) on Free Wikipedia.

Connect Four is a classic two-player connection game involving strategy and spatial reasoning. The game is played on a vertical grid with seven columns and six rows, resulting in 42 slots for game pieces.

Players take turns dropping colored discs (usually red and yellow) into the columns from the top. The discs fall to the lowest available space within the chosen column. The goal is to be the first to create a line of four of your own discs in a row, either horizontally, vertically, or diagonally.

The game ends in a draw if the grid becomes full without a player achieving a line of four. Connect Four is appreciated for its simplicity, yet it requires strategic planning and anticipation to outwit the opponent and create winning combinations while preventing your opponent from doing the same.

## Game implementations
> ### This game is implemented using four (4) classes and one (1) interfaces class

  - [ConnectMain](https://github.com/Cokode/Connect-Four/blob/codeCleanUP/src/main/java/connect/GameBoard.java)
  - [Controller](https://github.com/Cokode/Connect-Four/blob/codeCleanUP/src/main/java/connect/Controller.java)
  - [Game Board](https://github.com/Cokode/Connect-Four/blob/codeCleanUP/src/main/java/connect/GameBoard.java)
  - [Player](https://github.com/Cokode/Connect-Four/blob/codeCleanUP/src/main/java/connect/Player.java)
  - [Board Logic Interface](https://github.com/Cokode/Connect-Four/blob/codeCleanUP/src/main/java/connect/model/BoardLogicInterface.java)

#### **Class and methods function**
- **Game Board Class**
  ```
  The GameBoard class simulates the Connect-Four game board and implements the BoardLogicInterface.
  It contains methods crucial for the game's functionality. Each method defined in this class 
  provides the essential operations for the game's board.
    ```

- **Player Class**
     ```
  The Player class serves as a model for players participating in the game. It includes fields for 
  the player's name, player card, identification of whether the player is human or computer, player
  score, and status as a winner. Instances of this class are created when the controller class 
  initializes in the ConnectMain class. This class is used to generate an ArrayList to store players'
  information.
 
    ```

- **Controller Class**
     ```
  The Controller class handles the business logic required for processing incoming requests to the 
  GameBoard class. It's responsible for validating inputs, manipulating data, preparing responses,
   and managing potential errors that may arise during the game.
    ```

- **ConnectMain Class**
     ```
    The ConnectMain class encompasses various functionalities, including the game play methods. 
  This class acts as the starting point for the game. It initializes the GameBoard and Controller 
  classes, orchestrating the Connect-Four game's implementation.
    ```
  

