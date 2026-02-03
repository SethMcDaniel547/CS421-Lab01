//Parse args

//Have the search methods here that are called by the main method

import java.util.ArrayList;
import java.util.List;

public class KnightTour {
    //public List<int[]> path = new ArrayList<>();
    public static int moves = 0;

    public static void main(String[] args) {
        if (args.length != 4) {
            usage();
            return;
        }
        try {
            int mode = Integer.parseInt(args[0]);
            int n = Integer.parseInt(args[1]);
            int x = Integer.parseInt(args[2]);
            int y = Integer.parseInt(args[3]);

            if (mode < 0 || mode > 2) {throw new Exception();}
            if (n <= 2) {throw new Exception();}
            if (x < 0 || x >=n || y < 0 || y >= n) {throw new Exception();}

            KnightBoard.setBoard(n);

            switch (Integer.parseInt(args[0])) {
                case 0:
                    basicSearch(x, y, 1);
                    break;

                case 1:
                    heuristic1(x, y, 1);
                    break;

                case 2:
                    heuristic2(x, y, 1);
                    break;
            
                default:
                    throw new Exception();
        }
        KnightBoard.printBoard();
        System.out.println(moves);

        } catch (Exception e) {
            usage();
            return;
        }
    }

    private static boolean basicSearch(int x, int y, int depth) {
        moves++;
        KnightBoard.board[x][y] = depth;
        if (depth == KnightBoard.size()) {
            return true;
        }

        List<int[]> nextPosition = Position.getNextPositions(x, y);
        for (int i = 0; i < nextPosition.size(); i++) {
            int nextX = nextPosition.get(i)[0];
            int nextY = nextPosition.get(i)[1];
            if (KnightBoard.isFree(nextX, nextY)) {
                if (basicSearch(nextX, nextY, depth + 1)) return true;
            }
        }

        KnightBoard.board[x][y] = -1;
        return false;

    }

    private static boolean heuristic1(int x, int y, int depth) {
        moves++;
        KnightBoard.board[x][y] = depth;
        if (depth == KnightBoard.size()) {
            return true;
        }

        List<int[]> moves = Position.getNextPositions(x, y);

        moves.sort((a, b) -> {
            int distA = KnightBoard.getBorderDistance(a[0], a[1]);
            int distB = KnightBoard.getBorderDistance(b[0], b[1]);

            if (distA != distB) {
                return Integer.compare(distA, distB);
            }
            return 0;
        });

        for (int i = 0; i < moves.size(); i++) {
            if (heuristic1(moves.get(i)[0], moves.get(i)[1], depth + 1)) return true;
        }

        KnightBoard.board[x][y] = -1;
        return false;

    }

    private static boolean heuristic2(int x, int y, int depth) {
        moves++;
        KnightBoard.board[x][y] = depth;
        if (depth == KnightBoard.size()) {
            return true;
        }

        List<int[]> moves = Position.getNextPositions(x, y);

        moves.sort((a, b) -> {
            List<int[]> posA = Position.getNextPositions(a[0], a[1]);
            List<int[]> posB = Position.getNextPositions(b[0], b[1]);

            if (posA.size() != posB.size()) {
                return Integer.compare(posA.size(), posB.size());
            }
            return 0;
        });

        for (int i = 0; i < moves.size(); i++) {
            if (heuristic2(moves.get(i)[0], moves.get(i)[1], depth + 1)) return true;
        }

        KnightBoard.board[x][y] = -1;
        return false;
    }

    public static void usage() {
        System.out.println("Usage: java KnightTour <0/1/2 (no/heuristicI/heuristicII search)> <n> <x> <y>"
                            + "\n n must be greater than 2"
                            + "\n x and y must be within range of 0 and n-1"
        );
    }
}
