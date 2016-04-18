import java.util.Enumeration;
import java.util.InputMismatchException;
import java.util.Vector;

/**CS542: ADV AI:COMBINATOR GAMES (Spring 2016)
 * Homework 1
 * Edited by Shu-Ping Chu
 */
public class chess {
    private final static int row = 6;
    private final static int column = 5;
	private static char [][] board = new char[row][column];
    private static char nextPlayer;                         // for next player
    private static int move;                                // for total move
    private final static char dash = '-';
    private final static char newLine = '\n';

    // New add!! chess constructor
    public chess() {
        reset();
    }

    // New add!!
    public static void display_board_state() {
        System.out.println(move + " " + nextPlayer);
        for(int i = 0; i < row; ++i) {
            for(int j = 0; j < column; ++j) {
                System.out.print("Board[" + i + "][" + j + "]: " + board[i][j] + " ");
                if (column == j)
                    System.out.println();
            }
            System.out.println();
        }

    }

	public static void reset() {
		// reset the state of the game / your internal variables - note that this function is highly dependent on your implementation
        move = 1;
        nextPlayer = 'W';
        String initialGame = "";
        initialGame += "kqbnr\n";
        initialGame += "ppppp\n";
        initialGame += ".....\n";
        initialGame += ".....\n";
        initialGame += "PPPPP\n";
        initialGame += "RNBQK\n";

        String newline = "\n+";                  // Split string base on newline "\n" characteristic
        String[] tokens = initialGame.split(newline);

        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < tokens[i].length(); ++j) {
                board[i][j] = tokens[i].charAt(j);
            }
        }
        //display_board_state();

	}
	
	public static String boardGet() {
		// return the state of the game - one example is given below - note that the state has exactly 40 or 41 characters
		
		String strOut = "";
        /*
		strOut += "1 W\n";
		strOut += "kqbnr\n";
		strOut += "ppppp\n";
		strOut += ".....\n";
		strOut += ".....\n";
		strOut += "PPPPP\n";
		strOut += "RNBQK\n";
        */

        strOut += move;
        strOut += " ";
        strOut += nextPlayer;
        strOut += "\n";
        for(int i = 0; i < row; ++i ) {
            for (int j = 0; j < column; ++j) {
                strOut += board[i][j];
            }
            strOut += "\n";
        }
        //System.out.println("strOut:");
        //System.out.print(strOut);
		return strOut;
	}
	/* Split input string "strIn" into 7 parts, and set into the board field */
	public static void boardSet(String strIn) {
		// read the state of the game from the provided argument and set your internal variables accordingly - note that the state has exactly 40 or 41 characters
        String newline = "\n+";                  // Split string base on newline "\n" characteristic
        String[] tokens = strIn.split(newline);

        String tmpMove = "";
        for (int i = 0; i < tokens[0].length(); ++i) {
            if(' ' == tokens[0].charAt(i)) {
                nextPlayer = tokens[0].charAt(i+1);
                break;
            }
            tmpMove += tokens[0].charAt(i);
        }
        move = Integer.parseInt(tmpMove);

        //System.out.println("tokens_size:" + tokens.length);
        //System.out.println("tokens[1] size: " + tokens[1].length());
        //System.out.print(strIn);
        for (int i = 1; i < tokens.length; ++i) {
            //System.out.println("i: " + i);
            //System.out.println("Start copying: " + tokens[i]);
            for (int j = 0; j < tokens[i].length(); ++j) {
                board[i-1][j] = tokens[i].charAt(j);
            }
        }
        //display_board_state();

    }

    public static char winner() {
		// determine the winner of the current state of the game and return '?' or '=' or 'W' or 'B' - note that we are returning a character and not a string
		// white pieces as capital letters (KQBNRP) and black pieces as small letters (kqbnrp)
        boolean white_K_Exist = false;
        boolean black_k_Exist = false;

        if (1 == move)
            return '?';
        if (41 == move)
            return '=';
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < column; ++j) {
                if ('k' == board[i][j])
                    black_k_Exist = true;
                if ('K' == board[i][j])
                    white_K_Exist = true;
            }
        }
        if (true == white_K_Exist && false == black_k_Exist)
            return 'W';
        else if (false == white_K_Exist && true == black_k_Exist)
            return 'B';
        else
            return '?';
	}
	
	public static boolean isValid(int intX, int intY) {
		if (intX < 0) {
			return false;
			
		} else if (intX > 5) {
			return false;
			
		}
		
		if (intY < 0) {
			return false;
			
		} else if (intY > 4) {
			return false;
			
		}
		
		return true;
	}
	
	public static boolean isEnemy(char charPiece) {
		// with reference to the state of the game, return whether the provided argument is a piece from the side not on move
        // - note that we could but should not use the other is() functions in here but probably
		// white pieces as capital letters (KQBNRP) and black pieces as small letters (kqbnrp)
        if ('W' == nextPlayer) {
            if ('k' == charPiece)
                return true;
            else if ('q' == charPiece)
                return true;
            else if ('b' == charPiece)
                return true;
            else if ('n' == charPiece)
                return true;
            else if ('r' == charPiece)
                return  true;
            else if ('p' == charPiece)
                return true;
            else
                return false;
        } else if ('B' == nextPlayer) {
            if ('K' == charPiece)
                return true;
            else if ('Q' == charPiece)
                return true;
            else if ('B' == charPiece)
                return true;
            else if ('N' == charPiece)
                return true;
            else if ('R' == charPiece)
                return  true;
            else if ('P' == charPiece)
                return true;
            else
                return false;
        } else
            return false;
	}
	
	public static boolean isOwn(char charPiece) {
		// with reference to the state of the game, return whether the provided argument is a piece from the side on move
        // - note that we could but should not use the other is() functions in here but probably
        // white pieces as capital letters (KQBNRP) and black pieces as small letters (kqbnrp)
        if ('B' == nextPlayer) {
            if ('k' == charPiece)
                return true;
            else if ('q' == charPiece)
                return true;
            else if ('b' == charPiece)
                return true;
            else if ('n' == charPiece)
                return true;
            else if ('r' == charPiece)
                return  true;
            else if ('p' == charPiece)
                return true;
            else
                return false;
        } else if ('W' == nextPlayer) {
            if ('K' == charPiece)
                return true;
            else if ('Q' == charPiece)
                return true;
            else if ('B' == charPiece)
                return true;
            else if ('N' == charPiece)
                return true;
            else if ('R' == charPiece)
                return  true;
            else if ('P' == charPiece)
                return true;
            else
                return false;
        } else
            return false;
	}
	
	public static boolean isNothing(char charPiece) {
		// return whether the provided argument is not a piece / is an empty field
        // - note that we could but should not use the other is() functions in here but probably
        if ('k' == charPiece)
            return false;
        else if ('q' == charPiece)
            return false;
        else if ('b' == charPiece)
            return false;
        else if ('n' == charPiece)
            return false;
        else if ('r' == charPiece)
            return  false;
        else if ('p' == charPiece)
            return false;
        else if ('K' == charPiece)
            return false;
        else if ('Q' == charPiece)
            return false;
        else if ('B' == charPiece)
            return false;
        else if ('N' == charPiece)
            return false;
        else if ('R' == charPiece)
            return false;
        else if ('P' == charPiece)
            return false;
        else
            return true;
	}
	
	public static int eval() {
		// with reference to the state of the game, return the the evaluation score of the side on move - note that positive means an advantage while negative means a disadvantage
		String currentBoard = boardGet();
        //System.out.println("eval() invoked!!");
        //System.out.println("length:" + currentBoard.length());
        currentBoard = currentBoard.substring(currentBoard.length() - 36);
        //System.out.println("currentStr:");
        //System.out.println(currentBoard);
        int totalScore = 0;
        for (char c : currentBoard.toCharArray()) {
            totalScore += getScoreValue(c);
        }
        if ('W' == nextPlayer)
            ;                         // do nothing
        else if ('B' == nextPlayer)
            totalScore = -totalScore; // negate totalScore
        else {
            System.err.println("nextPlayer is undefined!! Shouldn't goes here!!");
            System.exit(0);
        }
        //System.out.println("Score:" + totalScore);
		return totalScore;
	}

    // Provide each piece score value to eval()
    public static int getScoreValue(char piece) {
        // white pieces as capital letters (KQBNRP) and black pieces as small letters (kqbnrp)
        // Assume white is return positive value, black is return negative value
        switch (piece) {
            // for white piece
            case 'K':        // as King
                return 100;
            case 'Q':        // as Queen
                return 60;
            case 'R':        // as Rock
                return 30;
            case 'B':        // as Bishop
                return 15;
            case 'N':        // as Knight
                return 5;
            case 'P':        // as Pawn
                return 1;
            case 'k':        // as King
                return -100;
            case 'q':        // as Queen
                return -60;
            case 'r':        // as Rock
                return -30;
            case 'b':        // as Bishop
                return -15;
            case 'n':        // as Knight
                return -5;
            case 'p':        // as Pawn
                return -1;
        }
        return 0;
    }

    /* Example: Interface position      Implementation index position:[row][column]
                     abcde [column]              01234 [column]
              [row]                      [row]
                6    kqbnr                 0     kqbnr
                5    ppppp                 1     ppppp
                4    .....                 2     .....
                3    .....                 3     .....
                2    PPPPP                 4     PPPPP
                1    RNBQK                 5     RNBQK

                [column:a] = [column:0]

                   [column][row] - [column][row] == [row][column] - [row][column]
                ex:   b      1   -    c      3   == [ 5 ][   1  ] - [ 3 ][   2  ]
      */
    public static char rowIndexMap(int x) {
        switch (x) {
            case 0:
                return '6';
            case 1:
                return '5';
            case 2:
                return '4';
            case 3:
                return '3';
            case 4:
                return '2';
            case 5:
                return '1';
        }
        System.err.println("Index error - row!");
        System.exit(0);
        return '?';
    }

    public static char columnIndexMap(int y) {
        switch (y) {
            case 0:
                return 'a';
            case 1:
                return 'b';
            case 2:
                return 'c';
            case 3:
                return 'd';
            case 4:
                return 'e';
        }
        System.err.println("Index error - column!");
        System.exit(0);
        return '?';
    }

	public static Vector<String> moves() {
		// with reference to the state of the game and return the possible moves
        // - one example is given below - note that a move has exactly 6 characters
		
		Vector<String> strOut = new Vector<String>();
		
		strOut.add("a2-a3\n");
		strOut.add("b2-b3\n");
		strOut.add("c2-c3\n");
		strOut.add("d2-d3\n");
		strOut.add("e2-e3\n");
		strOut.add("b1-a3\n");
		strOut.add("b1-c3\n");

        Vector<String> kingMoves = new Vector<String>();
        Vector<String> queenMoves = new Vector<String>();
        Vector<String> rockMoves = new Vector<String>();
        Vector<String> bishopMoves = new Vector<String>();
        Vector<String> knightMoves = new Vector<String>();
        Vector<String> pawnMoves = new Vector<String>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                // If find "Pawn"
                if('P' == board[i][j]) {// || 'p' == board[i][j]) {
                    //System.out.print(collectPawnMoves(i, j, nextPlayer));
                    pawnMoves.add(collectPawnMoves(i, j, nextPlayer));
                }
                if('N' == board[i][j]) {
                    pawnMoves.add(collectKnightMoves(i, j, nextPlayer));
                }
            }
        }
