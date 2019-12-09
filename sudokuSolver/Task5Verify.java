//Aviv Gai ID 203147988
public class Task5Verify {
	
	//assume sqrtN>=0, hints is an array of triplets. check that board is a legal solution
	public static boolean isSolution(int sqrtN, int[][] hints, int[][] board) {
		boolean goodSolution=true;
		//create new matrix where the original columns are now rows
		//and a new  matrix where the original blocks are now rows
		int[][] columns=TasksArrays.columns(board);
		int[][] blocks=TasksArrays.blocks(board,sqrtN);
		if(TasksArrays.isMatrixBetween(board, sqrtN*sqrtN, 1, sqrtN*sqrtN)==false)
			throw new RuntimeException("exception");
		else{
			//check that all the rows, columns, blocks has different value in each cell
			for(int i=0; i<board.length & goodSolution==true; i=i+1){
				if(TasksArrays.isAllDiff(board[i])==false)
					goodSolution=false;
				else if(TasksArrays.isAllDiff(columns[i])==false)
					goodSolution=false;
				else if(TasksArrays.isAllDiff(blocks[i])==false)
					goodSolution=false;
			}
			//check that the hints stay the same
			for(int i=0; i<hints.length & goodSolution==true; i=i+1 ){
				int indexRow=hints[i][0];
				int indexCol=hints[i][1];
				if(board[indexRow][indexCol]!=hints[i][2])
					goodSolution=false;
			}
		}
		return goodSolution ;
	}

}
