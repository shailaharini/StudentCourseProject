package com.ss.hibernate.dao;

import java.util.List;
import java.util.Set;

public interface StudentDAOInterface<T1, T2> {
    
    public void addStudent(T1 entity1,Set<T2> entityList);
    
    public void deleteStudent(T1 entity1);
       
    public List<T1> findByCourseName(T2 entity2);
    
    public List<T1> findStudentsNotRegistered(T2 entity2);
    
    public void addCourse(T2 entity1,Set<T1> entityList);
     
}
