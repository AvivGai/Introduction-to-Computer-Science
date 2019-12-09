/*---------------------------------------
 Genuine author: Aviv Gai, I.D.: 203147988
 Date: 29-12-2017 
---------------------------------------*/

public class MasterDegree extends Degree {
	final static int REQUIRED_CREDITS = 10;
	private boolean thesis;

	//constructor
	public MasterDegree(String name, int degreeCode, boolean withResearch) {
		super(name, degreeCode, REQUIRED_CREDITS);
		this.thesis=withResearch;
    }
    
	//methods
    public boolean getWithResearch(){
    	return thesis;
    }
}