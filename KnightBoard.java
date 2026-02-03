// Hold a 2d array of all positions initialized by nxn constructor (n > 2)

//

public class KnightBoard {
    static int[][] board;
    public static int boardSize;

    public KnightBoard(int n) {
        board = new int[n][n];
        boardSize = n;
    }
}