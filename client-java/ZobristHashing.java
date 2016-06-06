import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 * Created by evanchu on 6/5/16.
 */
public class ZobristHashing {
    private static long zobristNum;
    private static long zobristBlackRandom;
    private static long zobristWhiteRandom;
    private static Random randomGenerator = new Random();
    private static long [][] zobristBoard = new long [chess.row][chess.column];
    private static Map<Character, Long> zobristPiece = new HashMap<>();


    public static void initializeZobrist() {
        zobristNum = 0;
        System.out.println("ZobristNum value: " + zobristNum);
        zobristBlackRandom = randomGenerator.nextLong();
        System.out.println("ZobristBlack value: " + zobristBlackRandom);
        zobristWhiteRandom = randomGenerator.nextLong();
        System.out.println("ZobristWhite value: " + zobristWhiteRandom);
        System.out.println();

        // Initialize random number for each position
        System.out.println("ZobristBoard value: ");
        for (int i = 0; i < chess.row; i++) {
            for (int j = 0; j < chess.column; j++) {
                zobristBoard[i][j] = randomGenerator.nextLong();
                System.out.print(zobristBoard[i][j] + ",");
            }
            System.out.println();
        }
        System.out.println();

        // Initialize random number ofr each piece
        zobristPiece.put('k', randomGenerator.nextLong());
        zobristPiece.put('q', randomGenerator.nextLong());
        zobristPiece.put('b', randomGenerator.nextLong());
        zobristPiece.put('n', randomGenerator.nextLong());
        zobristPiece.put('r', randomGenerator.nextLong());
        zobristPiece.put('p', randomGenerator.nextLong());
        zobristPiece.put('.', randomGenerator.nextLong());
        zobristPiece.put('P', randomGenerator.nextLong());
        zobristPiece.put('R', randomGenerator.nextLong());
        zobristPiece.put('N', randomGenerator.nextLong());
        zobristPiece.put('B', randomGenerator.nextLong());
        zobristPiece.put('Q', randomGenerator.nextLong());
        zobristPiece.put('K', randomGenerator.nextLong());

        System.out.println("Show ZobristPiece value :");
        Iterator iterator = zobristPiece.keySet().iterator();
        while(iterator.hasNext()) {
            Object key = iterator.next();
            Object value = zobristPiece.get(key);
            System.out.println("Key: " + key + "  Value: " + value);
        }
    }

    public static long getZobristNum () {
        return zobristNum;
    }

    public static long xorZobrist(long zobristNum, int rowPos, int columnPos, char piece) {
        zobristNum ^= zobristPiece.get(piece) * zobristBoard[rowPos][columnPos];

        return zobristNum;
    }


    public static void zobristUpdate(String move) {
        int srcColumn = chess.mapColumnToIndex(move.charAt(0));
        int srcRow = chess.mapRowToIndex(move.charAt(1));
        int destColumn = chess.mapColumnToIndex(move.charAt(3));
        int destRow = chess.mapRowToIndex(move.charAt(4));

        char currentSourcePiece = chess.board[srcRow][srcColumn];
        char oldSourcePiece = currentSourcePiece;
        char newSourcePiece = '.';

        char currentDestPiece = chess.board[destRow][destColumn];
        char oldDestPiece = currentDestPiece;
        char newDestPiece = currentSourcePiece;

        xorZobrist(zobristNum, srcRow, srcColumn, oldSourcePiece);
        xorZobrist(zobristNum, srcRow, srcColumn, newSourcePiece);
        xorZobrist(zobristNum, destRow, destColumn, oldDestPiece);
        xorZobrist(zobristNum, destRow, destColumn, newDestPiece);

        if (chess.nextPlayer == 'B') {
            zobristNum ^= zobristBlackRandom;
        } else if (chess.nextPlayer == 'W') {
            zobristNum ^= zobristWhiteRandom;
        }
    }
}
