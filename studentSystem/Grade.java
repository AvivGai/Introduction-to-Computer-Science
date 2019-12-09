/*---------------------------------------
 Genuine author: Aviv Gai, I.D.: 203147988
 Date: 29-12-2017 
---------------------------------------*/

public class Grade {
	
	//fields
	private Course course;
	private int grade;
	
	//constructor
    public Grade(Course course, int grade) {
    	if(course==null | grade<0 | grade>100)
    		throw new IllegalArgumentException();
    	
    	this.course=course;
    	this.grade=grade;
    }

    //methods
    public Course getCourse() {
    	return course;
    }

    public int getGrade() {
        return grade;
    }

    public int setGrade(int grade) {
    	int prevGrade=this.grade;
    	this.grade=grade;
    	return prevGrade;
    }
    
    public String toString() {
    	return ("course: "+course+" grade: "+grade+"."); 
	}

    public boolean equals(Object other){
    	boolean output=true;
    	if(!(other instanceof Grade))
    		output=false;
    	if(output && course.getNumber()==((Grade)other).getCourse().getNumber()){
    		if(grade!=((Grade)other).getGrade())
    			output=false;
    	}
    	return output;
    }
}
