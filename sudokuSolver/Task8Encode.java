//Aviv Gai ID 203147988
public class Task8Encode {
	
	public static void encode(int sqrtN, int[][] hints, int[][][] map) {
		//cnf for cells - every cell should get exactly one value
		int[][] cellClauses;
		for(int i=0; i<sqrtN*sqrtN; i=i+1){
			for(int j=0; j<sqrtN*sqrtN; j=j+1){
				cellClauses=Task6Cnf.exactlyOne(map[i][j]);
				SATSolver.addClauses(cellClauses);
			}
		}
		//cnf for rows - every cell in the row should get a different value.
		int[] rows=new int[sqrtN*sqrtN]	;	
		int r=0;
		int[][] rowClauses;
			for(int i=0; i<sqrtN*sqrtN; i=i+1){
				for(int k=0; k<sqrtN*sqrtN; k=k+1){
					for(int j=0; j<sqrtN*sqrtN; j=j+1){
						rows[r]=map[i][j][k];
						r=r+1;
					}
					rowClauses=Task6Cnf.exactlyOne(rows);
					SATSolver.addClauses(rowClauses);
					r=0;
				}
			}
		//cnf for columns - every cell in the column should get a different value.
		int[] col=new int[sqrtN*sqrtN];
		int m=0;
		int[][] colClauses;			
		for(int k=0; k<sqrtN*sqrtN; k=k+1){
				for(int j=0; j<sqrtN*sqrtN; j=j+1){
					for(int i=0; i<sqrtN*sqrtN; i=i+1){
						col[m]=map[i][j][k];
						m=m+1;
					}
					colClauses=Task6Cnf.exactlyOne(col);
					SATSolver.addClauses(colClauses);
					m=0;
				}
		}
		//create a matrix for every value of k. for example [all i's][all j's][0]
		int[][] matrix=new int[sqrtN*sqrtN][sqrtN*sqrtN];
		for(int k=0; k<sqrtN*sqrtN; k=k+1){
			for(int i=0; i<sqrtN*sqrtN; i=i+1 ){
				for(int j=0; j<sqrtN*sqrtN; j=j+1){
					matrix[i][j]=map[i][j][k];
				}
			}
			//turn the blocks to rows
			int[][] blockMat=TasksArrays.blocks(matrix,sqrtN);
			int[] blocks=new int[sqrtN*sqrtN]	;	
			int b=0;
			////cnf for blocks - every cell in the block should get a different value. 
			int[][] blockClauses;
				for(int ii=0; ii<sqrtN*sqrtN; ii=ii+1){
					for(int jj=0; jj<sqrtN*sqrtN; jj=jj+1){
							blocks[b]=blockMat[ii][jj];
							b=b+1;
					}
					blockClauses=Task6Cnf.exactlyOne(blocks);
					SATSolver.addClauses(blockClauses);
					b=0;
				}
		}
		//cnf for hints - a cell with a hint should keep the given value,
		int[][] hintsClauses;
		int[] hintsarray=new int[1];
		for(int a=0; a<hints.length; a=a+1){
			int i=hints[a][0];
			int j=hints[a][1];
			int k=hints[a][2]-1;
			hintsarray[0]=map[i][j][k];
			hintsClauses=Task6Cnf.exactlyOne(hintsarray);
			SATSolver.addClauses(hintsClauses);
		}
		
	}
}


