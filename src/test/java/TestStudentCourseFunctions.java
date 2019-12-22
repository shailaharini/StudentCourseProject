
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import com.ss.hibernate.dao.StudentDAO;
import com.ss.hibernate.dao.StudentDAOInterface;
import com.ss.hibernate.entity.Course;
import com.ss.hibernate.entity.Student;

public class TestStudentCourseFunctions {
	
	private static StudentDAOInterface<Student, Course> sd;
	 
    public TestStudentCourseFunctions() {
    	sd = new StudentDAO();
    }
	
    @Test
	public void testAddStudent() {
		Student st1 = new Student("iii");
		Student st2 = new Student("hhh");
		Set<Course> cList = new HashSet<Course>();
		Course c1 = new Course();
		c1.setCourseName("Geography");
		cList.add(c1);
		Course c2 = new Course();
		c2.setCourseName("History");
		cList.add(c2);
		sd.addStudent(st2, cList);
		//sd.addStudent(st1, cList);
		//TODO :assertNotNull(Goes the insert record impl);
	}
	
    @Test
	public void deleteStudent() {
		Student st1 = new Student();
		st1.setStudentId((long) 8);
		sd.deleteStudent(st1);
		//TODO :assertNull(Goes the delete record impl);
	}
	
	
	@Test
	public void testFindByCourse() {
		Course c1 = new Course();
		c1.setCourseName("Arts");
		List<Student> sList =sd.findByCourseName(c1);
		sList.stream()
                    .map(Student::getStudentName)
                    .collect(Collectors.toList()).forEach(System.out::println);
		assertEquals(sList.size(), 2);
		
	}
	
	@Test
	public void testFindStudentsNotRegistered(){
		Course c1 = new Course();
		c1.setCourseName("Arts");
		List<Student> sList =sd.findStudentsNotRegistered(c1);
		sList.stream()
        .map(Student::getStudentName)
        .collect(Collectors.toList()).forEach(System.out::println);
		assertEquals(sList.size(), 3);
	}

	
	@Test
	public void testAddCourse() {
		//Course c1 = new Course("Biology");
		Course c2 = new Course("Arts");
		Set<Student> sList = new HashSet<Student>();
		Student s1 = new Student();
		s1.setStudentName("zzz");
		sList.add(s1);
		Student s2 = new Student();
		s2.setStudentName("xxx");
		sList.add(s2);
		sd.addCourse(c2, sList);
	}
	
	public static void main(String[] args) {
		TestStudentCourseFunctions tscf = new TestStudentCourseFunctions();
		//TODO:For now just calling directly the methods without junit implementation
		//for inserts and deletes as it requires additional DB call
		//tscf.testAddStudent();
		//tscf.testAddCourse();
		//tscf.deleteStudent();
		tscf.testFindByCourse();
		tscf.testFindStudentsNotRegistered();
	}
}
