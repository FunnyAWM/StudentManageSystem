package com.funnyawm.studentmanager.service;

import com.funnyawm.studentmanager.mapper.StudentMapper;
import com.funnyawm.studentmanager.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService implements StudentMapper {
    StudentMapper studentMapper;

    @Autowired
    public StudentService(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Student getStudentById(int id) {
        return studentMapper.getStudentById(id);
    }

    @Override
    public List<Student> getStudentByName(String name) {
        return studentMapper.getStudentByName(name);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void insertStudent(Student student) {
        studentMapper.insertStudent(student);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void deleteStudentById(int id) {
        studentMapper.deleteStudentById(id);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void updateStudent(Student student) {
        studentMapper.updateStudent(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentMapper.getAllStudents();
    }
}
