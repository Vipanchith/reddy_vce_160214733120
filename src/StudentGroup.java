import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A fix-sized array of students
 * array length should always be equal to the number of stored elements
 * after the element was removed the size of the array should be equal to the number of stored elements
 * after the element was added the size of the array should be equal to the number of stored elements
 * null elements are not allowed to be stored in the array
 * 
 * You may add new methods, fields to this class, but DO NOT RENAME any given class, interface or method
 * DO NOT PUT any classes into packages
 *
 */
public class StudentGroup implements StudentArrayOperation {

	private Student[] students;
	
	/**
	 * DO NOT remove or change this constructor, it will be used during task check
	 * @param length
	 */
	public StudentGroup(int length) {
		this.students = new Student[length];
	}

	@Override
	public Student[] getStudents() {
		// Add your implementation here
		return students;
	}

	@Override
	public void setStudents(Student[] students) {
		// Add your implementation here
		if (students == null)
			throw new IllegalArgumentException();
		this.students = students;
	}

	@Override
	public Student getStudent(int index) {
		// Add your implementation here
		if (index < 0 || index >= students.length)
			throw new IllegalArgumentException();
		return students[index];
	}

	@Override
	public void setStudent(Student student, int index) {
		// Add your implementation here
		if (student == null || index < 0 || index >= students.length)
			throw new IllegalArgumentException();
		students[index] = student;
	}

	@Override
	public void addFirst(Student student) {
		// Add your implementation here
		if (student == null)
			throw new IllegalArgumentException();
		Student[] newList = new Student[students.length+1];
		newList[0] = student;
		for (int i=1; i<newList.length; i++)
			newList[i] = students[i-1];
		students = newList;
	}

	@Override
	public void addLast(Student student) {
		// Add your implementation here
		if (student == null)
			throw new IllegalArgumentException();
		Student[] newList = new Student[students.length+1];
		for (int i=0; i<newList.length-1; i++)
			newList[i] = students[i];
		newList[newList.length-1] = student;
		students = newList;
	}

	@Override
	public void add(Student student, int index) {
		// Add your implementation here
		if (student == null || index < 0 || index >= students.length)
			throw new IllegalArgumentException();
		Student[] newList = new Student[students.length+1];
		for (int i=0; i<index; i++)
			newList[i] = students[i];
		newList[index] = student;
		for (int i=index+1; i<newList.length; i++)
			newList[i] = students[i-1];
		students = newList;
	}

	@Override
	public void remove(int index) {
		// Add your implementation here
		if (index < 0 || index >= students.length)
			throw new IllegalArgumentException();
		Student[] newList = new Student[students.length-1];
		for (int i=0; i<index; i++)
			newList[i] = students[i];
		for (int i=index; i<newList.length; i++)
			newList[i] = students[i+1];
		students = newList;
	}

	@Override
	public void remove(Student student) {
		// Add your implementation here
		if (student == null)
			throw new IllegalArgumentException();
		
		for (int i=0; i<students.length; i++) {
			if (students[i].compareTo(student) == 0) {
				remove(i);
				return;
			}
		}
		
		throw new IllegalArgumentException("Student not exist");
	}

	@Override
	public void removeFromIndex(int index) {
		// Add your implementation here
		Student[] newList = new Student[index+1];
		for (int i=0; i<newList.length; i++)
			newList[i] = students[i];
		
		students = newList;
	}

	@Override
	public void removeFromElement(Student student) {
		// Add your implementation here
		if (student == null)
			throw new IllegalArgumentException();
		
		for (int i=0; i<students.length; i++) {
			if (students[i].compareTo(student) == 0) {
				removeFromIndex(i);
				break;
			}
		}
	}

	@Override
	public void removeToIndex(int index) {
		// Add your implementation here
		Student[] newList = new Student[students.length-index];
		int idx = 0;
		for (int i=index; i<students.length; i++)
			newList[idx++] = students[i];
		
		students = newList;
	}

