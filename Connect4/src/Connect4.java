/*
 * Connect4.java
 *
 * Version:
 *  $Id: Connect4.java,v 1.4 2013/10/18 23:11:34 jxz5746 Exp $
 * Revisions:
 *  $Log: Connect4.java,v $
 *  Revision 1.4  2013/10/18 23:11:34  jxz5746
 *  Done
 *
 *  Revision 1.3  2013/10/18 20:14:03  jxz5746
 *  Added Comments, small changes. Created toString method instead of printBoard
 *
 *  Revision 1.2  2013/10/16 01:38:26  jxz5746
 *  Need more Comments
 *
 *  Revision 1.1  2013/10/15 21:34:17  jxz5746
 *  Needs Comments
 *
 */



/*
 * An implementation of the connect four game
 * Uses the command line for input
 * Has many error checks due to any possible input
 * from the command line
 * 
 * @author Jacob Zuber
 */
public class Connect4
{
	/*
	 * Main Method to run Connect four game
	 * 
	 * @param args  Input from the command line to
	 *   run Connect4. Input should be of the form:
	 *    Player_1_Difficulty Player_2_Difficulty (Board_Height Board_Width)
	 * 	  Where Board_Height and Board_Width are optional parameters
	 *    Player_Difficuly options include "good", "bad", "random", and "human"
	 *    Board_Height should be an int between 1 and 6
	 *    Board_Width should be an int between 1 and 7
	 */
	public static void main(String[] args)
	{	
		//(1) Error check for correct number of arguments
		if(args.length != 2 && args.length != 4)
		{
			System.err.println("Usage: java Connect4 player-X player-O [#rows #cols]"
					+ "\nwhere player-X and player-O are one of: human bad good random"
					+ "\n[#rows #cols] are optional, if provided their values must be"
					+ "\nin the ranges: #rows from 1 to 6 and #cols from 1 to 7");
			System.exit(0);
		}//end error check
		
		int height = -1;
		int width = -1;
	
		// Get input from command line
		String input1 = args[0];
		String input2 = args[1];
		char diff1 = Character.toUpperCase(input1.charAt(0));
		char diff2 = Character.toUpperCase(input2.charAt(0));
		
		if(args.length == 2)
		{
			//Default size of board is 4x4
			height = 4;
			width = 4;
		}
		else if(args.length == 4)
		{
			//Get height and width of board
			height = Integer.parseInt(args[2]);
			width = Integer.parseInt(args[3]);
			
			 //(2) Error check board height and width
			if(height > 6 || height < 1 || width < 1 || width > 7)
			{
				System.err.println("Usage: java Connect4 player-X player-O [#rows #cols]"
						+ "\nwhere player-X and player-O are one of: human bad good random"
						+ "\n[#rows #cols] are optional, if provided their values must be"
						+ "\nin the ranges: #rows from 1 to 6 and #cols from 1 to 7");
				System.exit(0);
			}//end error check
		}
		
		//(3) Error check if valid input for player 1
		if(!(input1.equalsIgnoreCase("human") || input1.equalsIgnoreCase("random") ||
				input1.equalsIgnoreCase("bad") || input1.equalsIgnoreCase("good")))
		{
			System.err.println("Usage: java Connect4 player-X player-O [#rows #cols]"
					+ "\nwhere player-X and player-O are one of: human bad good random"
					+ "\n[#rows #cols] are optional, if provided their values must be"
					+ "\nin the ranges: #rows from 1 to 6 and #cols from 1 to 7");
			System.exit(0);
		}//end error check
		
		//(4) Error check if valid input for player 2
		if(!(input2.equalsIgnoreCase("human") || input2.equalsIgnoreCase("random") ||
				input2.equalsIgnoreCase("bad") || input2.equalsIgnoreCase("good")))
		{
			System.err.println("Usage: java Connect4 player-X player-O [#rows #cols]"
					+ "\nwhere player-X and player-O are one of: human bad good random"
					+ "\n[#rows #cols] are optional, if provided their values must be"
					+ "\nin the ranges: #rows from 1 to 6 and #cols from 1 to 7");
			System.exit(0);
		}//end error check
		
		//Creating board and players
		ConnectFourPlayer X = new ConnectFourPlayer('X', diff1);
		ConnectFourPlayer O = new ConnectFourPlayer('O', diff2);
		ConnectFourBoard board = new ConnectFourBoard(height, width, X, O);
		board.play();

	} //end main
}








