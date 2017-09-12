import java.util.Date;

public class Main {

	public static void main(String[] args) {
		StudentGroup sg = new StudentGroup(1);
		sg.addFirst(new Student(120, "Vipanchith", new Date(), 80.0));
		Student student = sg.getStudent(0);
		System.out.println(student.getFullName());
		//You may test that your code works find here
		//Please check that your code works and has no 
		//compilation problems before to submit
	}

}
