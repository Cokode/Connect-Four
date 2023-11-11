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
        
        """;
    }
    private boolean addToBoard(int position, int player, int[][] table) {
        for (int i = table.length-1; i > 0; --i) {
            if ((table[i][position]) < 0){
                table[i][position] = player;
                return true;
            }
        }

        return false;
    }


    public static void main(String[] args) {
        ConnectModel connectModel = new ConnectModel();

        connectModel.printTable(connectModel.createBoard(7, 6));
    }


}
