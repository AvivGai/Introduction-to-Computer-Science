//Aviv Gai ID 203147988
public class Task9Decode {
	
	//assume the input is ok. decode the variable name to a value.
	public static int cellValue(int[][][] map, int i, int j, boolean[] assignment) {
		boolean found=false;
		int value=0;
		//check for every k if assignment[varName of i,j,k] is true
		for(int k=0; k<map.length; k=k+1){
			if(assignment[Task7Map.varName(i, j, k, map.length)]==true)
				//if true - the value of [i][j] is k+1
				value=k+1;
				found=true;
		if(found==false)
			throw new RuntimeException("exception");

		}
		return value ;
	}
	
	//assume input is ok. decode the whole map to values.
	public static int[][] mapToBoard(int[][][] map, int n, boolean[] assignment) {
		int[][] board=new int[n][n];
		for(int i=0; i<n; i=i+1)
			for(int j=0; j<n; j=j+1)
				board[i][j]=cellValue(map, i ,j ,assignment);
		return board ;
	}
}
