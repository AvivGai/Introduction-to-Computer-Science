/*---------------------------------------
 Genuine author: Aviv Gai, I.D.: 203147988
 Date: 29-12-2017 
---------------------------------------*/

import java.util.Iterator;

public class Course implements Comparable<Course>{

	//fields
	private String name;
	private Integer number;
	private int credit;
	private List<Course> preCourses;
	private List<Course> allPreCourses;

	public Course (String name, int credits) {}
	//constructor
	public Course(String name, int number, int credit) {
		if (name == null || name.equals("") | number <=0 | credit <= 0 | credit > 6| !onlyLettersAndSpaces(name))
			throw new IllegalArgumentException();

		this.name = name;
		this.number = number;
		this.credit = credit;
		this.preCourses = new LinkedList<Course>();
		this.allPreCourses=new LinkedList<Course>();

	}
	
	//methods
	public String getName(){
		return name;
	}

	public int getNumber(){
		return number;
	}

	public int getCredit(){
		return credit;
	}

	public String toString(){
		return "Course: name - " + name + ", number - " + number + ", credit - " + credit + ".";
	}

	public boolean equals(Object other){
		return other instanceof Course && ((Course) other).number == this.number;
	}


	public List<Course> getPreliminaryCourses()	{
		Sorter.bSort(preCourses);
		return preCourses;
	}

	//get ALL preliminary courses of a course (i know it works only until 2 depths, i couldnt find a recursive way it would work deeper)
	public List<Course> getAllPreliminaryCourses(){
		Iterator<Course> iter1=this.getPreliminaryCourses().iterator();
		while (iter1.hasNext()){
			Course x=iter1.next();
			if(!allPreCourses.contains(x))
				allPreCourses.add(x);
				x.getAllPreliminaryCourses();
			Iterator<Course> iter2=x.getPreliminaryCourses().iterator();
			while(iter2.hasNext()){
				Course y=iter2.next();
				if(!(allPreCourses.contains(y)))
					allPreCourses.add(y);
			}
		}
		Sorter.bSort(allPreCourses);
		return allPreCourses;
	}


	public void addPreliminaryCourse(Course course){
		if(!preCourses.contains(course))
			preCourses.add(course);
	}

	public void addPreliminaryCourses(List<Course> courses){
		Iterator<Course> iter=courses.iterator();
		while(iter.hasNext()){
			Course c=iter.next();
			if (!preCourses.contains(c))
				preCourses.add(c);
		}
	}

	public boolean isPreliminaryCourse(Course other){
		return other.getAllPreliminaryCourses().contains(this);
	}

	@Override
	public int compareTo(Course other) {
		if(this.isPreliminaryCourse(other))
			return -1;
		if(other.isPreliminaryCourse(this))
			return 1;
		else 
			return this.getNumber()-other.getNumber();
	}

	private boolean onlyLettersAndSpaces(String str) {
		boolean isLetter = true;
		for (int i = 0; i < str.length() & isLetter ; i++) {
			char c = str.charAt(i);
			isLetter = c == ' ' | (c >= 'a' & c <= 'z') | (c >= 'A' & c <= 'Z') | '0' <= c & c <= '9';
		}
		return isLetter;
	}
	
}