/*
        Vector<String> allMoves = new Vector<String>();
        for(Enumeration e = pawnMoves.elements(); e.hasMoreElements();) {
            allMoves.addElement((String) e.nextElement());
        }
        for(Enumeration e = kingMoves.elements(); e.hasMoreElements();) {
            allMoves.addElement((String) e.nextElement());
        }
*/
        Enumeration e = pawnMoves.elements();
        while(e.hasMoreElements())
            System.out.print(e.nextElement());
        //String s = "a2-a3\nb2-b3\nc2-c3\nd2-d3\ne2-e3\nb1-a3\nb1-c3\n";
        //System.out.print("Compare s:\n" +s);

		return pawnMoves;
	}

    // white pawn moves one space north, captures diagonally / north
    // black pawn moves south
    // promoted to queen once reaching the other end
    // x: row, y: column
    public static String collectPawnMoves(int x, int y, char player) {
        String pawnMoves = "";
        String pos = convertPosToString(x, y);
        String extraPos;

        // calculate White player - toward upper (North)
        if ('W' == player) {
            // check diagonal upper left if is enemy
            if (x > 0 && isValid(x-1, y-1) && isEnemy(board[x-1][y-1])) {
                System.out.println("White Upper left");
                extraPos = convertPosToString(x-1, y-1);
                pawnMoves += concatValidMovesString(pos, extraPos);
            }
            // check diagonal upper right if is enemy
            if (x > 0 && isValid(x-1, y+1) && isEnemy(board[x-1][y+1])) {
                System.out.println("White Upper right");
                extraPos = convertPosToString(x-1, y+1);
                pawnMoves += concatValidMovesString(pos, extraPos);
            }
            // check if upper is nothing
            if (x > 0 && isValid(x-1, y) && isNothing(board[x-1][y])) {
                System.out.println("White Upper");
                extraPos = convertPosToString(x-1, y);
                pawnMoves += concatValidMovesString(pos, extraPos);
            }
            if (x == 0)
                System.out.println("Pawn promote to queen");
        }

        // Calculate Black player - toward lower (South)
        if ('B' == player) {
            // check diagonal lower left if is enemy
            if (x > 0 && isValid(x+1, y-1) && isEnemy(board[x+1][y-1])) {
                //System.out.println("Black Lower left");
                extraPos = convertPosToString(x+1, y+1);
                pawnMoves += concatValidMovesString(pos, extraPos);
            }
            // check diagonal upper right if is enemy
            if (x > 0 && isValid(x+1, y+1) && isEnemy(board[x+1][y+1])) {
                //System.out.println("Black Lower right");
                extraPos = convertPosToString(x+1, y+1);
                pawnMoves += concatValidMovesString(pos, extraPos);
            }
            // check if upper is nothing
            if (x > 0 && isValid(x+1, y) && isNothing(board[x+1][y])) {
                //System.out.println("Black Lower");
                extraPos = convertPosToString(x+1, y);
                pawnMoves += concatValidMovesString(pos, extraPos);
            }
            if (x == 0)
                System.out.println("Pawn promote to queen");
        }

        //System.out.println("pawnMoves:" + pawnMoves);
        return pawnMoves;
    }

    public static String collectKnightMoves(int x, int y, char player) {
        String knightMoves = "";
        String pos = convertPosToString(x, y);
        String extraPos;

        System.out.println("collectKnightMoves");
        // calculate White player - toward upper (North)
        if ('W' == player) {
            // Upper 1st left!
            if (isValid(x-2, y-1) && !isOwn(board[x-2][y-1])) {
                //System.out.println("");
                extraPos = convertPosToString(x-2, y-1);
                knightMoves += concatValidMovesString(pos, extraPos);
            }
            // Upper 2nd left!
            if (isValid(x-1, y-2) && !isOwn(board[x-1][y-2])) {
                //System.out.println("");
                extraPos = convertPosToString(x-1, y-2);
                knightMoves += concatValidMovesString(pos, extraPos);
            }
            // Upper 1st right
            if (isValid(x-2, y+1) && !isOwn(board[x-2][y+1])) {
                //System.out.println("");
                extraPos = convertPosToString(x-2, y+1);
                knightMoves += concatValidMovesString(pos, extraPos);
            }
            // Upper 2nd right
            if (isValid(x-1, y+2) && !isOwn(board[x-1][y+2])) {
                //System.out.println("");
                extraPos = convertPosToString(x-1, y+2);
                knightMoves += concatValidMovesString(pos, extraPos);
            }
            // Lower 1st left
            if (isValid(x+2, y-1) && !isOwn(board[x+2][y-1])) {
                //System.out.println("");
                extraPos = convertPosToString(x+1, y-1);
                knightMoves += concatValidMovesString(pos, extraPos);
            }
            // Lower 2nd left
            if (isValid(x+1, y-2) && !isOwn(board[x+1][y-2])) {
                //System.out.println("");
                extraPos = convertPosToString(x+1, y-2);
                knightMoves += concatValidMovesString(pos, extraPos);
            }
            // Lower 1st right
            if (isValid(x+2, y+1) && !isOwn(board[x+2][y+1])) {
                //System.out.println("");
                extraPos = convertPosToString(x+2, y+1);
                knightMoves += concatValidMovesString(pos, extraPos);
            }
            // Lower 2nd right
            if (isValid(x+1, y+2) && !isOwn(board[x+1][y+2])) {
                //System.out.println("");
                extraPos = convertPosToString(x+1, y+2);
                knightMoves += concatValidMovesString(pos, extraPos);
            }
        }
        return knightMoves;
    }

    // Concat all of possible moves string
    public static String concatValidMovesString(String pos, String extraPos) {
        String moves = "";
        moves += pos;
        moves += dash;
        moves += extraPos;
        moves += newLine;
        return moves;
    }

    // Covert current position to interface position as a string, column first then row!
    public static String convertPosToString(int x, int y) {
        String s = "";
        s += columnIndexMap(y);
        s += rowIndexMap(x);

        return s;
    }
	
	public static Vector<String> movesShuffled() {
		// with reference to the state of the game, determine the possible moves and shuffle them before returning them - note that you can call the chess.moves() function in here
		
		return new Vector<String>();
	}
	
	public static Vector<String> movesEvaluated() {
		// with reference to the state of the game, determine the possible moves and sort them in order of an increasing evaluation score before returning them - note that you can call the chess.moves() function in here
		
		return new Vector<String>();
	}
	
	public static void move(String charIn) {
		// perform the supplied move (for example "a5-a4\n") and update the state of the game / your internal variables accordingly - note that it advised to do a sanity check of the supplied move
	}
	
	public static String moveRandom() {
		// perform a random move and return it - one example output is given below - note that you can call the chess.movesShuffled() function as well as the chess.move() function in here
		
		return "a2-a3\n";
	}
	
	public static String moveGreedy() {
		// perform a greedy move and return it - one example output is given below - note that you can call the chess.movesEvaluated() function as well as the chess.move() function in here
		
		return "a2-a3\n";
	}
	
	public static String moveNegamax(int intDepth, int intDuration) {
		// perform a negamax move and return it - one example output is given below - note that you can call the the other functions in here
		
		return "a2-a3\n";
	}
	
	public static String moveAlphabeta(int intDepth, int intDuration) {
		// perform a alphabeta move and return it - one example output is given below - note that you can call the the other functions in here
		
		return "a2-a3\n";
	}
	
	public static void undo() {
		// undo the last move and update the state of the game / your internal variables accordingly - note that you need to maintain an internal variable that keeps track of the previous history for this
	}
}