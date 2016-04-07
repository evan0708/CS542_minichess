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
            //chess test = new chess();
            //test.display_board_state();
            /*
            String strIn = "1 W\nkqbnr\nppppp\n.....\n.....\nPPPPP\nRNBQK\n";
            chess.boardSet(strIn);
            String ex = chess.boardGet();
            System.out.println("ex:");
            System.out.println(ex);
            */
            //chess.reset();


		}
		
		{
			zeromq.start();
		}
	}
}