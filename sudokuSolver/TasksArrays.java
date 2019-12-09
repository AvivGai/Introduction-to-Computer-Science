//Aviv Gai ID 203147988 
public class TasksArrays {
	
	// assume array!=null. check if all elements in array are different from each other.
	public static boolean isAllDiff(int[] array) {
		boolean isDiff=true;
		//for every element in the array check if its different from all other elements 
		for(int i=0; i<array.length & isDiff==true; i=i+1 ){
			for (int j=i+1; j<array.length & isDiff==true; j=j+1){
				if(array[i]==array[j])
					isDiff=false;
			}	
		}
		return isDiff ;
	}
	
	public static boolean isMatrixBetween(int[][] matrix, int n, int min, int max) {
		boolean matrixBetween=true;
		//check that the matrix length is n and not null
		if(matrix == null || matrix.length!=n) 
			matrixBetween=false;
		else{
			//check that every row is not null and that its length is n 
			for (int i=0; i<matrix.length & matrixBetween==true; i=i+1){
				if (matrix[i]==null || matrix[i].length != n) 
					matrixBetween=false;
				else{
					//check that every element in the matrix is between min and max
					for (int j=0; j<matrix[i].length & matrixBetween==true ; j=j+1){
						if (matrix[i][j]<min | matrix[i][j]>max) matrixBetween=false;
					}	
				}
			}
		}
		return matrixBetween;
	}
	
	//assume matrix!=null and the size is of the matrix matrix.length x matrix.length
	public static int[][] columns (int[][] matrix) {
		//create a new matrix in the same size as the matrix input
		int[][] matCol= new int[matrix.length][matrix.length];
		//for every element swap the index of the row with the index of the column
		for (int i=0; i<matrix.length; i=i+1){
			for (int j=0; j<matrix.length; j=j+1){
				matCol[j][i]=matrix[i][j];
			}
		}
		return matCol ;
	}
	
	//assume sqrtN>=2, matrix!= null, size matrix.length x matrix.length
	public static int[][] blocks (int[][] matrix, int sqrtN) {
		//create a new empty matrix in the same size and turn the blocks into rows
		int[][] matBlocks= new int[matrix.length][matrix.length];
		int k=0; int m=0;
		for(int row=0; row<sqrtN; row=row+1){
			for(int col=0; col<sqrtN; col=col+1){
				for (int i=row; i<matrix.length; i=i+sqrtN){
					for(int j=col; j<matrix.length; j=j+sqrtN){
						matBlocks[k][m]=matrix[i][j];	
						k=k+1;
						if (k>=matrix.length){
							k=0;
							m=m+1;
						}
					}
				}
			}
		}
	return matBlocks ;	
	}
}

