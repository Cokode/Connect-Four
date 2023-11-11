package connect;

import java.util.Arrays;

public class ConnectModel {

    public int[][] createBoard(int row, int colomn) {
        return new int[colomn][row];
    }

    public void printTable(int[][] table) {
        for (int[] ints : table) {
            for (int anInt : ints) {
                System.out.print("      [ "+anInt+" ]");
            }
            System.out.println("\n");
        }
    }


    public static void main(String[] args) {
        ConnectModel connectModel = new ConnectModel();

        connectModel.printTable(connectModel.createBoard(7, 6));
    }


}
