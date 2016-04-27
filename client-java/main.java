public class main {
	public static int intZeromq = 54361; // CHANGE THIS - OPTIONAL
	public static String strName = "ShuPingChu"; // CHANGE THIS - REQUIRED
	
	public static void main(String[] args) {
		{
			assert main.intZeromq > 1024;
			assert main.intZeromq < 65535;
			
			assert main.strName.length() > 2;
			assert main.strName.length() < 16;
			assert main.strName.indexOf(" ") == -1;
            chess test = new chess();
            //test.display_board_state();
            /*
            String strIn = "1 W\nkqbnr\nppppp\n.....\n.....\nPPPPP\nRNBQK\n";
            chess.boardSet(strIn);
            String ex = chess.boardGet();
            System.out.println("ex:");
            System.out.println(ex);
            */

            test.reset();
                          //abcde
            String strIn = "15 W\n" +
                           ".k..r\n" + //6
                           "p.P.p\n" + //5
                           "p....\n" + //4
                           ".Rnb.\n" + //3
                           "..PQp\n" + //2
                           "..B.K\n";  //1
            //test.boardSet(strIn);
            //System.out.println("               a               b               c               d               e");
            //test.display_board_state();
            //test.eval();
            //test.movesEvaluated();
			//test.moveGreedy();
            //test.moves();
            //test.move("d6-c5\n");
            //test.display_board_state();
			//test.undo();
            //test.display_board_state();

            //test.display_board_state();
            //System.out.println("Test eval()");
            //System.out.println("Socre:" + test.eval());


		}
		
		{
			/*
			char_charBu
			chess_reset();
			do {
				char charBuffer[256];

				chess_moveRandom(charBuffer);
				printf("move: %s\n\n", charBuffer);
				chess_boardGet(charBuffer);
				printf("%s\n", charBuffer);
				if(chess_winner() != '?') {
					break;
				}

				chess_moveGreddy(charBuffer);
				printf("move: %s\n\n", charBuffer);
				chess_boardGet(charBuffer);
				printf("%s\n",, charBuffer);
				if(chess_winner()!= '?') {
					break;
				}while(true);
			}
			*/
			zeromq.start();
		}
	}
}
