/*---------------------------------------
 Genuine author: Aviv Gai, I.D.: 203147988
 Date: 29-12-2017 
---------------------------------------*/

import java.util.List;

public class BachelorDegree extends Degree {
	final static int REQUIRED_CREDITS = 20;
	
	//constructor
    public BachelorDegree(String name, int degreeCode){
    	super(name, degreeCode, REQUIRED_CREDITS);
    }
}