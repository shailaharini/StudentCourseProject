CREATE DATABASE IF NOT EXISTS AssignmentDb;
CREATE DATABASE AssignmentDb;

USE AssignmentDb;

DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS student_course;

CREATE TABLE student (
  student_id int(20) NOT NULL auto_increment,
  student_name varchar(255) NULL,
  PRIMARY KEY (student_id )
);

CREATE TABLE course (
  course_id int(20) NOT NULL auto_increment,
  course_name varchar(255) NULL ,
  PRIMARY KEY (course_id )
);

CREATE TABLE student_course (
  student_id int(20) NOT NULL ,
  course_id int(20) NOT NULL ,
  PRIMARY KEY (student_id,course_id ),
  FOREIGN KEY (student_id) REFERENCES student(student_id), 
  FOREIGN KEY (course_id) REFERENCES course(course_id)
);



