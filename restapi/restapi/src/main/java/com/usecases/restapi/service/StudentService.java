package com.usecases.restapi.service;

import com.usecases.restapi.model.Student;

import java.util.List;
public interface StudentService {
    List<Student> getAllStudents();

    Student getStudentById(Long id);

    Student createStudent(Student student);

    Student updateStudent(Long id, Student student);

    void deleteStudent(Long id);
}
