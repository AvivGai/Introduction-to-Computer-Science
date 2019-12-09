/*---------------------------------------
 Genuine author: Aviv Gai, I.D.: 203147988
 Date: 30-12-2017 
---------------------------------------*/
import java.util.Iterator;

public class StudentManagementSystem {
	
	private List<Student> studentList;
	private List<Degree> degreeList;
	private List<Course> courseList;
	private int passGrade;

	public StudentManagementSystem(int failTreshold) {
		if(failTreshold<0 | failTreshold>100)
			throw new IllegalArgumentException();
	
		passGrade = failTreshold;
		studentList  = new DynamicArray<Student>();
		degreeList = new DynamicArray<Degree>();
		courseList = new DynamicArray<Course>();
	}

	//add a student to the system only if he is not already in the system and his degree is in the system.
	public boolean addStudent(Student student){
		for(Student element: studentList)
			if(element.equals(student))
				return false;
		if(!degreeList.contains(student.getDegree()))
			return false;
		else{
			studentList.add(student);
			return true;
		}
	}

	//add a course to the system only if it is not already in the system and all of its preliminary courses are in the system.
	public boolean addCourse(Course course){
		for(Course element: courseList){
			if(element.equals(course))
				return false;
		}
		for(Course element: course.getAllPreliminaryCourses()){
			if(!courseList.contains(element))
				return false;
		}
		courseList.add(course);
		return true;
	}

	//add a degree to the system only if it is not already in the system and all of its courses (mandatory and elective) are in the system.
	public boolean addDegree(Degree degree) {
		if(degreeList.contains(degree))
			return false;
		for(Course element: degree.getMandatoryCourses())
			if(!courseList.contains(element))
				return false;
		for(Course element: degree.getElectiveCourses())
			if(!courseList.contains(element))
				return false;
		degreeList.add(degree);
		return true;
	}

	//return all preliminary courses that a student needs before registering to a specific course.
	public List<Course> getMissingPreCourses(Course course, int studentId){
		List<Course> missingCourses = new LinkedList<>();
		Student stud = this.findStudentById(studentId);
		if(stud==null)
			missingCourses = null;
		else{
			Iterator<Course> iter = course.getAllPreliminaryCourses().iterator();
			while(iter.hasNext()){
				Course c=iter.next();
				if(!stud.getCourseGraded().contains(c))
					missingCourses.add(c);
			}
		}
		return missingCourses;
	}

	//register a student to a course if its part of his degree & he did all the preliminary courses & he didn't learn the course or failed in it.
	public boolean register(int studentId, Course course){
		Student stud = this.findStudentById(studentId);
		if(stud==null | !courseList.contains(course))
			return false;
		if(!stud.getDegree().getElectiveCourses().contains(course) & !stud.getDegree().getMandatoryCourses().contains(course))
			return false;
		for( Course element: course.getAllPreliminaryCourses())
			if(!stud.getCourseGraded().contains(element))
				return false;
		if(stud.getCourseGraded().contains(course)){
			Iterator<Grade> iter = stud.getGrades().iterator();
			while(iter.hasNext()){
				Grade g=iter.next();
				if(g.getCourse().getNumber()==course.getNumber()){
					if(g.getGrade()<passGrade){
						stud.registerTo(course);
						return true;
					}
					return false;
				}
			}
		}
		stud.registerTo(course);
		return true;
	}

	//add a grade in a course to a student only is he is register to the course or failed in it in the past.
	public boolean addGrade(Course course, int studentId, int grade){
		Student stud = findStudentById(studentId);
		if(stud==null || !courseList.contains(course) | !stud.isRegisteredTo(course))
			return false;
		if(stud.getCourseGraded().contains(course)){
			Iterator<Grade> iter = stud.getGrades().iterator();
			while(iter.hasNext()){
				Grade g=iter.next();
				if(g.getCourse().getNumber()==course.getNumber()){
					if(g.getGrade()>=passGrade)
						return false;
				}
			}
		}
		stud.addGrade(course, grade);
		return true;
	}
	
	//update grades for students who finished the course and return a list of students who failed.
	public List<Student> closeCourse(Course course, List<Pair<Integer, Integer>> grades){
		List<Student> studentsFailed = new LinkedList<>();
		Iterator<Pair<Integer,Integer>> iter = grades.iterator();
		while (iter.hasNext()){
			Pair<Integer,Integer> x=iter.next();
			Student student= findStudentById(x.getFirst());
			student.addGrade(course, x.getSecond());
			if(x.getSecond()<passGrade)
				studentsFailed.add(student);
		}
		return studentsFailed;
	}
	
