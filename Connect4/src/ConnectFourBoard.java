import javax.swing.JOptionPane;

/*
 * ConnectFourBoard.java
 *
 * Version:
 *  $Id: ConnectFourBoard.java,v 1.3 2013/10/18 20:14:03 jxz5746 Exp $
 * Revisions:
 *  $Log: ConnectFourBoard.java,v $
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
 * Connect four board represented as a two
 *  dimensional array
 * 
 * @author Jacob Zuber
 */
public class ConnectFourBoard 
{
	//two dimensional representation of the board
	//0 is empty spot, 1 is X spot, -1 is O spot
	private int[][] board;
	
	//if XO is even, X's turn, else O's turn
	private int XO;
	
	//holds the String to show if there is a win or not
	private String anyWin;
	
	//Instance of Player 2
	private ConnectFourPlayer pl1;
	
	//Instance of Player 2
	private ConnectFourPlayer pl2;
	
	/*
	 * Constructor
	 *  Takes size parameters for the
	 *   tow dimensional array
	 *   
	 * @param  row  number of rows in the 
	 *  two dimensional array
	 * @param  col  number of columns in the
	 *  two dimensional array
	 * @param pl1  Player 1
	 * @param pl2  Player 2
	 */
	public ConnectFourBoard(int row, int col,
							ConnectFourPlayer pl1, ConnectFourPlayer pl2)
	{
		this.board = new int[row][col];
		this.XO = 0;
		this.anyWin = "";
		this.pl1 = pl1;
		this.pl2 = pl2;
	}
	
	/*
	 * Constructor
	 *  Takes a previously made int array and makes
	 *   it into a board
	 *   
	 * @param  arr1  tow dimensional array to be set
	 *  to be the board
	 * @param pl1  Player 1
	 * @param pl2  Player 2
	 */
	public ConnectFourBoard(int[][] arr1,
							ConnectFourPlayer pl1, ConnectFourPlayer pl2)
	{
		this.board = arr1;
		this.XO = 0;
		this.anyWin = "";
		this.pl1 = pl1;
		this.pl2 = pl2;
	}
	
	/*
	 *  Runs the Connect Four board game with the given players
	 */
	public void play()
	{
		int x = 0;
		int o = 0;
		
		System.out.println(this);
		while(isWin() == 0)
		{
			if(isXTurn())
			{
				x = pl1.chooseColumn(this);
				drop(x);
				System.out.println("Player drops an X piece into column: " + x);
			}
			else if(!isXTurn())
			{
				o = pl2.chooseColumn(this);
				drop(o);
				System.out.println("Player drops an O piece into column: " + o);
			}
			
			System.out.println(this);
		} //end while
		System.out.println("\n" + winStatus());
	}
	
	/*
	 * returns the number of rows in the board
	 * 
	 * @return  number of rows in board
	 */
	public int getLength()
	{
		return board.length;
	}
	
	/*
	 * returns the number of columns in the board
	 * 
	 * @return  number of columns in the board
	 */
	public int getWidth()
	{
		return board[0].length;
	}
	
