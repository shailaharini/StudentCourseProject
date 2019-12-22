package com.ss.hibernate.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class StudentCourseId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long studentId;
	
	private Long courseId;
	
	public StudentCourseId() {
		
	}
	
	public StudentCourseId(Long studentId,Long courseId) {
		super();
		this.setStudentId(studentId);
		this.setCourseId(courseId);	
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	
	
	
	
	

}
