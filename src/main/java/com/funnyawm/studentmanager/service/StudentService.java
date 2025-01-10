package com.funnyawm.studentmanager.service;

import com.funnyawm.studentmanager.mapper.StudentMapper;
import com.funnyawm.studentmanager.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements StudentMapper{
    StudentMapper studentMapper;

    @Autowired
    public StudentService(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public Student getStudentById(int id) {
        return studentMapper.getStudentById(id);
    }

    @Override
    public void insertStudent(Student student) {
        studentMapper.insertStudent(student);
    }

    @Override
    public void deleteStudentById(int id) {
        studentMapper.deleteStudentById(id);
    }

    @Override
    public void updateStudent(Student student) {
        studentMapper.updateStudent(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentMapper.getAllStudents();
    }
}
