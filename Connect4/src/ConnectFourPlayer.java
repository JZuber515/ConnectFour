/*
 * ConnectFourBoard.java
 *
 * Version:
 *  $Id: ConnectFourPlayer.java,v 1.3 2013/10/18 20:14:02 jxz5746 Exp $
 * Revisions:
 *  $Log: ConnectFourPlayer.java,v $
 *  Revision 1.3  2013/10/18 20:14:02  jxz5746
 *  Added Comments, small changes. Created toString method instead of printBoard
 *
 *  Revision 1.2  2013/10/16 01:38:26  jxz5746
 *  Need more Comments
 *
 *  Revision 1.1  2013/10/15 21:34:17  jxz5746
 *  Needs Comments
 *
 */

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ConnectFourPlayer 
{
	//difficulty of player
	private char d;
	//whether the player is X or O
	private char XorO;
	//bad players choice of column
	private int badcol;
	
	/*
	 * Constructor
	 *  Creates a new ConnectFourPlayer
	 *  
	 * @param  board  ConnectFourBoard the player puts their pieces into
	 * @param  XorO  whether the player is player X or O
	 * @param  difficulty  the difficulty of the player or H if human
	 */
	public ConnectFourPlayer(char XorO, char difficulty)
	{
		this.d = difficulty;
		this.XorO = XorO;
		this.badcol = 0;
	}
	
	/*
	 * Chooses column to put piece into based on the player difficulty
	 * 
	 * @return  The int of the column the player wants to drop their piece into
	 *           or -1 if the player is an invlaid difficulty
	 */
	public int chooseColumn(ConnectFourBoard board)
	{
		int col = -1;
		//Human Player
		if(d == 'H')
		{
			System.out.println("\n\nHuman Player " + XorO + " moving...");
			col = human(board);
		}
		//Random Player
		if(d == 'R')
		{
			System.out.println("\n\nRandom Player " + XorO + " moving...");
			col = random(board);
		}
		//Bad Player
		if(d == 'B')
		{
			System.out.println("\n\nBad Player " + XorO + " moving...");
			col = bad(board);
		}
		if(d == 'G')
		{
			System.out.println("\n\nGood Player " + XorO + " moving...");
			col = good(board);
		}
		return col;
	}
	
	/*
	 * Human player's choice of column
	 * 
	 * @return  int of column human player wants to drop their piece into
	 */
	private int human(ConnectFourBoard board)
	{
		int col = -1;
		
		if(board.isXTurn())
		{
			col = getInt(
				-1, (board.getWidth()-1), 
					"Player X: Enter the Column to drop your piece (-1 to quit):");
		}
		else if(!board.isXTurn())
		{
			col = getInt(
				0, (board.getWidth()-1), 
					"Player O: Enter the Column to drop your piece (-1 to quit):");
		}
		if(col == -1)
		{
			if(board.isXTurn())
			{
				System.out.println("X quits the game");
			}
			if(!board.isXTurn())
			{
				System.out.println("O quits the game");
			}
			System.exit(0);
		}
		
		return col;
	}
	
	/*
	 * Random player's choice of column
	 * 
	 * @return  random int of column that is valid 
	 *           in the board. If the column is full, it repeats until
	 *            it finds a valid column
	 */
	private int random(ConnectFourBoard board)
	{
		int col = -1;
		
		do
		{
			col = ((int)(Math.random() * board.getWidth()));
		}
		while(board.isColFull(col));
		try{ Thread.sleep(500); }
		catch (Exception e) {}
		return col;
	}
	
	/*
	 * Bad player's choice of column
	 * 
	 * @return  The int of the column that is left most unfilled
	 */
	private int bad(ConnectFourBoard board)
	{		
		while(board.isColFull(badcol))
		{
			badcol++;
		}
		try{ Thread.sleep(500); }
		catch (Exception e) {}
		return badcol;
	}
	
	/*
	 * Good player's choice of column
	 * 
	 * @return  The int of the column that could make the player win,
	 *           block the other player, or has a potential win for the player,
	 *            else random
	 */
	private int good(ConnectFourBoard board)
	{	
		//create a new board to look at possible "good" places to move
		// Players don't matter since we aren't playing this board
		ConnectFourBoard copy = new ConnectFourBoard(board.copiedArray(),
										this, this);
		
		//Take Win Move
		for(int j = 0; j < copy.getWidth(); j++)
		{
			int rownum = copy.getNextInCol(j);
			if(!board.isColFull(j))
			{
				if(!copy.isFilled(rownum, j));
				{
					if(XorO == 'X')
					{
						copy.fill(rownum, j, 1);
						if(copy.isWin() == 1)
						{
							try{ Thread.sleep(500); }
							catch (Exception e) {}
							return j;
						}
						copy.fill(rownum, j, 0);
					}
					if(XorO == 'O')
					{
						copy.fill(rownum, j, -1);
						if(copy.isWin() == -1)
						{
							try{ Thread.sleep(500); }
							catch (Exception e) {}
							return j;
						}
						copy.fill(rownum, j, 0);
					}
				}
			}
		}
		
		//Block
		for(int j = 0; j < copy.getWidth(); j++)
		{
			int rownum = copy.getNextInCol(j);
			
			if(!board.isColFull(j))
			{
				if(!copy.isFilled(rownum, j));
				{
					if(XorO == 'X')
					{	
						copy.fill(rownum, j, -1);
						if(copy.isWin() == -1)
						{
							try{ Thread.sleep(500); }
							catch (Exception e) {}
							return j;
						}
						copy.fill(rownum, j, 0);
					}
					
					if(XorO == 'O')
					{
						copy.fill(rownum, j, 1);
						if(copy.isWin() == 1)
						{
							try{ Thread.sleep(500); }
							catch (Exception e) {}
							return j;
						}
						copy.fill(rownum, j, 0);
					}
				}
			}
		}
		
		//If no win and no block
		ArrayList<Integer> choices = new ArrayList<Integer>();
		for(int i = 0; i < board.getLength(); i++)
		{
			for(int j = 0; j < board.getWidth(); j++)
			{
				/*
				 * For  Player X
				 * Tries to find a spot within 4 spots of another
				 *  token of this player's that potentially could allow 
				 *   the player to win
				 */
				if(XorO == 'X' && board.isValid(i, j) && board.getToken(i, j) == 1)
				{
					for(int k = -4; k < 4; k++)
					{
						for(int x = -4; x < 4; x++)
						{
							if(board.isValid(i+k, j+x) && (!board.isFilled(i+k, j+x) || board.getToken(i+k, j+x) == 1))
							{
								if(board.getNextInCol(i+k) == j+x)
								{
									choices.add(i+k);
								}
							}
							else
							{
								break;
							}
						}
					}
				}				
				/*
				 * For  Player O
				 * Tries to find a spot within 4 spots of another
				 *  token of this player's that potentially could allow 
				 *   the player to win
				 */
				if(XorO == 'O' && board.isValid(i, j) && board.getToken(i, j) == -1)
				{
					for(int k = -4; k < 4; k++)
					{
						for(int x = -4; x < 4; x++)
						{	
							if(board.isValid(i+k, j+x) && (!board.isFilled(i+k, j+x) || board.getToken(i+k, j+x) == -1))
							{
								if(board.getNextInCol(i+k) == j+x)
								{
									choices.add(i+k);
								}
							}
						}
					}
				}
			}
		}
		
		if(choices.size() > 0)
		{	
			try{ Thread.sleep(500); }
			catch (Exception e) {}
			return choices.get((int)(Math.random() * choices.size()));
		}
		else
		{
			return random(board);
		}
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
	private int getInt(int min, int max, String prompt){
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


















