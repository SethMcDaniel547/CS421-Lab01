// Hold a 2d array of all positions initialized by nxn constructor (n > 2)

//

public class KnightBoard {
    static int[][] board;
    public static int boardSize;
    public static int spaces;

    public static void setBoard(int n) {
        board = new int[n][n];
        boardSize = n;
        spaces = n * n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = -1;
            }
        }
    }

    public static void printBoard() {
        int maxVal = boardSize * boardSize;
        int width =  String.valueOf(maxVal).length();

        String formatString = "%" + width + "d ";

        for (int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                if (board[j][i] < 0) {
                    System.out.println("No solution found!");
                    return;
                }
                System.out.printf(formatString, board[j][i]);
            }
            System.out.println();
        }
    }

    public static boolean isFree(int x, int y) {
        return board[x][y] < 0;
    }

    public static int size() {
        return spaces;
    }

    public static int getBorderDistance(int x, int y) {
        int vertDist = Math.min(x, (boardSize - 1) - x);
        int horDist = Math.min(y, (boardSize - 1) - y);
        return vertDist + horDist;
    }
}