	//close degree for a student if he passed all the mandatory courses and also passed enough elective courses.
	public boolean closeDegree(int studentId, int year){
		Student student = this.findStudentById(studentId);
		Degree degree = student.getDegree();
		int mandatoryCredits = 0;
		if(student == null || !student.getRegisteredCourses().isEmpty())
			return false;
		Iterator<Course> iter = degree.getMandatoryCourses().iterator();
		while(iter.hasNext()){
			Course c = iter.next();
			if(!student.getCourseGraded().contains(c) | student.getCourseGrade(c).getGrade()<passGrade)
				return false;
			mandatoryCredits = mandatoryCredits + c.getCredit();
		}
		int electiveCreditrequired = degree.getRequiredCredits()- mandatoryCredits;
		int electiveCreditPassed = 0;
		Iterator<Grade> iter1=student.getGrades().iterator();
		while(iter1.hasNext()){
			Grade g = iter1.next();
			if(!degree.getMandatoryCourses().contains(g.getCourse())){
				if(g.getGrade()>=passGrade)
					electiveCreditPassed = electiveCreditPassed + g.getCourse().getCredit();
			}
		}
		if(electiveCreditPassed<electiveCreditrequired)
			return false;
		student.closeDegree(year);
		return true;
	}
	
	// return a list of top K students in a degree who finished in the same year.
	public List<Student> getFirstKStudents(Degree degree, int year, int k){
		if(degree==null | year<0 | k<0)
			throw new IllegalArgumentException();
		List<Student> listOfStudents = new LinkedList<>();
		Iterator<Student> iter = studentList.iterator();
		while(iter.hasNext()){
			Student s = iter.next();
			if(s.getDegree()==degree & s.getFinishYear()==year)
				listOfStudents.add(s);
		}
		Sorter.bSort(listOfStudents);
		List<Student> listKStudents = new LinkedList<>();
		for(int i=0; i<k & i<listOfStudents.size(); i=i+1)
			listKStudents.add(listOfStudents.get(i));
		Sorter.bSort(listKStudents);
		return listKStudents;
	}

	//return a list of students who failed the course.
	public List<Student> getFailStudents(Course course){
		List<Student> failedStudents = new LinkedList<>();
		Iterator<Student> iter = studentList.iterator();
			while(iter.hasNext()){
				Student s = iter.next();
				if(s.getCourseGraded().contains(course) && s.getCourseGrade(course).getGrade() < passGrade)
					failedStudents.add(s);
			}
		return failedStudents;
	}

	//return all the students who are registered to the course.
	public List<Student> getRegisteredStudents(Course course){
		List<Student> registeredStudents = new LinkedList<>();
		Iterator<Student> iter = studentList.iterator();
			while(iter.hasNext()){
				Student s = iter.next();
				if(s.getRegisteredCourses().contains(course))
					registeredStudents.add(s);
			}
		return registeredStudents;
	}

	//return all the next available courses to a student in his degree (courses he has not done yet but did all the preliminary courses).
	public List<Course> nextAvailableCourses(int studentId){
		List<Course> availableCourses = new LinkedList<>();
		Student student = findStudentById(studentId);
		Degree degree = student.getDegree();
		boolean canRegister=true;
		Iterator<Course> iter = degree.getMandatoryCourses().iterator();
		while (iter.hasNext()){
			Course c = iter.next();
			canRegister=true;
			if(!student.getCourseGraded().contains(c)){
				for(Course element: c.getAllPreliminaryCourses()){
					if(!student.getCourseGraded().contains(element))
						canRegister=false;
					if(canRegister)
						availableCourses.add(c);
				}
			}
		}
		Iterator<Course> iter1 = degree.getElectiveCourses().iterator();
		while (iter1.hasNext()){
			Course c = iter.next();
			if(!student.getCourseGraded().contains(c))
				availableCourses.add(c);
		}
		Sorter.bSort(availableCourses);
		return availableCourses;	
	}

	//return all the mandatory courses that the student did not learn or failed.
	public List<Course> getMissingCourses(int studentId){
		Student student = findStudentById(studentId);
		List<Course> mandCoursesLeft = new LinkedList<>();
		List<Course> mandCoursesOfDegree = student.getDegree().getMandatoryCourses();
		Iterator<Course> iter = mandCoursesOfDegree.iterator();
		while(iter.hasNext()){
			Course c = iter.next();
			if(!student.getCourseGraded().contains(c))
				mandCoursesLeft.add(c);
			else if(student.getCourseGrade(c).getGrade()<passGrade)
				mandCoursesLeft.add(c);
		}
		Sorter.bSort(mandCoursesLeft);
		return mandCoursesLeft;
	}

	
	public List<Student> getStudents() {
		Sorter.bSort(studentList);
		return studentList;
	}

	
	public List<Course> getCourses() {
		Sorter.bSort(courseList);
		return courseList;
	}

	
	public List<Degree> getDegrees() {
		return degreeList;
	}

	
	public int getFailTreshold() {
		return passGrade;
	}
	
	//my own private function - finding a student by its ID number.
	private Student findStudentById(int studentId){
		Student stud=null;
		Iterator<Student> iter1 = studentList.iterator();
		while(iter1.hasNext()){
			Student s=iter1.next();
			if(s.getStudentInfo().getIdentityNumber()==studentId)
				stud = s;
		}
		return stud;
	}
	
}