	/*
	 * returns a String representation of
	 *  the ConnectFourBoard
	 *  
	 * @return  String representation of the board
	 */
	public String toString()
	{
		String done = "";
		
		done += " 0";
		for(int i = 1; i < board[0].length; i++)
		{
			done += (" " + i);
		}
		done += "\n";
		
		char token = 'D';
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board[i].length; j++)
			{
				if(isFilled(i, j))
				{
					if(board[i][j] == 1)
					{
						token = 'X';
					}
					if(board[i][j] == -1)
					{
						token = 'O';
					}
					done += "|" + token;
				}
				else
				{
					done += "| ";
				}
			}
			done += "|\n";
		}
		for(int i = 0; i < board[0].length; i++)
		{
			done += "+-";
		}
		done += "+";
		return done;
	}
	
	/*
	 * Checks to see if a spot in the board is
	 *  filled with a player piece
	 *  
	 * return  true if the spot is not equal to 0, else false
	 */
	public boolean isFilled(int row, int col)
	{
		if(board[row][col] != 0)
		{
			return true;
		}
		return false;
	}
	
	/*
	 * fills a specified spot with the given number
	 * 
	 * @param  row  row index of spot
	 * @param  col  column index of spot
	 * @param  num  number to put into spot
	 */
	public void fill(int row, int col, int num)
	{
		board[row][col] = num;
	}
	
	/*
	 * Checks to see if a column is full of pieces
	 * 
	 * @return  true if a column has no empty spaces
	 */
	public boolean isColFull(int colnum)
	{
		boolean check = true;
		for(int i = 0; i < board.length; i++)
		{
			if(board[i][colnum] == 0)
			{
				check = false;
				break;
			}
		}
		return check;
	}
	
	/*
	 * Checks to see if the board is full
	 * 
	 * @return  true if there are no empty spots in board, else false
	 */
	public boolean fullBoard()
	{
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board[i].length; j++)
			{
				if(board[i][j] == 0)
					return false;
			}
		}
		return true;
	}
	
	/*
	 * puts a player's token into the given column at 
	 *  the highest index that is empty. The bottom of the board.
	 *   Which token is dropped is specified by isXturn()
	 *   
	 * @param  colnum  column to drop the token into
	 */
	public void drop(int colnum)
	{
		if(isColFull(colnum))
		{
			if(isXTurn())
			{
				System.out.println("\n" + this);
				
				System.out.println("\n");
				drop(getInt(0, (board[0].length-1),
						"Player X:\nColumn Full. Choose another (0-"
								+ (board[0].length-1) + ")"));
			}
			else if(!isXTurn())
			{
				System.out.print("\n" + this);

				System.out.print("\n");
				drop(getInt(0, (board[0].length-1),
						"Player O:\nColumn Full. Choose another (0-" 
								+ (board[0].length-1) + ")"));
			}
			return;
		}
		
		int token = 0;
		if(isXTurn())
		{
			token = 1;
			//token is X
		}
		if(!isXTurn())
		{
			token = -1;
			//token is O
		}
		
		for(int i = board.length - 1; i >= 0; i--)
		{
			if(!isFilled(i, colnum))
			{
				board[i][colnum] = token;
				break;
			}
		}
		
		XO++;
	}
	
	/*
	 * Checks to see whose turn it is
	 * 
	 * @return  true if it is X's turn, false if O's turn
	 */
	public boolean isXTurn()
	{
		if(XO % 2 == 0)
		{
			return true;
		}
		else
			return false;
	}
	
	/*
	 * Checks to see if a player has won or if the game is over
	 *  Sets anyWin to the correct win condition or empty quotes if no win
	 * 
	 * @return  0 if there is no win, -1 if player O wins,
	 *            1 if player X wins, 2 if the board is filled
	 */
	public int isWin()
	{
		int temp = 0;
		
		//Check Left to Right
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j <= board[0].length - 4; j++)
			{
				for(int k = 0; k < 4; k++)
				{
					temp += board[i][j+k];
				}
				if(temp >= 4)
				{
					anyWin = "X won in row " + i;
					return 1;
				}
				if(temp <= -4)
				{
					anyWin = "O won in row " + i;
					return -1;
				}
				temp = 0;
			}
		}
		temp = 0;
		
		//Check Top to Bottom
		for(int j = 0; j < board[0].length; j++)
		{
			for(int i = 0; i <= board.length - 4; i++)
			{
				for(int k = 0; k < 4; k++)
				{
					temp += board[i+k][j];
				}
				if(temp >= 4)
				{
					anyWin = "X won in column " + j;
					return 1;
				}
				if(temp <= -4)
				{
					anyWin = "O won in column " + j;
					return -1;
				}
				temp = 0;
			}
		}
		temp = 0;
		
		//Check Diagonals
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board[i].length; j++)
			{
				if(i >= 3 && j >= 3)
				{
					temp = board[i][j] + board[i-1][j-1] + board[i-2][j-2] + board[i-3][j-3];
				}
				if(temp >= 4)
				{
					anyWin = "X won on a diagonal";
					return 1;
				}
				if(temp <= -4)
				{
					anyWin = "O won on a diagonal";
					return -1;
				}
				temp = 0;
				
				if(j >= 3 && i < board.length - 3)
				{
					temp = board[i][j] + board[i+1][j-1] + board[i+2][j-2] + board[i+3][j-3];
				}
				if(temp >= 4)
				{
					anyWin = "X won on a diagonal";
					return 1;
				}
				if(temp <= -4)
				{
					anyWin = "O won on a diagonal";
					return -1;
				}
			}
		}
		
		if(fullBoard())
		{
			anyWin = "Its a tie, no one wins";
			return 2;
		}
		anyWin = "";
		
		return 0;
	}
	
	/*
	 * creates a copied version of the board
	 * 
	 * @return  two dimensional representation of the board
	 */
	public int[][] copiedArray()
	{
		int[][] copy = new int[board.length][board[0].length];
		
		for(int i = 0; i < copy.length; i++)
		{
			for(int j = 0; j < copy[i].length; j++)
			{
				copy[i][j] = board[i][j];
			}
		}
		
		return copy;
	}
	
	/*
	 * Gets the next spot that a token can be dropped
	 *  into in the specified column
	 *  
	 * @param  colnum  column to check next spot
	 * 
	 * @return  row in which the next token may be dropped into
	 *           or -1 if the column is full
	 */
	public int getNextInCol(int colnum)
	{
		int row = -1;
		
		for(int i = 0; i < getLength(); i++)
		{
			if(!isFilled(i, colnum))
			{
				row = i;
			}
		}
		
		return row;
	}

	/*
	 * Gets the int representation of the item in
	 *  the spot of the given coordinates
	 *  
	 * @param  row  row index of spot
	 * @param  col  column index of spot
	 * 
	 * @return  -1 if spot contains an O, 0 if empty, 
	 *            1 if spot contains an X
	 */
	//returns -1 if O, 0 if empty, 1 if X
	public int getToken(int row, int col)
	{
		return board[row][col];
	}

	/*
	 * Checks to see if the given coordinates are valid in the board
	 * 
	 * @param  row  row index of spot
	 * @param  col  column index of spot
	 * 
	 * @return  true if the spot exists in the board, else false
	 */
	public boolean isValid(int row, int col)
	{
		if(row >= getLength() || row < 0)
		{
			return false;
		}
		if(col >= getWidth() || col < 0)
		{
			return false;
		}
		return true;
	}
	
	/*
	 * returns a String representation of the win
	 *  condition or empty quotes if no win
	 *  
	 * @return  String of if a player has won
	 */
	public String winStatus()
	{
		return anyWin;
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















