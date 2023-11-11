package connect;

import java.util.Arrays;

public class ConnectModel {

    // can be integrated as class property
    public int[][] createBoard(int row, int colomn) {
        return new int[colomn][row];
    }

    public void printTable(int[][] table) {
        for (int[] ints : table) {
            for (int anInt : ints) {
                System.out.print("   [ "+anInt+" ]");
            }
            System.out.println("\n");
        }
    }

    public String displayBoard() {
        return
        """
        to play with default board settings
        
        """;
    }

    public boolean addToBoard(int position, int playerCard, int[][] table) {
        if(position > table[0].length || position < 1) return false;

        for (int i = table.length-1; i > 0; --i) {
            if ((table[i][position--]) == 0){
                table[i][position] = playerCard;
             //   printTable(table);
                return true;
            }
        }

        return false;
    }


    public static void main(String[] args) {
        ConnectModel connectModel = new ConnectModel();

        int[][] arr = connectModel.createBoard(7, 6);

        connectModel.addToBoard(9, 5, arr);

        connectModel.printTable(arr);
    }


}
