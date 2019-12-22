package com.ss.hibernate.dao;

import com.ss.hibernate.entity.*;
import com.ss.hibernate.util.SessionFactoryUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;



public class StudentDAO implements StudentDAOInterface<Student, Course>{
	
   
    public StudentDAO() {
    	
    }

	public void addStudent(Student entity1, Set<Course> entity2List) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
		session.beginTransaction();
		System.out.println("Check if session is open"+session.isOpen());
        System.out.println("The student name is "+entity1.getStudentName());
        System.out.println("The course list length  is "+entity2List.size());
		Student s1 =new Student(entity1.getStudentName(),entity2List);
		for(Course c :entity2List) {
			s1.addCourse(c);
		}
		
		session.persist(s1);

		session.getTransaction().commit();
		}
		catch(Exception e) {
			session.getTransaction().rollback();
			System.out.println("Exception occurred in addStudent"+e.getMessage());
		}	
		finally {
			session.close();
			
		}
		
		
		
	}


	public void deleteStudent(Student entity1) {
		// TODO Auto-generated method stub
		
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
		session.beginTransaction();
		
		Student s1 = (Student)session.get(Student.class, entity1.getStudentId());
		for(Course c : s1.getCourses()) {
			s1.removeCourse(c);
		}
		session.delete(s1);
		session.getTransaction().commit();
		}
		catch(Exception e) {
			session.getTransaction().rollback();
			System.out.println("Exception occurred in deleteStudent"+e.getMessage());
		}		
		finally {
			session.close();
			
		}
	}
    
	
	@SuppressWarnings("unchecked")
	public List<Student> findByCourseName(Course entity2) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Course.class);
		List<Course> courseList = criteria.add(Restrictions.eq("courseName", entity2.getCourseName())).list();
		Set<Student> sSet = new HashSet<Student>();
		for(Course c1:courseList) {
			System.out.println("The number of students in for loop "+c1.getStudents().size());
			sSet.addAll(c1.getStudents());
		}
		System.out.println("The number of students "+sSet.size());
		List<Student> sList = sSet.stream().collect(Collectors.toList());
		sList.sort((Student s1, Student s2)->s1.getStudentName().compareTo(s2.getStudentName()));
		return sList;
	}
   
	
	public List<Student> findStudentsNotRegistered(Course entity2) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		List<Student> sList = findByCourseName(entity2);
		List<Student> fullList = findAllStudents();
		
		System.out.println("The number of students registered for a course:"+sList.size());
		System.out.println("The number of students for all courses:"+fullList.size());
		
		List<Student> filteredList =
				fullList.stream()
			      .filter(fullStudent -> sList.stream().noneMatch(
			                sStudent -> 
			                sStudent.getStudentId().equals(fullStudent.getStudentId()))).collect(Collectors.toList());
		
		System.out.println("The number of students not registered for that course:"+filteredList);
			      
		return filteredList;
	}
	
	
	@SuppressWarnings("unchecked")
    public List<Student> findAllStudents() {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();

        List<Student> sList = (List<Student>) session.createQuery("from Student").list();
        return sList;
    }

	@Override
	public void addCourse(Course entity1, Set<Student> entity2List) {
		// TODO Auto-generated method stub
		
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
		session.beginTransaction();
		System.out.println("Check if session is open"+session.isOpen());
        System.out.println("The course name is "+entity1.getCourseName());
        System.out.println("The student list length  is "+entity2List.size());
		Course c1 =new Course(entity1.getCourseName(),entity2List);
		//s1.getCourses().addAll(entity2List);
		for(Student s :entity2List) {
			c1.addStudent(s);
			session.persist(s);
		}
		
		session.persist(c1);

		session.getTransaction().commit();
		}
		catch(Exception e) {
			session.getTransaction().rollback();
			System.out.println("Exception occurred in addStudent"+e.getMessage());
		}	
		finally {
			session.close();
			
		}
		
	}
	
	/*public void getStudentScores(Student entity1, Course entity2,int score) {
		
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		
		try {
			
			session.beginTransaction();
			
		    Student s1 = new Student(entity1.getStudentName());
			session.persist(s1);
			
			Course c1= new Course(entity2.getCourseName());
			session.persist(c1);
			
			StudentCourse sc = new StudentCourse();
			sc.setScore(score);
			s1.getStudentCourses().add(sc);
			c1.getStudentCourses().add(sc);
			session.persist(sc);
			
			session.getTransaction().commit();
		}
		
		catch(Exception e) {
			session.getTransaction().rollback();
			System.out.println("Exception occurred in getStudentScores"+e.getMessage());
		}	
		finally {
			session.close();
			
		}
	
	}*/
	

}
