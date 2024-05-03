package com.usecases.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usecases.model.Student;
import com.usecases.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger LOGGER = Logger.getLogger(StudentServiceImpl.class.getName());

    @Autowired
    private JmsTemplate jmsTemplate;

    private final StudentRepository studentRepository;
    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {

        return studentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Student getStudentById(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        return optionalStudent.orElse(null);
    }

    @Override
    @Transactional
    public Student createStudent(Student student) {

        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public Student updateStudent(Long id, Student student) {
        if (studentRepository.existsById(id)) {
            student.setId(id);
            return studentRepository.save(student);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {

        studentRepository.deleteById(id);
    }

    @Override
    public void sendMessageToArtemisQueue() {

        List<Student> students = studentRepository.findAll();

        students.forEach(student -> {
            try {
                String studentJson = new ObjectMapper().writeValueAsString(student);
                jmsTemplate.convertAndSend("myqueue", studentJson);
                LOGGER.log(Level.INFO, " send successfully.");
            } catch (JsonProcessingException e) {
                LOGGER.log(Level.INFO, " Exception");
            }
        });
    }


}

