/*
 * An implementation of the connect four game
 * Does not use Command line for input
 * Requires less error checking due to methods:
 * getPlayerChar and getInt (see after main method)
 * 
 * @author Jacob Zuber
 */


import javax.swing.JOptionPane;

public class ConnectFour
{
	/*
	 * Main Method to run Connect four game
	 * 
	 * @param args Command Line arguments Not used in this version
	 */
	public static void main(String[] args)
	{	
		int height = -1;
		int width = -1;
		
		// Board Setup
		int customBoard = JOptionPane.showConfirmDialog(null, "Custom Board Size?");
		if(customBoard == 0)
		{
			height = getInt(1,6,"Enter height of board (1-6)");
			width = getInt(1,7,"Enter width of board (1-7)");
		}
		if(customBoard == 1)
		{
			//default board size
			height = 4;
			width = 4;
		}
		int[][] arr1 = new int[height][width];
		
		//ask for which player difficulties
		ConnectFourPlayer X = new ConnectFourPlayer(
						'X', getPlayerChar(
						"Player X is a (Random, Bad, Good, or Human) Player"));
		ConnectFourPlayer O = new ConnectFourPlayer(
						'O', getPlayerChar(
						"Player O is a (Random, Bad, Good, or Human) Player"));
		ConnectFourBoard board = new ConnectFourBoard(arr1, X, O);
		board.play();
		
	}//end main
	
	/*
	 * Gets input from human user
	 * 	If user enters a value that is not r, b, g, or h,
	 * 	 or the upper case counterpart, then it
	 * 	  re-prompts the user with the same message
	 * 
	 * @param prompt  String message the user sees
	 * @return	The valid character the user chose
	 */
	private static char getPlayerChar(String prompt)
	{
		//Can easily be adapted to accept a list of chars by
		// taking a char array as a parameter
		char out = 'D';
		while(out!='R' && out!='B' && out!='G' && out!='H')
		{
			String in = JOptionPane.showInputDialog(prompt);
			if(in.equals(null)) System.exit(0);
			if(in.length() > 0)
			{
				out = Character.toUpperCase(in.charAt(0));
			}
		}
		return out;
	}
	
	/*
	 * Gets input from human user
	 *  If user enters a value that is invalid or is not between min and max, 
	 *   it re-prompts the user with the same message
	 *   
	 * @param  min  minimum value the input can be
	 * @param  max  maximum value the input can be
	 * @param  prompt  String message the user sees
	 */
	private static int getInt(int min, int max, String prompt){
		Integer out = null;
		while(out==null || out<min || out>max){
			try{
				String in = JOptionPane.showInputDialog(prompt);
				if(in==null) System.exit(0);
				out = Integer.parseInt(in);
			}catch(Exception e){}
		}
		return out;
	}
}








