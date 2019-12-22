	package com.ss.hibernate.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course {
@Id
@Column(name = "course_id")
@GeneratedValue
private Long courseId;

@Column(name = "course_name")
private String courseName;

/*@OneToMany(mappedBy = "course")
private Set<StudentCourse> studentCourses = new HashSet<>();


public Set<StudentCourse> getStudentCourses() {
	return studentCourses;
}

public void setStudentCourses(Set<StudentCourse> studentCourses) {
	this.studentCourses = studentCourses;
}*/

@ManyToMany(mappedBy="courses")
private Set<Student> students = new HashSet<Student>();

public Course() {
}

public Course(String courseName, Set<Student> students) {
	this.courseName = courseName;
	this.students = students;
}

//utility methods
public void addStudent(Student s) {
	this.students.add(s);
	s.getCourses().add(this);
}

public void removeStudent(Student s) {
	this.students.remove(s);
	s.getCourses().remove(this);
}

public Course(String courseName) {
this.courseName = courseName;
}

public Set<Student> getStudents() {
return students;
}

public void setStudents(Set<Student> students) {
this.students = students;
}

public Long getCourseId() {
return courseId;
}

public void setCourseId(Long courseId) {
this.courseId = courseId;
}

public String getCourseName() {
return courseName;
}

public void setCourseName(String courseName) {
this.courseName = courseName;
}





}