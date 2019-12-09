//Aviv Gai ID 203147988
public class Task10Solve {
	
	//take sqrtN and array of hints and find a solution to sudoku (if there is one).
	public static int[][] solve(int sqrtN, int[][] hints) {
		int[][] board;
		int n=sqrtN*sqrtN;
		int[][][] map=Task7Map.varsMap(n);
		SATSolver.init(n*n*n);
		Task8Encode.encode(sqrtN, hints, map);
		boolean[] assignment=SATSolver.getSolution();
		if (assignment==null){
			throw new NullPointerException("timeout");
		}
		//if there is no satisfying assignment
		else if(assignment.length==0){
			board=null;
			return board;
		}
		//if there is a satisfying assignment
		else{
			board=Task9Decode.mapToBoard(map, n, assignment);
			if(Task5Verify.isSolution(sqrtN, hints, board)==true)
				return board;
			else
				throw new RuntimeException("illegal solution");
		}

	}
}
