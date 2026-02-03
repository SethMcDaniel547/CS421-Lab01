//This class is going to return all next positions when provided with a coord by working with the knight board
//Return an array of next positions, starting at 12 oclock and going clock wise

import java.util.ArrayList;
import java.util.List;

public class Position {
    public static List<int[]> getNextPositions(int x, int y) {
        List<int[]> nextPositions = new ArrayList<>();
        int[] colOffests = {1, 2, 2, 1, -1, -2, -2, -1};
        int[] rowOffests = {2, 1, -1, -2, -2, -1, 1, 2};
        for (int i = 8; i > 0; i--) {
            int nextCol = x + colOffests[i];
            int nextRow = y + rowOffests[i];

            if (nextCol >= 0 && nextCol < KnightBoard.boardSize && nextRow >= 0 && nextRow < KnightBoard.boardSize) {
                nextPositions.add(pair(nextCol, nextRow));
            }
        }

        return nextPositions;
    }

    private static int[] pair(int x, int y) {
    int[] newPair = new int[2];
    newPair[0] = x;
    newPair[1] = y;
    return newPair;
}
}

