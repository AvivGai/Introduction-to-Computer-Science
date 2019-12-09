/*---------------------------------------
 Genuine author: Aviv Gai, I.D.: 203147988
 Date: 14-12-2017
---------------------------------------*/
public class KQueens {

	/**
	 * Prints a given boolean board. true = "Q" and false = "*"
	 * 
	 * @param board
	 *            A boolean board. true value represents a queen placed on the
	 *            board.
	 */
	public static void printBoard(boolean[][] board) {
		//assume board!=null
		if(board.length==0)
			System.out.println("There is no solution");
		String str="";
		for(int i=0; i<board.length; i=i+1){
			for(int k=0; k<board.length; k=k+1){
				if(board[i][k]==true)
					str=str+"Q ";
				else
					str=str+"* ";
			}
			System.out.println(str);
			str="";
		}
	}

	/**
	 * Checks if a queen placed on board in cell board[row][col] is threatened by
	 * more than one queen
	 * 
	 * @param board
	 *            a square boolean board
	 * @param row
	 *            the row index of the queen
	 * @param col
	 *            the column index of the queen
	 * @return true if a queen placed on board in cell board[row][col] is threatened
	 *         by more than one queen
	 */
	public static boolean isQueenThreatened(boolean[][] board, int row, int col) {
		//assume board!=null, row and col < board.length
		boolean output=false;
		boolean threat=false;
		int counter=0;
		for(int i=col-1; i<board.length & i>=0; i=i-1){
			if(board[row][i]==true & threat==false){
				counter=counter+1;
				threat=true;}
		}
		threat=false;
		for(int i=col+1; i<board.length & i>=0 & counter<2; i=i+1){
			if(board[row][i]==true & threat==false){
				counter=counter+1;
				threat=true;}
		}
		threat=false;
		for(int i=row-1; i<board.length & i>=0 & counter<2; i=i-1){
			if(board[i][col]==true & threat==false){
				counter=counter+1;
				threat=true;}
		}
		threat=false;
		for(int i=row+1; i<board.length & i>=0 & counter<2; i=i+1){
			if(board[i][col]==true & threat==false){
				counter=counter+1;
				threat=true;}
		}
		threat=false;
		for(int m=row-1, n=col+1; m<board.length & m>=0 & n<board.length & n>=0 & counter<2; m=m-1, n=n+1){
			if(board[m][n]==true & threat==false ){
				counter=counter+1;
				threat=true;}
		}
		threat=false;
		for(int m=row+1, n=col-1; m<board.length & m>=0 & n<board.length & n>=0 & counter<2; m=m+1, n=n-1){
			if(board[m][n]==true & threat==false){
				counter=counter+1;
				threat=true;}
		}
		threat=false;
		for(int m=row+1, n=col+1; m<board.length & m>=0 & n<board.length & n>=0 & counter<2; m=m+1, n=n+1){
			if(board[m][n]==true & threat==false){
				counter=counter+1;
				threat=true;}
		}
		threat=false;
		for(int m=row-1, n=col-1; m<board.length & m>=0 & n<board.length & n>=0 & counter<2 ; m=m-1, n=n-1){
			if(board[m][n]==true & threat==false){
				counter=counter+1;
				threat=true;}
		}
		if(counter>=2)
			output=true;
		return output; 
	}

	/**
	 * Checks that there are k queens on the board, and that no queen is threatened
	 * by more than one queen
	 * 
	 * @param board
	 *            a boolean board with a solution to the KQueens problem
	 * @param k
	 *            the number of queens that should be on the board
	 * @return true iff the solution is legal
	 */
	public static boolean isLegalSolution(boolean[][] board, int k) {
		//assume board!=null and board size is n x n, n>=1
		boolean output=true;
		int counter=0;
		for(int i=0; i<board.length & output==true ; i=i+1){
			for(int m=0; m<board.length & output==true; m=m+1){
				if(board[i][m]==true){
					counter=counter+1;
					output=!(KQueens.isQueenThreatened(board, i, m));
				}
			}
		}
		if(counter!=k)
			output=false;
		return output; 
	}

	/**
	 * Adds a queen to board[row][col] only if the board obtained after adding the
	 * queen is legal
	 * 
	 * @param board
	 *            a boolean board representing a legal solution for numOfQueens
	 *            queens
	 * @param row
	 *            The row index in which an attempt is made to add a new queen.
	 * @param col
	 *            The column index in which an attempt is made to add a new queen.
	 * @param numOfQueens
	 *            number of queens placed on board
	 * @return true iff queen was added to the board
	 */
	public static boolean addQueen(boolean[][] board, int row, int col, int numOfQueens) {
		boolean output=true;
		if(board[row][col]==true)
			output=false;
		else{
			board[row][col]=true;
			output=isLegalSolution(board, numOfQueens+1);
			if(output==false)
				board[row][col]=false;
		}
		return output; 
	}

	/**
	 * Solves the k queens problem, for a board of size nxn Placed k queens on board
	 * such that each queen is threatened by no more than one queen Calls recursive
	 * function with the same name
	 * 
	 * @param n
	 *            the size of the board
	 * @param k
	 *            number of queens to be placed on board
	 * @return a boolean array of size nxn that represents a legal solution to the
	 *         problem, an empty array otherwise
	 */
	public static boolean[][] kQueens(int n, int k) {
		//assume n>=1,k>=0. create a new board sized n x n
		boolean[][] board=new boolean[n][n];
		if(kQueens(board, k, 0, 0, 0))
			return board;
		else {
			//create an empty board
			boolean[][] board1=new boolean[0][0];
			return board1;
		}
	}

	/**
	 * A recursive function that tries all possible combinations for placing queens
	 * Goes over board row by row from left to right
	 * 
	 * @param board
	 *            a partial solution for numOfQueens
	 * @param row
	 *            current row
	 * @param col
	 *            current column
	 * @param k
	 *            total number of queens to place
	 * @param numOfQueens
	 *            how many queens were placed so far on board
	 * @return true iff a legal solution was found. board will represent a solution
	 *         only if true is returned
	 */
	private static boolean kQueens(boolean[][] board, int k, int row, int col, int numOfQueens) {
		boolean output=false;
		//if there are k queens on the board - stop and return true
		if(numOfQueens==k)
			output=true;
		else{
			//if its the last cell, there is one queen missing and its possible to add - return true
			if(row==board.length-1 & col==board.length-1 ){
				if(k==numOfQueens+1 & KQueens.addQueen(board, row,col, numOfQueens))
					output=true;
			}
			else{
				//if you can add the queen at board[row][col] - check the rest of the board
				if(KQueens.addQueen(board, row,col, numOfQueens)){
					if(col<board.length-1)
						output=(kQueens(board,  k, row, col+1, numOfQueens+1));
					else if(col==board.length-1 & row<board.length-1)
						output=(kQueens(board,  k, row+1, 0, numOfQueens+1));
				}
				//if you can not add the queen at board[row][col] - check the rest of the board
				else{
					if(col<board.length-1)
						output=(kQueens(board,  k, row, col+1, numOfQueens));
					else if (col==board.length-1 & row<board.length-1)
						output=(kQueens(board,  k, row+1, 0, numOfQueens));
				}
				//if the output is still false - remove the first queen and
				//start again from the next cell
				if(output==false){
					board[row][col]=false;
					if(col<board.length-1)
						output=(kQueens(board,  k, row, col+1, numOfQueens));
					else if(col==board.length & row<board.length-1)
						output=(kQueens(board,  k, row+1, 0, numOfQueens));
				}
			}
		}
		return output;
	}	
}
			
			
		

		
			

