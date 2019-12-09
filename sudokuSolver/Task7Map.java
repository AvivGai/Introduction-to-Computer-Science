//Aviv Gai ID 203147988
public class Task7Map {
	
	//assume 0<=i,j,k<n. produce a variable name for the given indexes.
	public static int varName(int i, int j, int k, int n) {
		int varName=n*n*i+n*j+k+1;
		return varName ;
	}
	
	//assume 0<x<=n^3. get the indexes from the variable name.
	public static int[] nameToIndex(int x, int n) {
		boolean found=false;
		int[] output=new int[3];
		int k=(x-1)%n; //because k is the only parameter not multiply by n
		for(int i=0; i<n & found==false; i=i+1){
			for (int j=0; j<n & found==false; j=j+1){
				if(varName(i,j,k,n)==x){
					found=true;
					output[0]=i; output[1]=j; output[2]=k;
				}
			}
		}
		return output ;
	}
	
	//create a whole variables map for the parameter n.
	public static int[][][] varsMap(int n) {
		int[][][] map=new int[n][n][n];
		for(int i=0; i<n ; i=i+1){
			for (int j=0; j<n; j=j+1){
				for (int k=0; k<n ; k=k+1){
					map[i][j][k]=varName(i,j,k,n);
				}
			}
		}
		return map ;
	}
}