	@Override
	public void removeToElement(Student student) {
		// Add your implementation here
		for (int i=0; i<students.length; i++) {
			if (students[i].compareTo(student) == 0) {
				removeToIndex(i);
				break;
			}
		}
	}

	@Override
	public void bubbleSort() {
		// Add your implementation here
		Student temp;
		for (int i=0; i<students.length; i++) {
			for(int j = 0; j < students.length-i-1; j++) {
				if(students[j-1].compareTo(students[j]) == 1) {
					temp = students[j-1];
					students[j-1] = students[j];
					students[j] = temp;
				}
			}
        }
	}

	@Override
	public Student[] getByBirthDate(Date date) {
		// Add your implementation here
		if (date == null)
			throw new IllegalArgumentException();
		int count = 0;
		for (int i=0; i<students.length; i++) {
			if (students[i].getBirthDate().before(date))
				count++;
		}
		
		Student[] birthList = new Student[count];
		int idx = 0;
		for (int i=0; i<students.length; i++) {
			if (students[i].getBirthDate().before(date))
				birthList[idx++] = students[i];
		}
		
		return birthList;
	}

	@Override
	public Student[] getBetweenBirthDates(Date firstDate, Date lastDate) {
		// Add your implementation here
		if (firstDate == null || lastDate == null)
			throw new IllegalArgumentException();
		
		int count = 0;
		for (int i=0; i<students.length; i++) {
			if (students[i].getBirthDate().after(firstDate) && (students[i].getBirthDate().before(firstDate)))
				count++;
		}
		
		Student[] birthList = new Student[count];
		int idx = 0;
		for (int i=0; i<students.length; i++) {
			if (students[i].getBirthDate().after(firstDate) && (students[i].getBirthDate().before(firstDate)))
				birthList[idx++] = students[i];
		}
		
		return birthList;
	}

	@Override
	public Student[] getNearBirthDate(Date date, int days) {
		// Add your implementation here
		return null;
	}

	@Override
	public int getCurrentAgeByDate(int indexOfStudent) {
		// Add your implementation here
		if (indexOfStudent == 0)
			throw new IllegalArgumentException();
		
		Date date = new Date();
		long age = (date.getTime()/(1000 * 60 * 60 * 24*365)) - (students[indexOfStudent].getBirthDate().getTime()/(1000 * 60 * 60 * 24*365));
		return (int)age;
	}

	@Override
	public Student[] getStudentsByAge(int age) {
		// Add your implementation here
		int count = 0;
		for (int i=0; i<students.length; i++) {
			int tempAge = this.getCurrentAgeByDate(i);
			if(tempAge == age)
				count++;
		}
		
		Student[] nearList = new Student[count];
		count = 0;
		for(int i=0; i<students.length; i++) {
			int tempAge = this.getCurrentAgeByDate(i);
			if(tempAge == age)
				nearList[i] = students[i];
		}
		
		return nearList;
	}

	@Override
	public Student[] getStudentsWithMaxAvgMark() {
		// Add your implementation here
		double max = students[0].getAvgMark();
		for (int i=1; i<students.length; i++)
			if (students[i].getAvgMark() > max)
				max = students[i].getAvgMark();
		
		int count = 0;
		for (int i=0; i<students.length; i++)
			if (students[i].getAvgMark() == max)
				count++;
		
		Student[] markList = new Student[count];
		int idx = 0;
		for (int i=0; i<students.length; i++)
			if (students[i].getAvgMark() == max)
				markList[idx++] = students[i];
		
		return markList;
	}

	@Override
	public Student getNextStudent(Student student) {
		// Add your implementation here
		if(student == null)
			throw new IllegalArgumentException();
		
		int i = 0;
		for(i=0; i<students.length; i++)
			if(students[i].compareTo(student) == 0)
				break;
		
		i++;
		return students[i];
	}
}
