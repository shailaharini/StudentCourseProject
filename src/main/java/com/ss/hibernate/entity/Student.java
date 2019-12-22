package com.ss.hibernate.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {
@Id
@Column(name="student_id")
@GeneratedValue
private Long studentId;

@Column(name="student_name")
private String studentName ;

/*@OneToMany(mappedBy = "course")
private Set<StudentCourse> studentCourses = new HashSet<>();

public Set<StudentCourse> getStudentCourses() {
	return studentCourses;
}

public void setStudentCourses(Set<StudentCourse> studentCourses) {
	this.studentCourses = studentCourses;
}*/

public Student(String studentName) {
this.studentName = studentName;
}

public Student() {
}

public Student(String studentName, Set<Course> courses) {
	this.studentName = studentName;
	this.courses = courses;
}

@ManyToMany(cascade = {CascadeType.PERSIST})
@JoinTable(name="student_course",
joinColumns={@JoinColumn(name="student_id")},inverseJoinColumns={@JoinColumn(name="course_id")})
private Set<Course> courses = new HashSet<Course>(0);

//utility methods
public void addCourse(Course c) {
	this.courses.add(c);
	c.getStudents().add(this);
}


public void removeCourse(Course c) {
	this.courses.remove(c);
	c.getStudents().remove(this);
}


public Long getStudentId() {
return studentId;
}

public void setStudentId(Long studentId) {
this.studentId = studentId;
}

public String getStudentName() {
return studentName;
}

public void setStudentName(String studentName) {
this.studentName = studentName;
}


public Set<Course> getCourses() {
return courses;
}

public void setCourses(Set<Course> courses) {
this.courses = courses;
} 


}