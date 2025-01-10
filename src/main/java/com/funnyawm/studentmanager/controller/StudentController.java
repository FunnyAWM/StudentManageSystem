package com.funnyawm.studentmanager.controller;

import com.funnyawm.studentmanager.model.Student;
import com.funnyawm.studentmanager.model.StudentIdWrapper;
import com.funnyawm.studentmanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    StudentService studentService;

    @GetMapping(value = {"/getStudent", "/updateStudent", "/deleteStudent", "/addStudent"})
    public ResponseEntity<String> returnMethodNotAllowed() {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Get method not allowed");
    }

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/getStudent")
    public Student getStudentById(@RequestBody StudentIdWrapper studentIdWrapper) {
        return studentService.getStudentById(studentIdWrapper.getId());
    }

    @PostMapping("/updateStudent")
    public void updateStudent(@RequestBody Student student) {
        studentService.updateStudent(student);
    }

    @PostMapping("/deleteStudent")
    public void deleteStudent(@RequestBody StudentIdWrapper studentIdWrapper) {
        studentService.deleteStudentById(studentIdWrapper.getId());
    }

    @PostMapping("/addStudent")
    public void addStudent(@RequestBody Student student) {
        studentService.insertStudent(student);
    }

    @PostMapping("/allStudents")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
}
