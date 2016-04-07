import java.util.Vector;

/**CS542: ADV AI:COMBINATOR GAMES (Spring 2016)
 * Homework 1
 * Edited by Shu-Ping Chu
 */
public class chess {
    private final static int row = 6;
    private final static int column = 5;
	private static char [][] board = new char[row][column];
    private static char nextPlayer;
    private static int move;

    // New add!! chess constructor
    public chess() {
        reset();
    }

    // New add!!
    public static void display_board_state() {
        System.out.println(move + " " + nextPlayer);
        for(int i = 0; i < row; ++i) {
            for(int j = 0; j < column; ++j) {
                System.out.print(board[i][j]);
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
        System.out.println("strOut:");
        System.out.print(strOut);
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
        System.out.print(strIn);
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
			
		} else if (intX > 4) {
			return false;
			
		}
		
		if (intY < 0) {
			return false;
			
		} else if (intY > 5) {
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
		
		return 0;
	}
	
	public static Vector<String> moves() {
		// with reference to the state of the game and return the possible moves - one example is given below - note that a move has exactly 6 characters
		
		Vector<String> strOut = new Vector<String>();
		
		strOut.add("a2-a3\n");
		strOut.add("b2-b3\n");
		strOut.add("c2-c3\n");
		strOut.add("d2-d3\n");
		strOut.add("e2-e3\n");
		strOut.add("b1-a3\n");
		strOut.add("b1-c3\n");
		
		return strOut;
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