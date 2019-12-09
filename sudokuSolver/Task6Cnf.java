//Aviv Gai ID 203147988
public class Task6Cnf {
	
	//assume input is ok. make a cnf that assign at least one variable as true.
	 public static int[][] atLeastOne(int[] vars) {
			int[][] output=new int[1][vars.length];
			output[0]=vars;
			return output;
	}
	
	//assume input is ok. make a cnf that assign at most one variable as true.
	 public static int[][] atMostOne(int[] vars) {
			int n=vars.length;
			int[][] output=new int[n*(n-1)/2][2];
			int m=0;
			for(int i=0; i<n; i=i+1){
				for(int j=i+1; j<n; j=j+1){
					int[] array={vars[i]*(-1),vars[j]*(-1)};
					output[m]=array;
					m=m+1;
				}
			}
			return output;
	}
	
	//assume input is ok. make a cnf that assign exactly one variable as true.
	public static int[][] exactlyOne(int[] vars) {
		int n=vars.length;
		int[][] output=new int[(n*(n-1)/2)+1][] ;
		output[0]=atLeastOne(vars)[0];
		for(int i=0; i<n*(n-1)/2 ;i=i+1){
			output[i+1]=atMostOne(vars)[i];
		}
		return output ;
	}
}
