//Aviv Gai ID 203147988
public class Task11Unique {
	
	//check if the given sudoku has only one solution.
	public static int[][] solveUnique(int sqrtN, int[][] hints) {
		int[][] board;
		int n=sqrtN*sqrtN;
		int[][][] map=Task7Map.varsMap(n);
		SATSolver.init(n*n*n);
		Task8Encode.encode(sqrtN, hints, map);
		boolean[] assignment1=SATSolver.getSolution();
		if (assignment1==null)
			throw new NullPointerException("timeout");
		else if(assignment1.length==0)
			throw new RuntimeException("no solution");
		//if we found one solution - check for another solution
		else{
			board=Task9Decode.mapToBoard(map, n, assignment1);
			// add a clause that rejects the first solution
			int[] clause=new int[n*n];
			int m=0;
			for(int i=1; i<assignment1.length; i=i+1){
				if(assignment1[i]==true){
					clause[m]=i*(-1);
					m=m+1;
				}
			}
			SATSolver.init(n*n*n);
			Task8Encode.encode(sqrtN, hints, map);
			SATSolver.addClause(clause);	
			boolean[] assignment2=SATSolver.getSolution();
			if (assignment2==null)
				throw new NullPointerException("timeout");
			// if there is no other solution - return the first one
			else if(assignment2.length==0)
				return board;
			// if there is another solution - return "null"
			else{
				board=null;
				return board;
			}
		}

	}
}
