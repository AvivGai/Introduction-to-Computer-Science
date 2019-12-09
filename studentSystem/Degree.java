/*---------------------------------------
 Genuine author: Aviv Gai, I.D.: 203147988
 Date: 29-12-2017 
---------------------------------------*/

import java.util.*;

public abstract class Degree {
	
	//fields
	private String name;
	private int degreeCode;
	private List<Course> mandatoryCourses;
	private List<Course> electiveCourses;
	private int requiredCredits;
	private int mandCredits;

	//constructor
    public Degree(String name, int degreeCode, int requiredCredits) {
    	if(name==null || name.length()==0 | degreeCode<=0 | requiredCredits<0)
    		throw new IllegalArgumentException();
    	if(!name.matches("[a-zA-Z0-9 ]*"))
    		throw new IllegalArgumentException();
    	
    	this.name = name;
		this.degreeCode = degreeCode;
		this.requiredCredits = requiredCredits;
		this.mandatoryCourses = new LinkedList<Course>();
		this.electiveCourses=new LinkedList<Course>();
		this.mandCredits=0;
    }

    public Degree() {

    }

    //methods
    public String getDegreeName(){ 
    	return name;
    }

    public int getDegreeCode(){
    	return degreeCode;
    }

    public List<Course> getMandatoryCourses() {
    	Sorter.bSort(mandatoryCourses);
        return mandatoryCourses;
    }

    public List<Course> getElectiveCourses() {
    	Sorter.bSort(electiveCourses);
    	return electiveCourses;
    }

    public boolean addCourse(Course course,boolean mandatory){
    	boolean output=true;
    	if(!mandatory){
    		if(!(electiveCourses.contains(course)))
    			electiveCourses.add(course);
    		else
    			output=false;
    	}
    	else if(mandatory){
    		if(mandatoryCourses.contains(course))
    			output=false;
    		else{
    			if(this.getCourseAndPreCredit(course)>(requiredCredits-getMandatoryCredits()))
					output=false;
				else{
					mandatoryCourses.add(course); 
					mandCredits = mandCredits + course.getCredit();
					Iterator<Course> iter1=course.getAllPreliminaryCourses().iterator();
					while(iter1.hasNext()){
						Course y=iter1.next();
						if(mandatoryCourses.isEmpty() ||!mandatoryCourses.contains(y))
							mandatoryCourses.add(y); 
							mandCredits= mandCredits + y.getCredit();
					}
				}
    		}
		}
		return output;
    }
    
    //my own private function
    private int getCourseAndPreCredit(Course course){
    	int credits=course.getCredit();
		Iterator<Course> iter=course.getAllPreliminaryCourses().iterator();
		while(iter.hasNext()){
			Course x=iter.next();
			if(mandatoryCourses.isEmpty() || !(mandatoryCourses.contains(x)))
				credits= credits + x.getCredit();
		}
		return credits;
    }
    
    public int getRequiredCredits(){
    	return requiredCredits;
    }

    public int getMandatoryCredits(){
    	return mandCredits  ;
	}

}
