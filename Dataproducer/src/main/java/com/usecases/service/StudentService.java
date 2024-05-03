package com.usecases.service;



import com.usecases.model.Student;

import java.util.List;
public interface StudentService {
    List<Student> getAllStudents();

    Student getStudentById(Long id);

    Student createStudent(Student student);

    Student updateStudent(Long id, Student student);

    void deleteStudent(Long id);

    void sendMessageToArtemisQueue();
}
