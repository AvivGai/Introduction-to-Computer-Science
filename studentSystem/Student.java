/*---------------------------------------
 Genuine author: Aviv Gai, I.D.: 203147988
 Date: 29-12-2017 
---------------------------------------*/
import java.util.Iterator;

public class Student implements Comparable<Student>{
	//fields
	private StudentInfo studentInfo;
	private Degree degree;
	private List<Course> registeredCourses;
	private List<Grade> grades;
	private int currentYear;
	private int finishAt;
	private int creditsGraded;
	private List<Course> courseGraded;
	private int avGrade;

	//constructors
	public Student(StudentInfo studentInfo, Degree degree) {
		if (studentInfo == null | degree == null)
			throw new IllegalArgumentException();

		this.studentInfo = studentInfo;
		this.degree = degree;
		this.registeredCourses = new LinkedList<Course>();
		this.grades = new LinkedList<Grade>();
		this.currentYear = 1;
		this.finishAt = 0;
		this.creditsGraded=0;
		this.courseGraded=new LinkedList<Course>();
		this.avGrade = 0;
	}
	
	//methods
	public StudentInfo getStudentInfo(){
		return studentInfo;
	}
	
	public Degree getDegree() {
		return degree;
	}
	
	public int getCurrentYear() {
		return currentYear;
	}
	
	public int getFinishYear() {
		return finishAt; 
	}

	public List<Grade> getGrades() {
		return grades;
	}
	public void increaseYear() {
		currentYear=currentYear+1;
	}
	
	public void closeDegree(int year) {
		currentYear=0;
		finishAt=year;
	}
	
	//check if the student is registered to the course
	public boolean isRegisteredTo(Course course){
		if(registeredCourses.contains(course))
			return true;
		else
			return false;
	}
	
	//check if the student completed a course with a passing grade
	public boolean isCompleted(Course course, int passGrade){
		boolean output=false;
		Iterator<Grade> iter=grades.iterator();
		while(iter.hasNext()){
			Grade x=iter.next();
			if(x.getCourse().getNumber()==course.getNumber() && x.getGrade()>=passGrade)
				output=true;
		}
		return output;
	}
	
	public boolean registerTo(Course course){
		if(!registeredCourses.contains(course)){
			registeredCourses.add(course);
			return true;
		}
		return false;
	}
	
	
	public double averageGrade() {
		double average=0;
		if(grades.isEmpty())
			return average;
		else{
			average = avGrade/creditsGraded;
			return average;
		}
	}
	
	//add a grade if the student is registered to the course
	public boolean addGrade(Course course, int grade) {
		Grade g=new Grade(course, grade);
		boolean output=false;
		if(registeredCourses.contains(course)){
			grades.add(g);
			registeredCourses.remove(course);
			output=true;
			//updating my private fields
			creditsGraded = creditsGraded + course.getCredit();
			courseGraded.add(course);
			avGrade=avGrade+(course.getCredit()*grade);
			
		}
		return output;
	}
	
	public int setGrade(Course course, int grade) {
		boolean found=false;
		Iterator<Grade> iter=grades.iterator();
		while(iter.hasNext()){
			if(iter.next().getCourse().getNumber()==course.getNumber())
				found=true;
		}
		if(!found)
			throw new IllegalArgumentException();
		int oldGrade=0;
		Iterator<Grade> iter1=grades.iterator();
		while(iter1.hasNext()){
			Grade y=iter.next();
			if(y.getCourse().getNumber()==course.getNumber())
				oldGrade= y.setGrade(grade);
				
		}
		return oldGrade;
	}
	
	public boolean equals(Object other) {
		if (!(other instanceof Student))
			return false;
		else if(studentInfo.getIdentityNumber()==((Student)other).getStudentInfo().getIdentityNumber())
			return true;
		else
			return false;
	}

	public List<Course> getRegisteredCourses() {
		return registeredCourses;
	}

	public Grade getCourseGrade(Course c){
		Grade g=null;
		Iterator<Grade> iter=grades.iterator();
		while(iter.hasNext()){
			Grade x=iter.next();
			if(x.getCourse().getNumber()==c.getNumber())
				g=x;
		}
		return g;
	}

	@Override
	public int compareTo(Student o) {
		if(this.averageGrade()==o.averageGrade())
			return this.studentInfo.getIdentityNumber()-o.getStudentInfo().getIdentityNumber();
		else if(this.averageGrade()<o.averageGrade())
			return -1;
		else
			return 1;
	}
	
	//this method is public so i can use it in StudentManagmentSystem
	public List<Course> getCourseGraded(){
		return courseGraded;
	}
	
}
