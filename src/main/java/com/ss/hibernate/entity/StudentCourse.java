package com.ss.hibernate.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class StudentCourse {
	
	@EmbeddedId
    private StudentCourseId id = new StudentCourseId();
	
	@ManyToOne
    @MapsId("studentId")
    private Student student;
 
    @ManyToOne
    @MapsId("courseId")
    private Course course;
 
    private int score;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
    
    

}
